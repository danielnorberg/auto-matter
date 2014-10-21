package io.norberg.automatter;

import com.google.testing.compile.JavaFileObjects;

import org.junit.Test;

import javax.tools.JavaFileObject;

import io.norberg.automatter.processor.AutoMatterProcessor;

import static com.google.common.truth.Truth.assert_;
import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;
import static com.google.testing.compile.JavaSourcesSubjectFactory.javaSources;
import static java.util.Arrays.asList;

public class AutoMatterProcessorTest {

  @Test
  public void testFoo() {
    final JavaFileObject source = JavaFileObjects.forResource("Foo.java");
    assert_().about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(JavaFileObjects.forResource("FooBuilder.java"));
  }

  @Test
  public void testTopLevel() {
    final JavaFileObject source = JavaFileObjects.forResource("TopLevel.java");
    assert_().about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(JavaFileObjects.forResource("TopLevelBuilder.java"));
  }

  @Test
  public void verifyClassTargetFails() {
    final JavaFileObject source = JavaFileObjects.forResource("bad/ClassFoo.java");
    assert_().about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .failsToCompile()
        .withErrorContaining("@AutoMatter target must be an interface");
  }

  @Test
  public void testAutoValue() {
    final JavaFileObject source = JavaFileObjects.forResource("AutoValueFoobar.java");
    final JavaFileObject value = JavaFileObjects.forResource("AutoValue_AutoValueFoobar.java");
    assert_().about(javaSources())
        .that(asList(value, source))
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(JavaFileObjects.forResource("AutoValueFoobarBuilder.java"));
  }
}
