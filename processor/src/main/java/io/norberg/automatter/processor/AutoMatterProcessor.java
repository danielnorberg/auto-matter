package io.norberg.automatter.processor;

import com.google.auto.service.AutoService;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;

import com.squareup.javawriter.JavaWriter;

import java.io.IOException;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Generated;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.tools.JavaFileObject;

import io.norberg.automatter.AutoMatter;

import static java.lang.String.format;
import static java.util.Collections.reverse;
import static javax.lang.model.element.ElementKind.PACKAGE;
import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PRIVATE;
import static javax.lang.model.element.Modifier.PUBLIC;
import static javax.lang.model.element.Modifier.STATIC;
import static javax.lang.model.type.TypeKind.ARRAY;
import static javax.tools.Diagnostic.Kind.ERROR;

/**
 * An annotation processor that takes a value type defined as an interface with getter methods and
 * materializes it, generating a concrete builder and value class.
 */
@AutoService(Processor.class)
public final class AutoMatterProcessor extends AbstractProcessor {

  private static final String JAVA_LANG = "java.lang.";

  private Filer filer;
  private Elements elements;
  private Messager messager;

  @Override
  public synchronized void init(final ProcessingEnvironment processingEnv) {
    super.init(processingEnv);
    filer = processingEnv.getFiler();
    elements = processingEnv.getElementUtils();
    this.messager = processingEnv.getMessager();
  }

  @Override
  public boolean process(final Set<? extends TypeElement> annotations,
                         final RoundEnvironment env) {
    final Set<? extends Element> elements = env.getElementsAnnotatedWith(AutoMatter.class);
    for (Element element : elements) {
      try {
        writeBuilder(element);
      } catch (IOException e) {
        messager.printMessage(ERROR, e.getMessage());
      } catch (AutoMatterProcessorException e) {
        e.print(messager);
      }
    }
    return false;
  }

  private void writeBuilder(final Element element) throws IOException,
                                                          AutoMatterProcessorException {
    final Descriptor d = new Descriptor(element);

    final JavaFileObject sourceFile = filer.createSourceFile(d.builderFullName);
    final JavaWriter writer = new JavaWriter(sourceFile.openWriter());

    writer.emitPackage(d.packageName);
    writer.emitImports("io.norberg.automatter.AutoMatter",
                       "java.util.Arrays",
                       "javax.annotation.Generated");

    writer.emitEmptyLine();
    writer.emitAnnotation(
        Generated.class,
        ImmutableMap.of("value", "\"" + AutoMatterProcessor.class.getName() + "\""));
    writer.beginType(d.builderSimpleName, "class", d.isPublic
                                                   ? EnumSet.of(PUBLIC, FINAL)
                                                   : EnumSet.of(FINAL));
    emitFields(writer, d);
    emitConstructors(writer, d);
    emitAccessors(writer, d);
    emitBuild(writer, d);
    emitFactoryMethods(writer, d);
    emitValue(writer, d);

    writer.endType();
    writer.close();
  }

  private EnumSet<Modifier> maybePublic(final boolean isPublic, final Modifier modifier,
                                        final Modifier... modifiers) {
    return maybePublic(isPublic, EnumSet.of(modifier, modifiers));
  }

  private EnumSet<Modifier> maybePublic(final boolean isPublic, final EnumSet<Modifier> modifiers) {
    if (isPublic) {
      modifiers.add(PUBLIC);
    }
    return modifiers;
  }

  private void emitConstructors(final JavaWriter writer,
                                final Descriptor descriptor)
      throws IOException {
    emitDefaultConstructor(writer, descriptor);
    emitCopyValueConstructor(writer, descriptor);
    emitCopyBuilderConstructor(writer, descriptor);
  }

  private void emitFactoryMethods(final JavaWriter writer, final Descriptor d) throws IOException {
    emitFromValueFactory(writer, d);
    emitFromBuilderFactory(writer, d);
  }

  private void emitFromValueFactory(final JavaWriter writer, final Descriptor d)
      throws IOException {
    writer.emitEmptyLine();
    writer.beginMethod(d.builderSimpleName, "from", EnumSet.of(STATIC, PUBLIC),
                       d.targetSimpleName, "v");
    writer.emitStatement("return new %s(v)", d.builderSimpleName);
    writer.endMethod();
  }

