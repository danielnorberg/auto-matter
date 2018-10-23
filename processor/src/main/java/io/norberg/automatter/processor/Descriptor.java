package io.norberg.automatter.processor;

import static java.util.stream.Collectors.joining;
import static javax.lang.model.element.Modifier.DEFAULT;
import static javax.lang.model.element.Modifier.PUBLIC;
import static javax.lang.model.element.Modifier.STATIC;

import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeVariableName;
import io.norberg.automatter.AutoMatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ErrorType;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.IntersectionType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import javax.lang.model.type.UnionType;
import javax.lang.model.type.WildcardType;
import javax.lang.model.util.Elements;
import javax.lang.model.util.TypeKindVisitor8;
import javax.lang.model.util.Types;

/**
 * Holds information about an automatter annotated interface and the entities it generates.
 */
class Descriptor {

  private final DeclaredType valueType;
  private final TypeElement valueTypeElement;
  private final List<? extends TypeMirror> valueTypeArguments;
  private final String packageName;
  private final String valueTypeName;
  private final String builderName;
  private final List<ExecutableElement> fields;
  private final Map<ExecutableElement, TypeName> fieldTypes;
  private final boolean isPublic;
  private final String concreteBuilderName;
  private final String fullyQualifiedBuilderName;
  private boolean isGeneric;
  private boolean toBuilder;
  private ExecutableElement toString;

  Descriptor(final Element element, final Elements elements, final Types types)
      throws AutoMatterProcessorException {
    if (!element.getKind().isInterface()) {
      throw new AutoMatterProcessorException("@AutoMatter target must be an interface", element);
    }
    this.valueType = (DeclaredType) element.asType();
    this.valueTypeElement = (TypeElement) element;
    this.valueTypeArguments = valueType.getTypeArguments();
    this.valueTypeName = nestedName(valueTypeElement, elements);
    this.isGeneric = !valueTypeArguments.isEmpty();
    this.packageName = elements.getPackageOf(element).getQualifiedName().toString();
    this.builderName = element.getSimpleName().toString() + "Builder";
    final String typeParameterization = isGeneric ?
        "<" + valueTypeArguments.stream().map(TypeMirror::toString).collect(joining(",")) + ">"
        : "";
    this.concreteBuilderName = builderName + typeParameterization;
    this.fullyQualifiedBuilderName = fullyQualifedName(packageName, concreteBuilderName);
    this.fields = new ArrayList<>();
    this.fieldTypes = new LinkedHashMap<>();
    this.isPublic = element.getModifiers().contains(PUBLIC);
    this.toString = findToStringMethod(valueTypeElement);
    enumerateFields(types);
  }

  Optional<ExecutableElement> toStringMethod() {
    return Optional.ofNullable(toString);
  }

  private static String nestedName(final TypeElement element, final Elements elements) {
    final String qualifiedName = element.getQualifiedName().toString();
    final String packageName = elements.getPackageOf(element).getQualifiedName().toString();
    if (packageName.isEmpty()) {
      return qualifiedName;
    }
    return qualifiedName.substring(packageName.length() + 1);
  }

  private void enumerateFields(final Types types) {
    final List<ExecutableElement> methods = methods(valueTypeElement);
    for (final Element member : methods) {
      if (member.getKind() != ElementKind.METHOD || !Fields.isField(member)) {
        continue;
      }
      final ExecutableElement method = (ExecutableElement) member;
      if (member.getSimpleName().toString().equals("builder")) {
        final TypeMirror returnType = (method).getReturnType();
        // TODO: javac does not seem to want to provide the name of the return type if it is not yet present and generic
        if (!isGeneric &&
            !returnType.toString().equals(concreteBuilderName) &&
            !returnType.toString().equals(fullyQualifiedBuilderName)) {
          throw new AutoMatterProcessorException(
              "builder() return type must be " + concreteBuilderName, valueTypeElement);
        }
        toBuilder = true;
        continue;
      }

      if (Fields.hasDefaultValue(method)
          && (Fields.isPrimitive(method)
          || Fields.isNullableAnnotated(method)
          || Fields.isCollection(method)
          || Fields.isMap(method)
          || Fields.isOptional(method))) {
        throw new AutoMatterProcessorException(
            "Field with default value cannot be nullable, a primitive, a collection, a map or optional.", valueTypeElement);
      }

      verifyResolved(method.getReturnType());

      fields.add(method);

      // Resolve inherited members
      final ExecutableType methodType = (ExecutableType) types.asMemberOf(valueType, member);
      final TypeMirror fieldType = methodType.getReturnType();


      // Resolve types
      fieldTypes.put(method, TypeName.get(fieldType));
    }
  }

