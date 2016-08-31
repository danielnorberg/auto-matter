package io.norberg.automatter;

import com.google.common.collect.ImmutableSet;
import com.google.testing.compile.JavaFileObjects;

import org.junit.Assume;
import org.junit.Test;

import javax.tools.JavaFileObject;

import io.norberg.automatter.processor.AutoMatterProcessor;

import static com.google.common.truth.Truth.assert_;
import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;
import static com.google.testing.compile.JavaSourcesSubjectFactory.javaSources;

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
  public void testPackageLocal() {
    final JavaFileObject source = JavaFileObjects.forResource("good/PackageLocal.java");
    assert_().about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(
        JavaFileObjects.forResource("expected/PackageLocalBuilder.java"));
  }

  @Test
  public void testNestedPackageLocal() {
    final JavaFileObject source = JavaFileObjects.forResource("good/NestedPackageLocal.java");
    assert_().about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(
        JavaFileObjects.forResource("expected/NestedPackageLocalFoobarBuilder.java"));
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

  @Test
  public void testNullableFields() {
    assert_().about(javaSources())
        .that(ImmutableSet.of(
            JavaFileObjects.forResource("good/NullableFields.java"),
            JavaFileObjects.forResource("good/Nullable.java")
        ))
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(
        JavaFileObjects.forResource("expected/NullableFieldsBuilder.java"));
  }

  @Test
  public void testCollectionFields() {
    assert_().about(javaSource())
        .that(JavaFileObjects.forResource("good/CollectionFields.java"))
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(
        JavaFileObjects.forResource("expected/CollectionFieldsBuilder.java"));
  }

  @Test
  public void testNullableCollectionFields() {
    assert_().about(javaSource())
        .that(JavaFileObjects.forResource("good/NullableCollectionFields.java"))
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(
        JavaFileObjects.forResource("expected/NullableCollectionFieldsBuilder.java"));
  }

  @Test
  public void testSingularCollectionFields() {
    assert_().about(javaSource())
        .that(JavaFileObjects.forResource("good/SingularCollectionFields.java"))
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError();
  }

  @Test
  public void testReservedCollectionFieldNames() {
    assert_().about(javaSource())
        .that(JavaFileObjects.forResource("good/ReservedCollectionFieldNames.java"))
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError();
  }

  @Test
  public void testGuavaOptionalFields() {
    assert_().about(javaSource())
        .that(JavaFileObjects.forResource("good/GuavaOptionalFields.java"))
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(
        JavaFileObjects.forResource("expected/GuavaOptionalFieldsBuilder.java"));
  }

  @Test
  public void testJUTOptionalFields() {
    Assume.assumeTrue(hasJutOptional());
    assert_().about(javaSource())
        .that(JavaFileObjects.forResource("good/JUTOptionalFields.java"))
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(
        JavaFileObjects.forResource("expected/JUTOptionalFieldsBuilder.java"));
  }

  @Test
  public void testDefaultMethods() {
    Assume.assumeTrue(isJava8());
    assert_().about(javaSource())
        .that(JavaFileObjects.forResource("good/DefaultMethods.java"))
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(
        JavaFileObjects.forResource("expected/DefaultMethodsBuilder.java"));
  }

  @Test
  public void testOverriddenDefaultMethods() {
    Assume.assumeTrue(isJava8());
    assert_().about(javaSource())
        .that(JavaFileObjects.forResource("good/OverriddenBaseMethods.java"))
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(
        JavaFileObjects.forResource("expected/OverriddenMethodsBuilder.java"));
  }

  @Test
  public void testGenericSingle() {
    final JavaFileObject source = JavaFileObjects.forResource("good/GenericSingle.java");
    assert_().about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(JavaFileObjects.forResource("expected/GenericSingleBuilder.java"));
  }

  @Test
  public void testGenericMultiple() {
    final JavaFileObject source = JavaFileObjects.forResource("good/GenericMultiple.java");
    assert_().about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(JavaFileObjects.forResource("expected/GenericMultipleBuilder.java"));
  }

  @Test
  public void testGenericCollection() {
    final JavaFileObject source = JavaFileObjects.forResource("good/GenericCollection.java");
    assert_().about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(JavaFileObjects.forResource("expected/GenericCollectionBuilder.java"));
  }

  @Test
  public void testGenericJUTOptionalFields() {
    Assume.assumeTrue(hasJutOptional());
    final JavaFileObject source = JavaFileObjects.forResource("good/GenericJUTOptionalFields.java");
    assert_().about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(JavaFileObjects.forResource("expected/GenericJUTOptionalFieldsBuilder.java"));
  }

  @Test
  public void testGenericGuavaOptionalFields() {
    final JavaFileObject source = JavaFileObjects.forResource("good/GenericGuavaOptionalFields.java");
    assert_().about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(JavaFileObjects.forResource("expected/GenericGuavaOptionalFieldsBuilder.java"));
  }

  @Test
  public void testInheritance() {
    assert_().about(javaSources())
        .that(ImmutableSet.of(
            JavaFileObjects.forResource("good/inheritance/Foo.java"),
            JavaFileObjects.forResource("good/inheritance/Bar.java"),
            JavaFileObjects.forResource("good/inheritance/Foobar.java")
        ))
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(JavaFileObjects.forResource("expected/inheritance/FoobarBuilder.java"));
  }

  @Test
  public void testGenericInheritance() {
    assert_().about(javaSources())
        .that(ImmutableSet.of(
            JavaFileObjects.forResource("good/inheritance/Foo.java"),
            JavaFileObjects.forResource("good/inheritance/Bar.java"),
            JavaFileObjects.forResource("good/inheritance/GenericFoobar.java")
        ))
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(JavaFileObjects.forResource("expected/inheritance/GenericFoobarBuilder.java"));
  }

  private boolean isJava8() {
    try {
      Class.forName("java.util.Optional");
      return true;
    } catch (ClassNotFoundException e) {
      return false;
    }
  }

  private boolean hasJutOptional() {
    try {
      Class.forName("java.util.Optional");
      return true;
    } catch (ClassNotFoundException e) {
      return false;
    }
  }
}