  private void emitFromBuilderFactory(final JavaWriter writer,
                                      final Descriptor d) throws IOException {
    writer.emitEmptyLine();
    writer.beginMethod(d.builderSimpleName, "from", EnumSet.of(STATIC, PUBLIC),
                       d.builderSimpleName, "v");
    writer.emitStatement("return new %s(v)", d.builderSimpleName);
    writer.endMethod();
  }

  private void emitDefaultConstructor(final JavaWriter writer,
                                      final Descriptor descriptor) throws IOException {
    writer.emitEmptyLine();
    writer.beginConstructor(EnumSet.of(PUBLIC));
    writer.endConstructor();
  }

  private void emitCopyValueConstructor(final JavaWriter writer,
                                        final Descriptor descriptor) throws IOException {
    writer.emitEmptyLine();
    writer.beginConstructor(EnumSet.of(PRIVATE), descriptor.targetSimpleName, "v");
    for (ExecutableElement field : descriptor.fields) {
      writer.emitStatement("this.%1$s = v.%1$s()", fieldName(field));
    }
    writer.endConstructor();
  }

  private void emitCopyBuilderConstructor(final JavaWriter writer,
                                          final Descriptor descriptor) throws IOException {
    writer.emitEmptyLine();
    writer.beginConstructor(EnumSet.of(PRIVATE), descriptor.builderSimpleName, "v");
    for (ExecutableElement field : descriptor.fields) {
      writer.emitStatement("this.%1$s = v.%1$s", fieldName(field));
    }
    writer.endConstructor();
  }

  private String fullyQualifedName(final String packageName, final String simpleName) {
    return packageName.isEmpty()
           ? simpleName
           : packageName + "." + simpleName;
  }

  private void emitFields(final JavaWriter writer, final Descriptor descriptor)
  throws IOException {
    writer.emitEmptyLine();
    for (ExecutableElement field : descriptor.fields) {
      writer.emitField(fieldType(field), fieldName(field), EnumSet.of(PRIVATE));
    }
  }

  private void emitValue(final JavaWriter writer,
                         final Descriptor descriptor)
      throws IOException, AutoMatterProcessorException {
    writer.emitEmptyLine();
    writer.beginType("Value", "class", EnumSet.of(PRIVATE, STATIC, FINAL),
                     null, descriptor.targetSimpleName);
    emitValueFields(writer, descriptor.fields);
    emitValueConstructor(writer, descriptor.fields);
    emitValueGetters(writer, descriptor.fields);
    emitValueToBuilder(writer, descriptor);
    emitValueEquals(writer, descriptor);
    emitValueHashCode(writer, descriptor.fields);
    emitValueToString(writer, descriptor.fields, descriptor.targetSimpleName);
    writer.endType();
  }

  private void emitValueToBuilder(final JavaWriter writer,
                                  final Descriptor descriptor) throws IOException {
    writer.emitEmptyLine();
    // Always emit toBuilder, but only annotate it with @Override if the target asked for it.
    if (descriptor.toBuilder) {
      writer.emitAnnotation(Override.class);
    }
    writer.beginMethod(descriptor.builderFullName, "builder", EnumSet.of(PUBLIC));
    writer.emitStatement("return new %s(this)", descriptor.builderSimpleName);
    writer.endMethod();
  }

  private void emitValueConstructor(final JavaWriter writer, final List<ExecutableElement> fields)
      throws IOException {
    writer.emitEmptyLine();
    final List<String> parameters = Lists.newArrayList();
    for (ExecutableElement field : fields) {
      parameters.add("@AutoMatter.Field(\"" + fieldName(field) + "\") " + fieldType(field));
      parameters.add(fieldName(field));
    }
    writer.beginConstructor(EnumSet.of(PRIVATE), parameters, null);
    for (ExecutableElement field : fields) {
      writer.emitStatement("this.%1$s = %1$s", fieldName(field));
    }
    for (ExecutableElement field : fields) {
      emitNullCheck(writer, "this", field);
    }
    writer.endConstructor();
  }

  private void emitNullCheck(final JavaWriter writer, final String scope, final ExecutableElement field)
      throws IOException {
    if (!shouldEnforceNonNull(field)) {
      return;
    }
    final String variable = scope.isEmpty()
                            ? fieldName(field)
                            : scope + "." + fieldName(field);
    writer.beginControlFlow("if (" + variable + " == null)");
    writer.emitStatement("throw new NullPointerException(\"%s\")", fieldName(field));
    writer.endControlFlow();
  }

