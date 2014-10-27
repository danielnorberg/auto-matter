package io.norberg.automatter;

import com.google.testing.compile.JavaFileObjects;

import org.junit.Test;

import javax.tools.JavaFileObject;

import io.norberg.automatter.processor.AutoMatterProcessor;

import static com.google.common.truth.Truth.assert_;
import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;

public class AutoMatterProcessorTest {

  @Test
  public void testFoo() {
    final JavaFileObject source = JavaFileObjects.forResource("good/Foo.java");
    assert_().about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(JavaFileObjects.forResource("expected/FooBuilder.java"));
  }

  @Test
  public void testTopLevel() {
    final JavaFileObject source = JavaFileObjects.forResource("good/TopLevel.java");
    assert_().about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(JavaFileObjects.forResource("expected/TopLevelBuilder.java"));
  }

  @Test
  public void testNested() {
    final JavaFileObject source = JavaFileObjects.forResource("good/Nested.java");
    assert_().about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(JavaFileObjects.forResource("expected/NestedFoobarBuilder.java"));
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
  public void verifyMissingImportFails() {
    final JavaFileObject source = JavaFileObjects.forResource("bad/MissingImport.java");
    assert_().about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .failsToCompile()
        .withErrorContaining("Cannot resolve type, might be missing import: Dependency");
  }

  @Test
  public void verifyBadBuilderReturnTypeFails() {
    final JavaFileObject source = JavaFileObjects.forResource("bad/BadBuilderReturnType.java");
    assert_().about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .failsToCompile()
        .withErrorContaining("builder() return type must be BadBuilderReturnTypeBuilder");
  }
}
