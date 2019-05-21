package io.norberg.automatter;

import static com.google.common.truth.Truth.assert_;
import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;
import static com.google.testing.compile.JavaSourcesSubjectFactory.javaSources;
import static com.squareup.javapoet.WildcardTypeName.subtypeOf;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.google.common.collect.ImmutableSet;
import com.google.common.io.Resources;
import com.google.testing.compile.JavaFileObjects;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.WildcardTypeName;
import io.norberg.automatter.processor.AutoMatterProcessor;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.tools.JavaFileObject;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

public class AutoMatterProcessorTest {

  private Class<?> generatedAnnotationClass;

  @Before
  public void setUp() throws Exception {
    generatedAnnotationClass = generatedAnnotationType();
  }

  private static Class<?> generatedAnnotationType() {
    try {
      return Class.forName("javax.annotation.processing.Generated");
    } catch (ClassNotFoundException ignore) {
    }

    try {
      return Class.forName("javax.annotation.Generated");
    } catch (ClassNotFoundException ignore) {
    }

    return null;
  }

  private JavaFileObject expectedSource(String resourceName) {
    final String rawSource;
    try {
      rawSource = Resources.toString(Resources.getResource(resourceName), StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    final String generatedAnnotation;
    final String generatedAnnotationImport;

    if (generatedAnnotationClass != null) {
      generatedAnnotationImport = "import " + generatedAnnotationClass.getCanonicalName() + ";";
      generatedAnnotation = "@" + generatedAnnotationClass.getSimpleName() +
          "(\"" + AutoMatterProcessor.class.getCanonicalName() + "\")";
    } else {
      generatedAnnotationImport = "";
      generatedAnnotation = "";
    }

    final String source = rawSource
        .replace("${GENERATED_IMPORT}", generatedAnnotationImport)
        .replace("${GENERATED_ANNOTATION}", generatedAnnotation);

    return JavaFileObjects.forSourceString(resourceName, source);
  }

  @Test
  public void testFoo() {
    final JavaFileObject source = JavaFileObjects.forResource("good/Foo.java");
    assert_().about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(expectedSource("expected/FooBuilder.java"));
  }

  @Test
  public void testTopLevel() {
    final JavaFileObject source = JavaFileObjects.forResource("good/TopLevel.java");
    assert_().about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(expectedSource("expected/TopLevelBuilder.java"));
  }

  @Test
  public void testNested() {
    final JavaFileObject source = JavaFileObjects.forResource("good/Nested.java");
    assert_().about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(expectedSource("expected/NestedFoobarBuilder.java"));
  }

  @Test
  public void testPackageLocal() {
    final JavaFileObject source = JavaFileObjects.forResource("good/PackageLocal.java");
    assert_().about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(
        expectedSource("expected/PackageLocalBuilder.java"));
  }

  @Test
  public void testNestedPackageLocal() {
    final JavaFileObject source = JavaFileObjects.forResource("good/NestedPackageLocal.java");
    assert_().about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(
        expectedSource("expected/NestedPackageLocalFoobarBuilder.java"));
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
  public void verifyUnkownFieldTypeFails() {
    final JavaFileObject source = JavaFileObjects.forResource("bad/UnknownFieldType.java");
    assert_().about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .failsToCompile()
        .withErrorContaining("Failed to generate @AutoMatter builder for UnknownFieldType "
            + "because some fields have unresolved types");
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
        expectedSource("expected/NullableFieldsBuilder.java"));
  }

  @Test
  public void testCollectionFields() {
    assert_().about(javaSource())
        .that(JavaFileObjects.forResource("good/CollectionFields.java"))
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(
        expectedSource("expected/CollectionFieldsBuilder.java"));
  }

  @Test
  public void testCollectionInterfaceField() {
    assert_().about(javaSource())
        .that(JavaFileObjects.forResource("good/CollectionInterfaceField.java"))
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(
        expectedSource("expected/CollectionInterfaceFieldBuilder.java"));
  }

  @Test
  public void testNullableCollectionFields() {
    assert_().about(javaSource())
        .that(JavaFileObjects.forResource("good/NullableCollectionFields.java"))
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(
        expectedSource("expected/NullableCollectionFieldsBuilder.java"));
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
        expectedSource("expected/GuavaOptionalFieldsBuilder.java"));
  }

  @Test
  public void testJUTOptionalFields() {
    Assume.assumeTrue(hasJutOptional());
    assert_().about(javaSource())
        .that(JavaFileObjects.forResource("good/JUTOptionalFields.java"))
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(
        expectedSource("expected/JUTOptionalFieldsBuilder.java"));
  }

  @Test
  public void testDefaultMethods() {
    Assume.assumeTrue(isJava8());
    assert_().about(javaSource())
        .that(JavaFileObjects.forResource("good/DefaultMethods.java"))
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(
        expectedSource("expected/DefaultMethodsBuilder.java"));
  }

  @Test
  public void testOverriddenDefaultMethods() {
    Assume.assumeTrue(isJava8());
    assert_().about(javaSource())
        .that(JavaFileObjects.forResource("good/OverriddenBaseMethods.java"))
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(
        expectedSource("expected/OverriddenMethodsBuilder.java"));
  }

  @Test
  public void testGenericSingle() {
    final JavaFileObject source = JavaFileObjects.forResource("good/GenericSingle.java");
    assert_().about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(expectedSource("expected/GenericSingleBuilder.java"));
  }

  @Test
  public void testGenericMultiple() {
    final JavaFileObject source = JavaFileObjects.forResource("good/GenericMultiple.java");
    assert_().about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(expectedSource("expected/GenericMultipleBuilder.java"));
  }

  @Test
  public void testGenericCollection() {
    final JavaFileObject source = JavaFileObjects.forResource("good/GenericCollection.java");
    assert_().about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(expectedSource("expected/GenericCollectionBuilder.java"));
  }

  @Test
  public void testGenericJUTOptionalFields() {
    Assume.assumeTrue(hasJutOptional());
    final JavaFileObject source = JavaFileObjects.forResource("good/GenericJUTOptionalFields.java");
    assert_().about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(expectedSource("expected/GenericJUTOptionalFieldsBuilder.java"));
  }

  @Test
  public void testGenericJUTOptionalNested() {
    Assume.assumeTrue(hasJutOptional());
    final JavaFileObject source = JavaFileObjects.forResource("good/GenericJUTOptionalNested.java");
    assert_().about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(expectedSource("expected/GenericJUTOptionalNestedBuilder.java"));
  }

  @Test
  public void testGenericNested() {
    Assume.assumeTrue(hasJutOptional());
    final JavaFileObject source = JavaFileObjects.forResource("good/GenericNested.java");
    assert_().about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(expectedSource("expected/GenericNestedBuilder.java"));
  }

  @Test
  public void testGenericGuavaOptionalFields() {
    final JavaFileObject source = JavaFileObjects.forResource("good/GenericGuavaOptionalFields.java");
    assert_().about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(expectedSource("expected/GenericGuavaOptionalFieldsBuilder.java"));
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
        .and().generatesSources(expectedSource("expected/inheritance/FoobarBuilder.java"));
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
        .and().generatesSources(expectedSource("expected/inheritance/GenericFoobarBuilder.java"));
  }

  @Test
  public void testConcreteCollectionInheritingFromGenericCollection() {
    assert_().about(javaSources())
        .that(ImmutableSet.of(
            JavaFileObjects.forResource("good/inheritance/GenericCollectionParent.java"),
            JavaFileObjects.forResource("good/inheritance/ConcreteExtensionOfGenericParent.java")
        ))
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(expectedSource("expected/inheritance/GenericCollectionParentBuilder.java"),
                                expectedSource("expected/inheritance/ConcreteExtensionOfGenericParentBuilder.java"));
  }

  @Test
  public void testDeferredProcessing() {
    assert_().about(javaSources())
        .that(ImmutableSet.of(
            JavaFileObjects.forResource("good/deferred-processing/Foo.java"),
            JavaFileObjects.forResource("good/deferred-processing/Bar.java")
        ))
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(
            expectedSource("expected/deferred-processing/FooBuilder.java"),
            expectedSource("expected/deferred-processing/BarBuilder.java"));
  }

  @Test
  public void testCustomToStringDefault() {
    final JavaFileObject source = JavaFileObjects.forResource("good/CustomToStringDefault.java");
    assert_().about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(expectedSource("expected/CustomToStringDefaultBuilder.java"));
  }

  @Test
  public void testCustomToStringStatic() {
    final JavaFileObject source = JavaFileObjects.forResource("good/CustomToStringStatic.java");
    assert_().about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(expectedSource("expected/CustomToStringStaticBuilder.java"));
  }

  @Test
  public void testReifiableCollectionTypes() {
    final JavaFileObject source = JavaFileObjects.forResource("good/ReifiableCollectionTypes.java");
    assert_().about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and().generatesSources(
        expectedSource("expected/ReifiableCollectionTypesBuilder.java"));
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