  private void emitValueFields(final JavaWriter writer, final List<ExecutableElement> fields)
      throws IOException {
    writer.emitEmptyLine();
    for (ExecutableElement field : fields) {
      writer.emitField(fieldType(field), fieldName(field), EnumSet.of(PRIVATE, FINAL));
    }
  }

  private void emitValueGetters(final JavaWriter writer, final List<ExecutableElement> fields)
      throws IOException {
    for (ExecutableElement field : fields) {
      emitValueGetter(writer, field);
    }
  }

  private void emitValueGetter(final JavaWriter writer, final ExecutableElement field)
      throws IOException {
    writer.emitEmptyLine();
    emitValueGetterNullableAnnotation(writer, field);
    writer.emitAnnotation("AutoMatter.Field");
    writer.emitAnnotation(Override.class);
    writer.beginMethod(fieldType(field), fieldName(field), EnumSet.of(PUBLIC));
    writer.emitStatement("return %s", fieldName(field));
    writer.endMethod();
  }

  private void emitValueGetterNullableAnnotation(final JavaWriter writer,
                                                 final ExecutableElement field) throws IOException {
    if (isPrimitive(field)) {
      return;
    }
    final AnnotationMirror nullableAnnotation = nullableAnnotation(field);
    if (nullableAnnotation != null) {
      final Element annotationElement = nullableAnnotation.getAnnotationType().asElement();
      final String annotationString = annotationElement.asType().toString();
      final Map<String, String> values = annotationValues(nullableAnnotation);
      writer.emitAnnotation(annotationString, values);
    }
  }

  private boolean isPrimitive(final ExecutableElement field) {
    return field.getReturnType().getKind().isPrimitive();
  }

  private void emitValueEquals(final JavaWriter writer, final Descriptor descriptor)
      throws IOException, AutoMatterProcessorException {

    writer.emitEmptyLine();
    writer.emitAnnotation(Override.class);
    writer.beginMethod("boolean", "equals", EnumSet.of(PUBLIC), "Object", "o");

    writer.beginControlFlow("if (this == o)");
    writer.emitStatement("return true");
    writer.endControlFlow();

    writer.beginControlFlow("if (!(o instanceof " + descriptor.targetSimpleName + "))");
    writer.emitStatement("return false");
    writer.endControlFlow();

    if (!descriptor.fields.isEmpty()) {
      writer.emitEmptyLine();
      writer.emitStatement("final %1$s that = (%1$s) o", descriptor.targetSimpleName);
      writer.emitEmptyLine();
      for (ExecutableElement field : descriptor.fields) {
        writer.beginControlFlow(fieldNotEqualCondition(field));
        writer.emitStatement("return false");
        writer.endControlFlow();
      }
    }

    writer.emitEmptyLine();
    writer.emitStatement("return true");
    writer.endMethod();
  }

  private String fieldNotEqualCondition(final ExecutableElement field)
      throws AutoMatterProcessorException {
    final String name = field.getSimpleName().toString();
    final TypeMirror type = field.getReturnType();
    switch (type.getKind()) {
      case LONG:
      case INT:
      case BOOLEAN:
      case BYTE:
      case SHORT:
      case CHAR:
        return format("if (%1$s != that.%1$s())", name);
      case FLOAT:
        return format("if (Float.compare(that.%1$s(), %1$s) != 0)", name);
      case DOUBLE:
        return format("if (Double.compare(that.%1$s(), %1$s) != 0)", name);
      case ARRAY:
        return format("if (!Arrays.equals(%1$s, that.%1$s()))", name);
      case DECLARED:
        return format("if (%1$s != null ? !%1$s.equals(that.%1$s()) : that.%1$s() != null)", name);
      case ERROR:
        throw fail("Cannot resolve type, might be missing import: " + type, field);
      default:
        throw fail("Unsupported type: " + type, field);
    }
  }

