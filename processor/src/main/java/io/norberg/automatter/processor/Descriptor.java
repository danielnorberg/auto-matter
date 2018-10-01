package io.norberg.automatter.processor;

import static java.util.stream.Collectors.joining;
import static javax.lang.model.element.Modifier.PUBLIC;
import static javax.lang.model.element.Modifier.STATIC;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.expr.Name;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeVariableName;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import javax.lang.model.util.Elements;
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
  private final List<ImportDeclaration> imports;
  private final boolean hasStarImports;
  private boolean isGeneric;
  private boolean toBuilder;
  private boolean needsStarImports;

  Descriptor(final Element element, final Elements elements, final Types types, final SourceProvider sourceProvider)
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
    final CompilationUnit compilationUnit = JavaParser.parse(sourceProvider.source(valueTypeElement));
    this.imports = compilationUnit.getImports();
    this.hasStarImports = imports.stream().anyMatch(ImportDeclaration::isAsterisk);
    enumerateFields(types);
  }

  boolean needsStarImports() {
    return needsStarImports;
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
      if (member.getKind() != ElementKind.METHOD ||
          isStaticOrDefault(member)) {
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

      fields.add(method);

      // Resolve inherited members
      final ExecutableType methodType = (ExecutableType) types.asMemberOf(valueType, member);
      final TypeMirror fieldType = methodType.getReturnType();

      // Resolve types
      fieldTypes.put(method, resolveType(fieldType, types));
    }
  }


  private TypeName resolveType(TypeMirror type, Types types) {
    switch (type.getKind()) {
      case ERROR:
        return resolveErrorType(type);
      case DECLARED:
        return resolveDeclaredType((DeclaredType) type, types);
      default:
        return TypeName.get(type);
    }
  }

  private TypeName resolveDeclaredType(DeclaredType type, Types types) {
    if (type.getTypeArguments().size() == 0) {
      return TypeName.get(type);
    }
    final ClassName rawType = ClassName.get((TypeElement) type.asElement());
    final TypeName[] typeArguments = type.getTypeArguments().stream()
        .map(a -> resolveType(a, types))
        .toArray(TypeName[]::new);
    return ParameterizedTypeName.get(rawType, typeArguments);
  }

  private TypeName resolveErrorType(TypeMirror type) throws AutoMatterProcessorException {

    final String typeString = type.toString();

    if (typeString.equals("<any>")) {
      // TODO: work around by introspecting the compilation unit
      throw new AutoMatterProcessorException("Failed to resolve type: " + type);
    }
    if (typeString.contains("<")) {
      throw new UnsupportedOperationException("FIXME: Generics in unresolved types not supported");
    }

    // Unresolved type, might be e.g. a generated type that is available later, look for a matching import
    final String identifier = typeString.split("\\.", 2)[0];
    final Optional<ImportDeclaration> memberImport = imports.stream()
        .filter(d -> !d.isAsterisk() && !d.isStatic())
        .filter(d -> d.getName().getIdentifier().equals(identifier))
        .findAny();

    if (!memberImport.isPresent()) {
      // Could not resolve, might need to include star imports in generated source
      if (hasStarImports) {
        needsStarImports = true;
      }
      return ClassName.bestGuess(typeString);
    }

    // Found a matching import, use that qualified name.
    final Name name = memberImport.get().getName();

    final String nameString;
    if (typeString.contains(".")) {
      nameString = name.toString() + "." + typeString.split("\\.", 2)[1];
    } else {
      nameString = name.toString();
    }
    return ClassName.bestGuess(nameString);
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

  private static boolean isStaticOrDefault(final Element member) {
    final Set<Modifier> modifiers = member.getModifiers();
    for (final Modifier modifier : modifiers) {
      if (modifier == STATIC) {
        return true;
      }
      // String comparison to avoid requiring JDK 8
      if (modifier.name().equals("DEFAULT")) {
        return true;
      }
    }
    return false;
  }

  public String packageName() {
    return this.packageName;
  }

  public String builderName() {
    return this.builderName;
  }

  public String valueTypeName() {
    return this.valueTypeName;
  }

  public boolean isPublic() {
    return this.isPublic;
  }

  public boolean isGeneric() {
    return this.isGeneric;
  }

  public List<ExecutableElement> fields() {
    return fields;
  }

  public Map<ExecutableElement, TypeName> fieldTypes() {
    return fieldTypes;
  }

  public boolean hasToBuilder() {
    return this.toBuilder;
  }

  private static String fullyQualifedName(final String packageName, final String simpleName) {
    return packageName.isEmpty() ? simpleName : packageName + "." + simpleName;
  }

  public List<TypeVariableName> typeVariables() {
    final List<TypeVariableName> variables = new ArrayList<>();
    if (isGeneric) {
      for (final TypeMirror argument : valueTypeArguments) {
        final TypeVariable typeVariable = (TypeVariable) argument;
        variables.add(TypeVariableName.get(typeVariable));
      }
    }
    return variables;
  }

  public TypeName[] typeArguments() {
    final List<TypeVariableName> variables = typeVariables();
    return variables.toArray(new TypeName[variables.size()]);
  }
}
