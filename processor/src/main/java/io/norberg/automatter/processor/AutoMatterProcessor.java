package io.norberg.automatter.processor;

import com.google.auto.service.AutoService;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;

import com.squareup.javawriter.JavaWriter;

import org.modeshape.common.text.Inflector;

import java.io.IOException;
import java.util.Collections;
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
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.tools.JavaFileObject;

import io.norberg.automatter.AutoMatter;

import static com.google.common.base.Preconditions.checkArgument;
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

  private Filer filer;
  private Elements elements;
  private Messager messager;
  public static final Inflector INFLECTOR = new Inflector();

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
    writer.emitImports("io.norberg.automatter.AutoMatter");
    writer.emitEmptyLine();
    writer.emitImports("java.util.ArrayList",
                       "java.util.Arrays",
                       "java.util.Collection",
                       "java.util.Collections",
                       "java.util.HashMap",
                       "java.util.Iterator",
                       "java.util.List",
                       "java.util.Map");
    writer.emitEmptyLine();
    writer.emitImports("javax.annotation.Generated");

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

  private void emitConstructors(final JavaWriter writer,
                                final Descriptor descriptor)
      throws IOException {
    emitDefaultConstructor(writer);
    emitCopyValueConstructor(writer, descriptor);
    emitCopyBuilderConstructor(writer, descriptor);
  }

  private void emitFactoryMethods(final JavaWriter writer, final Descriptor descriptor)
      throws IOException {
    emitFromValueFactory(writer, descriptor);
    emitFromBuilderFactory(writer, descriptor);
  }

  private void emitFromValueFactory(final JavaWriter writer, final Descriptor descriptor)
  throws IOException {
    writer.emitEmptyLine();
    writer.beginMethod(descriptor.builderSimpleName, "from", EnumSet.of(STATIC, PUBLIC),
                       descriptor.targetSimpleName, "v");
    writer.emitStatement("return new %s(v)", descriptor.builderSimpleName);
    writer.endMethod();
  }

  private void emitFromBuilderFactory(final JavaWriter writer,
                                      final Descriptor descriptor) throws IOException {
    writer.emitEmptyLine();
    writer.beginMethod(descriptor.builderSimpleName, "from", EnumSet.of(STATIC, PUBLIC),
                       descriptor.builderSimpleName, "v");
    writer.emitStatement("return new %s(v)", descriptor.builderSimpleName);
    writer.endMethod();
  }

  private void emitDefaultConstructor(final JavaWriter writer) throws IOException {
    writer.emitEmptyLine();
    writer.beginConstructor(EnumSet.of(PUBLIC));
    writer.endConstructor();
  }

  private void emitCopyValueConstructor(final JavaWriter writer, final Descriptor descriptor)
      throws IOException {
    writer.emitEmptyLine();
    writer.beginConstructor(EnumSet.of(PRIVATE), descriptor.targetSimpleName, "v");
    for (ExecutableElement field : descriptor.fields) {
      emitCopyValueConstructorFieldCopy(writer, field);
    }
    writer.endConstructor();
  }

  private void emitCopyValueConstructorFieldCopy(final JavaWriter writer,
                                                 final ExecutableElement field) throws IOException {
    final String name = fieldName(field);
    final String copy;
    if (isList(field)) {
      writer.emitStatement("%1$s _%2$s = v.%2$s()", fieldType(writer, field), name);
      copy = constructorListCopy("_" + name, fieldTypeArguments(writer, field));
    } else if (isMap(field)) {
      writer.emitStatement("%1$s _%2$s = v.%2$s()", fieldType(writer, field), name);
      copy = constructorMapCopy("_" + name, fieldTypeArguments(writer, field));
    } else {
      copy = "v." + name + "()";
    }
    writer.emitStatement("this.%s = %s", name, copy);
  }

  private String constructorListCopy(final String original, final String typeArguments) {
    return format("(%1$s == null) ? null : new ArrayList<%2$s>(%1$s)", original, typeArguments);
  }

  private String constructorMapCopy(final String original, final String typeArguments) {
    return format("(%1$s == null) ? null : new HashMap<%2$s>(%1$s)", original, typeArguments);
  }

  private void emitCopyBuilderConstructor(final JavaWriter writer,
                                          final Descriptor descriptor) throws IOException {
    writer.emitEmptyLine();
    writer.beginConstructor(EnumSet.of(PRIVATE), descriptor.builderSimpleName, "v");
    for (ExecutableElement field : descriptor.fields) {
      emitCopyBuilderConstructorFieldCopy(writer, field);
    }
    writer.endConstructor();
  }

  private void emitCopyBuilderConstructorFieldCopy(final JavaWriter writer,
                                                   final ExecutableElement field)
      throws IOException {
    final String name = fieldName(field);
    final String copy;
    if (isList(field)) {
      copy = constructorListCopy("v." + name, fieldTypeArguments(writer, field));
    } else if (isMap(field)) {
      copy = constructorMapCopy("v." + name, fieldTypeArguments(writer, field));
    } else {
      copy = "v." + name;
    }
    writer.emitStatement("this.%s = %s", name, copy);
  }

  private boolean isList(final ExecutableElement field) {
    final String returnType = field.getReturnType().toString();
    return returnType.startsWith("java.util.List<");
  }

  private boolean isMap(final ExecutableElement field) {
    final String returnType = field.getReturnType().toString();
    return returnType.startsWith("java.util.Map<");
  }

  private String fullyQualifedName(final String packageName, final String simpleName) {
    return packageName.isEmpty()
           ? simpleName
           : packageName + "." + simpleName;
  }

  private void emitFields(final JavaWriter writer, final Descriptor descriptor) throws IOException {
    writer.emitEmptyLine();
    for (ExecutableElement field : descriptor.fields) {
      writer.emitField(fieldType(writer, field), fieldName(field), EnumSet.of(PRIVATE));
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
      parameters.add("@AutoMatter.Field(\"" + fieldName(field) + "\") " + fieldType(writer, field));
      parameters.add(fieldName(field));
    }
    writer.beginConstructor(EnumSet.of(PRIVATE), parameters, null);
    for (ExecutableElement field : fields) {
      emitNullCheck(writer, "", field);
    }
    for (ExecutableElement field : fields) {
      writer.emitStatement("this.%s = %s",
                           fieldName(field), valueConstructorFieldCopy(writer, field));
    }
    writer.endConstructor();
  }

  private String valueConstructorFieldCopy(final JavaWriter writer, final ExecutableElement field) {
    if (isList(field)) {
      return format("(%1$s != null) ? %1$s : Collections.<%2$s>emptyList()",
                    fieldName(field), fieldTypeArguments(writer, field));
    } else if (isMap(field)) {
      return format("(%1$s != null) ? %1$s : Collections.<%2$s>emptyMap()",
                    fieldName(field), fieldTypeArguments(writer, field));
    } else {
      return fieldName(field);
    }
  }

  private void emitNullCheck(final JavaWriter writer, final String scope,
                             final ExecutableElement field)
      throws IOException {
    if (!shouldEnforceNonNull(field)) {
      return;
    }
    final String variable = scope.isEmpty()
                            ? fieldName(field)
                            : scope + "." + fieldName(field);
    final String name = fieldName(field);
    emitNullCheck(writer, variable, name);
  }

  private void emitNullCheck(final JavaWriter writer, final String variable, final String name)
      throws IOException {
    writer.beginControlFlow("if (" + variable + " == null)");
    emitNPE(writer, name);
    writer.endControlFlow();
  }

  private void emitValueFields(final JavaWriter writer, final List<ExecutableElement> fields)
      throws IOException {
    writer.emitEmptyLine();
    for (ExecutableElement field : fields) {
      writer.emitField(fieldType(writer, field), fieldName(field), EnumSet.of(PRIVATE, FINAL));
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
    writer.beginMethod(fieldType(writer, field), fieldName(field), EnumSet.of(PUBLIC));
    writer.emitStatement("return %s", unmodifiableValueField(field));
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
      parameters.add(buildFieldCopy(writer, field));
    }
    writer.emitStatement("return new Value(%s)", Joiner.on(", ").join(parameters));
    writer.endMethod();
  }

  private String buildFieldCopy(final JavaWriter writer, final ExecutableElement field) {
    if (isList(field)) {
      return format("(%1$s != null) ? new ArrayList<%2$s>(%1$s) : Collections.<%2$s>emptyList()",
                    fieldName(field), fieldTypeArguments(writer, field));
    }
    if (isMap(field)) {
      return format("(%1$s != null) ? new HashMap<%2$s>(%1$s) : Collections.<%2$s>emptyMap()",
                    fieldName(field), fieldTypeArguments(writer, field));
    } else {
      return fieldName(field);
    }
  }

  private void emitAccessors(final JavaWriter writer,
                             final Descriptor descriptor) throws IOException {
    for (final ExecutableElement field : descriptor.fields) {
      emitGetter(writer, field);
      if (isList(field)) {
        emitListSetters(writer, descriptor.builderSimpleName, field);
      } else if (isMap(field)) {
        emitMapSetters(writer, descriptor.builderSimpleName, field);
      } else {
        emitRawSetter(writer, descriptor.builderSimpleName, field);
      }
    }
  }

  private void emitMapSetters(final JavaWriter writer, final String builderName,
                              final ExecutableElement field) throws IOException {
    emitMapSetter(writer, builderName, field);
    emitMapEntriesSetters(writer, builderName, field);
    emitMapEntryPutter(writer, builderName, field);
  }

  private void emitMapSetter(final JavaWriter writer, final String builderName,
                             final ExecutableElement field) throws IOException {
    final String name = fieldName(field);
    final String type = fieldTypeArguments(writer, field);

    writer.emitEmptyLine();
    writer.beginMethod(builderName, name, EnumSet.of(PUBLIC),
                       fieldType(writer, field), name);

    // Null checks
    final String entry = variableName("entry", name);
    emitNullCheck(writer, name, name);
    writer.beginControlFlow("for (Map.Entry<" + type + "> " + entry + " : " + name + ".entrySet())");
    emitNullCheck(writer, entry + ".getKey()", name + ": null key");
    emitNullCheck(writer, entry + ".getValue()", name + ": null value");
    writer.endControlFlow();

    // Copy
    writer.emitStatement("this.%1$s = new HashMap<%2$s>(%1$s)", name, type);

    writer.emitStatement("return this");
    writer.endMethod();
  }

  private void emitMapEntriesSetters(final JavaWriter writer, final String builderName,
                                     final ExecutableElement field) throws IOException {
    for (int i = 1; i <= 5; i++) {
      emitMapEntriesSetter(writer, builderName, field, i);
    }
  }

  private void emitMapEntriesSetter(final JavaWriter writer, final String builderName,
                                    final ExecutableElement field, final int entries)
      throws IOException {
    checkArgument(entries > 0, "entries");
    final String name = fieldName(field);
    final String keyName = "k" + entries;
    final String valueName = "v" + entries;
    final String keyType = keyType(writer, field);
    final String valueType = valueType(writer, field);

    final List<String> parameters = Lists.newArrayList();
    for (int i = 1; i < entries + 1; i++) {
      parameters.add(keyType);
      parameters.add("k" + i);
      parameters.add(valueType);
      parameters.add("v" + i);
    }
    writer.emitEmptyLine();
    writer.beginMethod(builderName, name, EnumSet.of(PUBLIC), parameters,
                       Collections.<String>emptyList());

    // Recursion
    if (entries > 1) {
      final List<String> recursionParameters = Lists.newArrayList();
      for (int i = 1; i < entries; i++) {
        recursionParameters.add("k" + i);
        recursionParameters.add("v" + i);
      }
      writer.emitStatement("%s(%s)", name, Joiner.on(", ").join(recursionParameters));
    }

    // Null checks
    emitNullCheck(writer, keyName, name + ": " + keyName);
    emitNullCheck(writer, valueName, name + ": " + valueName);

    // Map instantiation
    if (entries == 1) {
      writer.emitStatement("%s = new HashMap<%s>()", name, fieldTypeArguments(writer, field));
    }

    // Put
    writer.emitStatement("%s.put(%s, %s)", name, keyName, valueName);

    writer.emitStatement("return this");
    writer.endMethod();
  }

  private String keyType(final JavaWriter writer, final ExecutableElement field) {
    checkArgument(isMap(field), "field is not a Map");
    final List<String> arguments = typeArguments(writer, field);
    return arguments.get(0);
  }

  private String valueType(final JavaWriter writer, final ExecutableElement field) {
    checkArgument(isMap(field), "field is not a Map");
    final List<String> arguments = typeArguments(writer, field);
    return arguments.get(1);
  }

  private void emitMapEntryPutter(final JavaWriter writer, final String builderName,
                                  final ExecutableElement field) throws IOException {

    final String name = fieldName(field);
    final String singular = singular(name);
    if (singular == null) {
      return;
    }
    final String keyType = keyType(writer, field);
    final String valueType = valueType(writer, field);

    writer.emitEmptyLine();
    writer.beginMethod(builderName, singular, EnumSet.of(PUBLIC),
                       keyType, "key", valueType, "value");

    // Null checks
    emitNullCheck(writer, "key", singular + ": key");
    emitNullCheck(writer, "value", singular + ": value");
    emitLazyMapFieldInstantiation(writer, field);

    // Put
    writer.emitStatement("%s.put(key, value)", name);

    writer.emitStatement("return this");
    writer.endMethod();
  }

  private void emitListSetters(final JavaWriter writer, final String builderName,
                               final ExecutableElement field) throws IOException {
    emitListIterableSetter(writer, builderName, field);
    emitListVarArgSetter(writer, builderName, field);
    emitListItemSingleAdder(writer, builderName, field);
  }

  private void emitListItemSingleAdder(final JavaWriter writer, final String builderName,
                                       final ExecutableElement field) throws IOException {
    final String name = fieldName(field);
    final String singular = singular(name);
    if (singular == null) {
      return;
    }
    writer.emitEmptyLine();
    writer.beginMethod(builderName, singular, EnumSet.of(PUBLIC),
                       fieldTypeArguments(writer, field), singular);
    emitNullCheck(writer, singular, singular);
    emitLazyListFieldInstantiation(writer, field);
    writer.emitStatement("%s.add(%s)", fieldName(field), singular);
    writer.emitStatement("return this", fieldName(field));
    writer.endMethod();
  }

  private String singular(final String name) {
    final String singular = INFLECTOR.singularize(name);
    return name.equals(singular) ? null : singular;
  }

  private void emitListIterableSetter(final JavaWriter writer, final String builderName,
                                      final ExecutableElement field) throws IOException {
    writer.emitEmptyLine();
    final String name = fieldName(field);
    final String type = fieldTypeArguments(writer, field);
    final String iterableType = "Iterable<" + type + ">";
    writer.beginMethod(builderName, name, EnumSet.of(PUBLIC), iterableType, name);

    // Null check
    writer.beginControlFlow("if (" + name + " == null)");
    if (shouldEnforceNonNull(field)) {
      emitNPE(writer, name);
    } else {
      writer.emitStatement("this.%s = null", name);
    }

    final String item = variableName("item", name);

    // Collection optimization
    final String collection = variableName("collection", name);
    writer.nextControlFlow("else if (" + name + " instanceof Collection)");
    writer.emitStatement("Collection<%1$s> %2$s = (Collection<%1$s>) %3$s", type, collection, name);
    beginFor(writer, type, item, collection);
    emitNullCheck(writer, item, name + ": null item");
    endFor(writer);
    writer.emitStatement("this.%1$s = new ArrayList<%2$s>(collection)", name, type);

    // Iterator fallback
    final String iterator = variableName("iterator", name);
    writer.nextControlFlow("else");
    writer.emitStatement("this.%s = new ArrayList<%s>()", name, type);
    writer.emitStatement("Iterator<%s> %s = %s.iterator()", type, iterator, name);

    writer.beginControlFlow("while (" + iterator + ".hasNext())");
    writer.emitStatement("%s %s = %s.next()", type, item, iterator);
    emitNullCheck(writer, item, name + ": null item");
    writer.emitStatement("this.%s.add(%s)", name, item);
    writer.endControlFlow();
    writer.endControlFlow();

    writer.emitStatement("return this");
    writer.endMethod();
  }

  private String variableName(final String name, final String... scope) {
    return variableName(name, ImmutableSet.copyOf(scope));
  }

  private String variableName(final String name, final Set<String> scope) {
    if (!scope.contains(name)) {
      return name;
    }
    return variableName("_" + name, scope);
  }

  private void endFor(final JavaWriter writer) throws IOException {
    writer.endControlFlow();
  }

  private void beginFor(final JavaWriter writer, final String type, final String item,
                        final String collection) throws IOException {
    writer.beginControlFlow("for (" + type + " " + item + " : " + collection + ")");
  }

  private void emitNPE(final JavaWriter writer, final String name) throws IOException {
    writer.emitStatement("throw new NullPointerException(\"%s\")", name);
  }

  private String fieldTypeArguments(final JavaWriter writer, final ExecutableElement field) {
    return Joiner.on(",").join(typeArguments(writer, field));
  }

  private List<String> typeArguments(final JavaWriter writer, final ExecutableElement field) {
    final DeclaredType type = (DeclaredType) field.getReturnType();
    final List<String> arguments = Lists.newArrayList();
    for (TypeMirror argument : type.getTypeArguments()) {
      arguments.add(writer.compressType(argument.toString()));
    }
    return arguments;
  }

  private void emitListVarArgSetter(final JavaWriter writer, final String builderName,
                                    final ExecutableElement field) throws IOException {
    final String type = fieldTypeArguments(writer, field);
    final String vararg = type + "...";
    writer.emitEmptyLine();
    writer.beginMethod(builderName, fieldName(field), EnumSet.of(PUBLIC),
                       vararg, fieldName(field));
    emitNullCheck(writer, "", field);
    writer.emitStatement("return %1$s(Arrays.asList(%1$s))", fieldName(field));
    writer.endMethod();
  }

  private void emitRawSetter(final JavaWriter writer, final String builderName,
                             final ExecutableElement field)
      throws IOException {
    writer.emitEmptyLine();
    writer.beginMethod(builderName, fieldName(field), EnumSet.of(PUBLIC),
                       fieldType(writer, field), fieldName(field));
    emitNullCheck(writer, "", field);
    writer.emitStatement("this.%1$s = %1$s", fieldName(field));
    writer.emitStatement("return this");
    writer.endMethod();
  }

  private void emitGetter(final JavaWriter writer,
                          final ExecutableElement field)
      throws IOException {
    writer.emitEmptyLine();
    writer.beginMethod(fieldType(writer, field), fieldName(field), EnumSet.of(PUBLIC));
    if (isList(field)) {
      emitListGetterBody(writer, field);
    } else if (isMap(field)) {
      emitMapGetterBody(writer, field);
    } else {
      emitDefaultGetterBody(writer, field);
    }
    writer.endMethod();
  }

  private void emitListGetterBody(final JavaWriter writer, final ExecutableElement field)
      throws IOException {
    emitLazyListFieldInstantiation(writer, field);
    writer.emitStatement("return %s", fieldName(field));
  }

  private void emitLazyListFieldInstantiation(final JavaWriter writer,
                                              final ExecutableElement field) throws IOException {
    final String name = fieldName(field);
    writer.beginControlFlow("if (" + name + " == null)");
    writer.emitStatement("%s = new ArrayList<%s>()", name, fieldTypeArguments(writer, field));
    writer.endControlFlow();
  }

  private void emitMapGetterBody(final JavaWriter writer, final ExecutableElement field)
      throws IOException {
    emitLazyMapFieldInstantiation(writer, field);
    writer.emitStatement("return %s", fieldName(field));
  }

  private void emitLazyMapFieldInstantiation(final JavaWriter writer,
                                             final ExecutableElement field) throws IOException {
    final String name = fieldName(field);
    writer.beginControlFlow("if (" + name + " == null)");
    writer.emitStatement("%s = new HashMap<%s>()", name, fieldTypeArguments(writer, field));
    writer.endControlFlow();
  }

  private void emitDefaultGetterBody(final JavaWriter writer, final ExecutableElement field)
      throws IOException {
    writer.emitStatement("return %s", fieldName(field));
  }

  private String unmodifiableValueField(final ExecutableElement field) {
    if (isList(field)) {
      return format("Collections.unmodifiableList(%s)", fieldName(field));
    } else if (isMap(field)) {
      return format("Collections.unmodifiableMap(%s)", fieldName(field));
    } else {
      return fieldName(field);
    }
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

  private String fieldType(final JavaWriter writer, final ExecutableElement field) {
    return writer.compressType(field.getReturnType().toString());
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
    private final String builderSimpleName;
    private final boolean isPublic;

    private Descriptor(final Element element) throws AutoMatterProcessorException {
      this.isPublic = element.getModifiers().contains(PUBLIC);
      this.packageName = elements.getPackageOf(element).getQualifiedName().toString();
      this.targetSimpleName = nestedName(element);
      final String targetName = element.getSimpleName().toString();
      this.builderFullName = fullyQualifedName(packageName, targetName + "Builder");
      this.builderSimpleName = simpleName(builderFullName);

      if (!element.getKind().isInterface()) {
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