  private void emitValueHashCode(final JavaWriter writer, final List<ExecutableElement> fields)
      throws IOException, AutoMatterProcessorException {
    writer.emitEmptyLine();
    writer.emitAnnotation(Override.class);
    writer.beginMethod("int", "hashCode", EnumSet.of(PUBLIC));
    writer.emitStatement("int result = 1");
    writer.emitStatement("long temp");
    for (ExecutableElement field : fields) {
      final String name = field.getSimpleName().toString();
      final TypeMirror type = field.getReturnType();
      switch (type.getKind()) {
        case LONG:
          writer.emitStatement("result = 31 * result + (int) (%1$s ^ (%1$s >>> 32))", name);
          break;
        case INT:
          writer.emitStatement("result = 31 * result + %s", name);
          break;
        case BOOLEAN:
          writer.emitStatement("result = 31 * result + (%s ? 1231 : 1237)", name);
          break;
        case BYTE:
        case SHORT:
        case CHAR:
          writer.emitStatement("result = 31 * result + (int) %s", name);
          break;
        case FLOAT:
          writer.emitStatement("result = 31 * result + " +
                               "(%1$s != +0.0f ? Float.floatToIntBits(%1$s) : 0)", name);
          break;
        case DOUBLE:
          writer.emitStatement("temp = Double.doubleToLongBits(%s)", name);
          writer.emitStatement("result = 31 * result + (int) (temp ^ (temp >>> 32))");
          break;
        case ARRAY:
          writer.emitStatement("result = 31 * result + " +
                               "(%1$s != null ? Arrays.hashCode(%1$s) : 0)", name);
          break;
        case DECLARED:
          writer.emitStatement("result = 31 * result + (%1$s != null ? %1$s.hashCode() : 0)", name);
          break;
        case ERROR:
          throw fail("Cannot resolve type, might be missing import: " + type, field);
        default:
          throw fail("Unsupported type: " + type, field);
      }
    }
    writer.emitStatement("return result");
    writer.endMethod();
  }

  private void emitValueToString(final JavaWriter writer, final List<ExecutableElement> fields,
                                 final String targetName)
  throws IOException {
    writer.emitEmptyLine();
    writer.emitAnnotation(Override.class);
    writer.beginMethod("String", "toString", EnumSet.of(PUBLIC));
    emitToStringStatement(writer, fields, targetName);
    writer.endMethod();
  }

  private void emitToStringStatement(final JavaWriter writer, final List<ExecutableElement> fields,
                                     final String targetName) throws IOException {
    final StringBuilder builder = new StringBuilder();
    builder.append("return \"").append(targetName).append("{\" + \n");
    boolean first = true;
    for (ExecutableElement field : fields) {
      final String comma = first ? "" : ", ";
      final String name = fieldName(field);
      if (field.getReturnType().getKind() == ARRAY) {
        builder.append(format("\"%1$s%2$s=\" + Arrays.toString(%2$s) +\n", comma, name));
      } else {
        builder.append(format("\"%1$s%2$s=\" + %2$s +\n", comma, name));
      }
      first = false;
    }
    builder.append("'}'");
    writer.emitStatement(builder.toString());
  }

  private void emitBuild(final JavaWriter writer,
                         final Descriptor descriptor) throws IOException {
    writer.emitEmptyLine();
    writer.beginMethod(descriptor.targetSimpleName, "build", EnumSet.of(PUBLIC));
    final List<String> parameters = Lists.newArrayList();
    for (ExecutableElement field : descriptor.fields) {
      parameters.add(field.getSimpleName().toString());
    }
    writer.emitStatement("return new Value(%s)", Joiner.on(", ").join(parameters));
    writer.endMethod();
  }

  private void emitAccessors(final JavaWriter writer,
                             final Descriptor descriptor) throws IOException {
    for (final ExecutableElement field : descriptor.fields) {
      emitGetter(writer, field);
      emitSetter(writer, descriptor.builderSimpleName, field);
    }
  }

  private void emitSetter(final JavaWriter writer, final String builderName,
                          final ExecutableElement field)
      throws IOException {
    writer.emitEmptyLine();
    writer.beginMethod(builderName, fieldName(field), EnumSet.of(PUBLIC),
                       fieldType(field), fieldName(field));
    emitNullCheck(writer, "", field);
    writer.emitStatement("this.%1$s = %1$s", fieldName(field));
    writer.emitStatement("return this");
    writer.endMethod();
  }

  private void emitNullCheck(final ExecutableElement field) {

  }

  private void emitGetter(final JavaWriter writer,
                          final ExecutableElement field)
      throws IOException {
    writer.emitEmptyLine();
    writer.beginMethod(fieldType(field), fieldName(field), EnumSet.of(PUBLIC));
    writer.emitStatement("return %s", fieldName(field));
    writer.endMethod();
  }

