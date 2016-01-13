package io.norberg.automatter.processor;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeVariableName;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import javax.lang.model.util.Elements;

import static java.util.Collections.reverse;
import static javax.lang.model.element.ElementKind.PACKAGE;
import static javax.lang.model.element.Modifier.PUBLIC;
import static javax.lang.model.element.Modifier.STATIC;

/**
 * Holds information about an automatter annotated interface and the entities it generates.
 */
class Descriptor {

  private final String packageName;
  private final String valueTypeName;
  private final String builderName;
  private final boolean isGeneric;
  private final List<? extends TypeMirror> typeArguments;
  private final List<ExecutableElement> fields;
  private final boolean isPublic;
  private final boolean toBuilder;

  public static Descriptor from(final Element element, final Elements elements) throws AutoMatterProcessorException {
    if (!element.getKind().isInterface()) {
      throw new AutoMatterProcessorException("@AutoMatter target must be an interface", element);
    }

    final DeclaredType type = (DeclaredType) element.asType();
    final List<? extends TypeMirror> typeArguments = type.getTypeArguments();
    final boolean isGeneric = !typeArguments.isEmpty();
    final String typeParameterization = isGeneric ? "<" + Joiner.on(',').join(typeArguments) + ">" : "";

    final String packageName = elements.getPackageOf(element).getQualifiedName().toString();
    final String valueTypeName = nestedName(element);
    final String interfaceName = element.getSimpleName().toString();
    final String rawBuilderName = interfaceName + "Builder";
    final String builderName = rawBuilderName + typeParameterization;
    final String fullyQualifiedName = fullyQualifedName(packageName, builderName);

    final ImmutableList.Builder<ExecutableElement> fields = ImmutableList.builder();
    boolean toBuilder = false;
    for (final Element member : element.getEnclosedElements()) {
      if (member.getKind().equals(ElementKind.METHOD)) {
        final ExecutableElement executable = (ExecutableElement) member;
        if (isStaticOrDefault(member)) {
          continue;
        }

        if (executable.getSimpleName().toString().equals("builder")) {
          // TODO: javac does not seem to want to provide the name of the return type if it is not yet present and generic
          if (!isGeneric) {
            final String returnType = executable.getReturnType().toString();
            if (!returnType.equals(builderName) && !returnType.equals(fullyQualifiedName)) {
              throw new AutoMatterProcessorException("builder() return type must be " + builderName, element);
            }
          }
          toBuilder = true;
          continue;
        }
        fields.add(executable);
      }
    }

    final boolean isPublic = element.getModifiers().contains(PUBLIC);

    return new Descriptor(packageName, valueTypeName, rawBuilderName, isGeneric, typeArguments, fields.build(),
                          isPublic, toBuilder);
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

  private Descriptor(String packageName, String valueTypeName, String builderName, final boolean isGeneric,
                     final List<? extends TypeMirror> typeArguments, List<ExecutableElement> fields,
                     boolean isPublic, boolean toBuilder) {
    this.packageName = packageName;
    this.valueTypeName = valueTypeName;
    this.builderName = builderName;
    this.isGeneric = isGeneric;
    this.typeArguments = typeArguments;
    this.fields = fields;
    this.isPublic = isPublic;
    this.toBuilder = toBuilder;
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

  public List<? extends TypeMirror> typeArguments() {
    return typeArguments;
  }

  public String typeParameterization() {
    return isGeneric ? "<" + Joiner.on(',').join(typeArguments) + ">" : "";
  }

  public List<ExecutableElement> fields() {
    return this.fields;
  }

  public boolean hasToBuilder() {
    return this.toBuilder;
  }

  private static String nestedName(final Element element) {
    final List<Element> classes = enclosingClasses(element);
    final List<String> names = Lists.newArrayList();
    for (Element cls : classes) {
      names.add(cls.getSimpleName().toString());
    }
    return Joiner.on('.').join(names);
  }

  private static List<Element> enclosingClasses(final Element element) {
    final List<Element> classes = Lists.newArrayList();
    Element e = element;
    while (e.getKind() != PACKAGE) {
      classes.add(e);
      e = e.getEnclosingElement();
    }
    reverse(classes);
    return classes;
  }

  private static String fullyQualifedName(final String packageName, final String simpleName) {
    return packageName.isEmpty() ? simpleName : packageName + "." + simpleName;
  }

  public List<TypeVariableName> typeVariables() {
    final List<TypeVariableName> variables = new ArrayList<>();
    if (isGeneric) {
      for (final TypeMirror argument : typeArguments) {
        final TypeVariable typeVariable = (TypeVariable) argument;
        variables.add(TypeVariableName.get(typeVariable));
      }
    }
    return variables;
  }

  public TypeName[] typeArgumentsArray() {
    final List<TypeVariableName> variables = typeVariables();
    return variables.toArray(new TypeName[variables.size()]);
  }
}
