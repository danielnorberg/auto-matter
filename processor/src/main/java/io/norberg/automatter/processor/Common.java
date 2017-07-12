package io.norberg.automatter.processor;

import com.google.common.collect.ImmutableSet;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import org.modeshape.common.text.Inflector;

import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;
import java.util.Set;

class Common {
  private static final Inflector INFLECTOR = new Inflector();

  private static final Set<String> KEYWORDS = ImmutableSet.of(
      "abstract", "continue", "for", "new", "switch", "assert", "default", "if", "package",
      "synchronized", "boolean", "do", "goto", "private", "this", "break", "double", "implements",
      "protected", "throw", "byte", "else", "import", "public", "throws", "case", "enum",
      "instanceof", "return", "transient", "catch", "extends", "int", "short", "try", "char",
      "final", "interface", "static", "void", "class", "finally", "long", "strictfp", "volatile",
      "const", "float", "native", "super", "while");

  static void assertNotNull(MethodSpec.Builder spec, String name) {
    assertNotNull(spec, name, name);
  }

  static void assertNotNull(MethodSpec.Builder spec, String name, String msg) {
    spec.beginControlFlow("if ($N == null)", name)
        .addStatement("throw new $T($S)", ClassName.get(NullPointerException.class), msg)
        .endControlFlow();
  }

  static AutoMatterProcessorException fail(final String msg, final Element element)
      throws AutoMatterProcessorException {
    throw new AutoMatterProcessorException(msg, element);
  }

  static String singular(final Elements elements, final String name) {
    final String singular = INFLECTOR.singularize(name);
    if (KEYWORDS.contains(singular)) {
      return null;
    }
    if (elements.getTypeElement("java.lang." + singular) != null) {
      return null;
    }
    return name.equals(singular) ? null : singular;
  }

  static ClassName rawBuilderType(final Descriptor d) {
    return ClassName.get(d.packageName(), d.builderName());
  }

  static TypeName builderType(final Descriptor d) {
    final ClassName raw = rawBuilderType(d);
    if (!d.isGeneric()) {
      return raw;
    }
    return ParameterizedTypeName.get(raw, d.typeArguments());
  }
}