  private Map<String, String> annotationValues(final AnnotationMirror annotation) {
    final Map<String, String> values = new LinkedHashMap<String, String>();
    for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry :
        annotation.getElementValues().entrySet()) {
      final String key = entry.getKey().getSimpleName().toString();
      final String value = entry.getValue().toString();
      values.put(key, value);
    }
    return values;
  }

  private String fieldName(final ExecutableElement field) {
    return field.getSimpleName().toString();
  }

  private String fieldType(final ExecutableElement field) {
    final String name = field.getReturnType().toString();
    if (name.startsWith(JAVA_LANG)) {
      return name.substring(JAVA_LANG.length());
    } else {
      return name;
    }
  }

  private static String simpleName(String fullyQualifiedName) {
    int lastDot = fullyQualifiedName.lastIndexOf('.');
    return fullyQualifiedName.substring(lastDot + 1, fullyQualifiedName.length());
  }

  @Override
  public Set<String> getSupportedAnnotationTypes() {
    return ImmutableSet.of(AutoMatter.class.getName());
  }

  private class Descriptor {

    private final List<ExecutableElement> fields;
    private final boolean toBuilder;
    private final String builderFullName;
    private final String packageName;
    private final String targetSimpleName;
    private final String targetFullName;
    private final String builderSimpleName;
    private final boolean isPublic;

    private Descriptor(final Element element) throws AutoMatterProcessorException {
      this.isPublic = element.getModifiers().contains(PUBLIC);
      this.packageName = elements.getPackageOf(element).getQualifiedName().toString();
      this.targetSimpleName = nestedName(element);
      this.targetFullName = fullyQualifedName(packageName, targetSimpleName);
      final String targetName = element.getSimpleName().toString();
      this.builderFullName = fullyQualifedName(packageName, targetName + "Builder");
      this.builderSimpleName = simpleName(builderFullName);

      if(!element.getKind().isInterface()) {
        error("@AutoMatter target must be an interface", element);
      }

      final ImmutableList.Builder<ExecutableElement> fields = ImmutableList.builder();
      boolean toBuilder = false;
      for (final Element member : element.getEnclosedElements()) {
        if (member.getKind().equals(ElementKind.METHOD)) {
          final ExecutableElement executable = (ExecutableElement) member;
          if (executable.getModifiers().contains(STATIC)) {
            continue;
          }
          if (executable.getSimpleName().toString().equals("builder")) {
            final String type = executable.getReturnType().toString();
            if (!type.equals(builderSimpleName) && !type.equals(builderFullName)) {
              throw fail("builder() return type must be " + builderSimpleName, element);
            }
            toBuilder = true;
            continue;
          }
          fields.add(executable);
        }
      }
      this.fields = fields.build();
      this.toBuilder = toBuilder;
    }

    private String nestedName(final Element element) {
      final List<Element> classes = enclosingClasses(element);
      final List<String> names = Lists.newArrayList();
      for (Element cls : classes) {
        names.add(cls.getSimpleName().toString());
      }
      return Joiner.on('.').join(names);
    }

    private List<Element> enclosingClasses(final Element element) {
      final List<Element> classes = Lists.newArrayList();
      Element e = element;
      while (e.getKind() != PACKAGE) {
        classes.add(e);
        e = e.getEnclosingElement();
      }
      reverse(classes);
      return classes;
    }
  }

  private boolean shouldEnforceNonNull(final ExecutableElement field) {
    return !isPrimitive(field) && !isNullableAnnotated(field);
  }

  private boolean isNullableAnnotated(final ExecutableElement field) {
    return nullableAnnotation(field) != null;
  }

  private AnnotationMirror nullableAnnotation(final ExecutableElement field) {
    for (AnnotationMirror annotation : field.getAnnotationMirrors()) {
      if (annotation.getAnnotationType().asElement().getSimpleName().contentEquals("Nullable")) {
        return annotation;
      }
    }
    return null;
  }

  private AutoMatterProcessorException fail(final String msg, final Element element)
      throws AutoMatterProcessorException {
    throw new AutoMatterProcessorException(msg, element);
  }

  private void error(final String s, final Element element) {
    messager.printMessage(ERROR, s, element);
  }

  @Override
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.latestSupported();
  }
}