  private List<ExecutableElement> methods(final TypeElement element) {
    final Map<String, ExecutableElement> methodMap = new LinkedHashMap<>();
    enumerateMethods(element, methodMap);
    return new ArrayList<>(methodMap.values());
  }

  private void enumerateMethods(final TypeElement element, final Map<String, ExecutableElement> methods) {
    for (final TypeMirror interfaceType : element.getInterfaces()) {
      final TypeElement interfaceElement = (TypeElement) ((DeclaredType) interfaceType).asElement();
      enumerateMethods(interfaceElement, methods);
    }
    for (final Element member : element.getEnclosedElements()) {
      if (member.getKind() != ElementKind.METHOD) {
        continue;
      }
      methods.put(member.getSimpleName().toString(), (ExecutableElement) member);
    }
  }

  private ExecutableElement findToStringMethod(final TypeElement element) {
    final List<ExecutableElement> matches = new ArrayList<>();
    for (final Element member : element.getEnclosedElements()) {
      if (member.getKind() == ElementKind.METHOD && member.getAnnotation(AutoMatter.ToString.class) != null) {
        if (!member.getModifiers().contains(STATIC) && !member.getModifiers().contains(DEFAULT)) {
          throw new AutoMatterProcessorException(
              "Method annotated with @AutoMatter.ToString must be static or default", valueTypeElement);
        }
        matches.add((ExecutableElement) member);
      }
    }
    if (matches.size() == 1) {
      return matches.get(0);
    } else if (matches.size() > 1) {
      throw new AutoMatterProcessorException(
          "There must only be one @AutoMatter.ToString annotated method on a type", valueTypeElement);
    }
    for (final TypeMirror interfaceType : element.getInterfaces()) {
      final TypeElement interfaceElement = (TypeElement) ((DeclaredType) interfaceType).asElement();
      final ExecutableElement method = findToStringMethod(interfaceElement);
      if (method != null) {
        return method;
      }
    }
    return null;
  }

  String packageName() {
    return this.packageName;
  }

  String builderName() {
    return this.builderName;
  }

  String valueTypeName() {
    return this.valueTypeName;
  }

  boolean isPublic() {
    return this.isPublic;
  }

  boolean isGeneric() {
    return this.isGeneric;
  }

  List<ExecutableElement> fields() {
    return fields;
  }

  Map<ExecutableElement, TypeName> fieldTypes() {
    return fieldTypes;
  }

  boolean hasToBuilder() {
    return this.toBuilder;
  }

  private static String fullyQualifedName(final String packageName, final String simpleName) {
    return packageName.isEmpty() ? simpleName : packageName + "." + simpleName;
  }

  List<TypeVariableName> typeVariables() {
    final List<TypeVariableName> variables = new ArrayList<>();
    if (isGeneric) {
      for (final TypeMirror argument : valueTypeArguments) {
        final TypeVariable typeVariable = (TypeVariable) argument;
        variables.add(TypeVariableName.get(typeVariable));
      }
    }
    return variables;
  }

  TypeName[] typeArguments() {
    final List<TypeVariableName> variables = typeVariables();
    return variables.toArray(new TypeName[0]);
  }

  private static void verifyResolved(TypeMirror type) {
    type.accept(new TypeKindVisitor8<Void, Void>() {
      @Override
      public Void visitIntersection(IntersectionType t, Void aVoid) {
        t.getBounds().forEach(Descriptor::verifyResolved);
        return null;
      }

      @Override
      public Void visitUnion(UnionType t, Void aVoid) {
        t.getAlternatives().forEach(Descriptor::verifyResolved);
        return null;
      }

      @Override
      public Void visitArray(ArrayType t, Void aVoid) {
        verifyResolved(t.getComponentType());
        return null;
      }

      @Override
      public Void visitError(ErrorType t, Void aVoid) {
        throw new UnresolvedTypeException(t.toString());
      }

      @Override
      public Void visitTypeVariable(TypeVariable t, Void aVoid) {
        verifyResolved(t.getLowerBound());
        verifyResolved(t.getUpperBound());
        return null;
      }

      @Override
      public Void visitWildcard(WildcardType t, Void aVoid) {
        final TypeMirror extendsBound = t.getExtendsBound();
        if (extendsBound != null) {
          verifyResolved(extendsBound);
        }
        final TypeMirror superBound = t.getSuperBound();
        if (superBound != null) {
          verifyResolved(superBound);
        }
        return null;
      }
    }, null);
  }
}
