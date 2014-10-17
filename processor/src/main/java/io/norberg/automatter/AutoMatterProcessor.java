package io.norberg.automatter;

import com.google.auto.service.AutoService;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;

import com.squareup.javawriter.JavaWriter;

import java.io.IOException;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Generated;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.JavaFileObject;

import static javax.lang.model.SourceVersion.RELEASE_6;
import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PRIVATE;
import static javax.lang.model.element.Modifier.PUBLIC;
import static javax.lang.model.element.Modifier.STATIC;
import static javax.tools.Diagnostic.Kind.ERROR;

/**
 * An annotation processor that takes a value type defined as an interface with getter methods and
 * materializes it, generating a concrete builder and value class.
 */
@AutoService(Processor.class)
@SupportedSourceVersion(RELEASE_6)
public final class AutoMatterProcessor extends AbstractProcessor {

  private static final String JAVA_LANG = "java.lang.";

  private Filer filer;
  private Elements elements;

  @Override
  public synchronized void init(final ProcessingEnvironment processingEnv) {
    super.init(processingEnv);
    filer = processingEnv.getFiler();
    elements = processingEnv.getElementUtils();
  }

  @Override
  public boolean process(final Set<? extends TypeElement> annotations,
                         final RoundEnvironment env) {
    final Set<? extends Element> elements = env.getElementsAnnotatedWith(AutoMatter.class);
    for (Element element : elements) {
      try {
        writeBuilder(element, env);
      } catch (IOException e) {
        processingEnv.getMessager().printMessage(ERROR, e.getMessage());
      }
    }
    return false;
  }

  private void writeBuilder(final Element element, final RoundEnvironment env) throws IOException {
    final String packageName = elements.getPackageOf(element).getQualifiedName().toString();
    final Name targetSimpleName = element.getSimpleName();
    final String targetName = fullyQualifedName(packageName, targetSimpleName.toString());
    final String builderName = targetName + "Builder";
    final String simpleBuilderName = simpleName(builderName);

    final List<ExecutableElement> fields = enumerateFields(element);

    final JavaFileObject sourceFile = filer.createSourceFile(builderName);
    final JavaWriter writer = new JavaWriter(sourceFile.openWriter());

    writer.emitPackage(packageName);
    writer.emitImports("javax.annotation.Generated");

    writer.emitEmptyLine();
    writer.emitAnnotation(
        Generated.class,
        ImmutableMap.of("value", "\"" + AutoMatterProcessor.class.getName() + "\""));
    writer.beginType(simpleBuilderName, "class", EnumSet.of(PUBLIC, FINAL));

    emitFields(writer, fields);
    emitSetters(writer, simpleBuilderName, fields);
    emitBuild(targetSimpleName, writer, fields);
    emitValue(targetSimpleName, writer, fields);

    writer.endType();
    writer.close();
  }

  private String fullyQualifedName(final String packageName, final String simpleName) {
    return packageName.isEmpty()
           ? simpleName
           : packageName + "." + simpleName;
  }

  private void emitFields(final JavaWriter writer, final List<ExecutableElement> fields)
      throws IOException {
    writer.emitEmptyLine();
    for (ExecutableElement field : fields) {
      writer.emitField(fieldType(field), fieldName(field), EnumSet.of(PRIVATE));
    }
  }

  private void emitValue(final Name targetName, final JavaWriter writer,
                         final List<ExecutableElement> fields)
      throws IOException {
    writer.emitEmptyLine();
    writer.beginType("Value", "class", EnumSet.of(PRIVATE, STATIC, FINAL),
                     null, targetName.toString());
    emitValueFields(writer, fields);
    emitValueConstructor(writer, fields);
    emitValueGetters(writer, fields);
    writer.endType();
  }

  private void emitValueConstructor(final JavaWriter writer, final List<ExecutableElement> fields)
      throws IOException {
    writer.emitEmptyLine();
    final List<String> parameters = Lists.newArrayList();
    for (ExecutableElement field : fields) {
      parameters.add(fieldType(field));
      parameters.add(fieldName(field));
    }
    writer.beginConstructor(EnumSet.of(PRIVATE), parameters, null);
    for (ExecutableElement field : fields) {
      writer.emitStatement("this.%1$s = %1$s", fieldName(field));
    }
    writer.endConstructor();
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
    writer.emitAnnotation(Override.class);
    writer.beginMethod(fieldType(field), fieldName(field), EnumSet.of(PUBLIC));
    writer.emitStatement("return %s", fieldName(field));
    writer.endMethod();
  }

  private void emitBuild(final Name targetName, final JavaWriter writer,
                         final List<ExecutableElement> fields) throws IOException {
    writer.emitEmptyLine();
    writer.beginMethod(targetName.toString(), "build", EnumSet.of(PUBLIC));
    final List<String> parameters = Lists.newArrayList();
    for (Element field : fields) {
      parameters.add(field.getSimpleName().toString());
    }
    writer.emitStatement("return new Value(%s)", Joiner.on(", ").join(parameters));
    writer.endMethod();
  }

  private void emitSetters(final JavaWriter writer, final String builderName,
                           final List<ExecutableElement> fields) throws IOException {
    for (final ExecutableElement field : fields) {
      emitSetter(writer, builderName, field);
    }
  }

  private void emitSetter(final JavaWriter writer, final String builderName,
                          final ExecutableElement field)
      throws IOException {
    writer.emitEmptyLine();
    writer.beginMethod(builderName, fieldName(field), EnumSet.of(PUBLIC),
                       fieldType(field), fieldName(field));
    writer.emitStatement("this.%1$s = %1$s", fieldName(field));
    writer.emitStatement("return this");
    writer.endMethod();
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

  private List<ExecutableElement> enumerateFields(final Element element) {
    final List<ExecutableElement> fields = Lists.newArrayList();
    for (final Element member : element.getEnclosedElements()) {
      if (member.getKind().equals(ElementKind.METHOD)) {
        final ExecutableElement executable = (ExecutableElement) member;
        if (executable.getModifiers().contains(STATIC)) {
          continue;
        }
        fields.add(executable);
      }
    }
    return fields;
  }

  private static String simpleName(String fullyQualifiedName) {
    int lastDot = fullyQualifiedName.lastIndexOf('.');
    return fullyQualifiedName.substring(lastDot + 1, fullyQualifiedName.length());
  }

  @Override
  public Set<String> getSupportedAnnotationTypes() {
    return ImmutableSet.of(AutoMatter.class.getName());
  }
}
