package io.norberg.automatter;

import static com.google.common.truth.Truth.assert_;
import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;
import static com.google.testing.compile.JavaSourcesSubjectFactory.javaSources;
import static org.junit.Assert.assertEquals;

import com.google.common.collect.ImmutableSet;
import com.google.common.io.Resources;
import com.google.testing.compile.JavaFileObjects;
import io.norberg.automatter.processor.AutoMatterProcessor;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
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
      generatedAnnotation =
          "@"
              + generatedAnnotationClass.getSimpleName()
              + "(\""
              + AutoMatterProcessor.class.getCanonicalName()
              + "\")";
    } else {
      generatedAnnotationImport = "";
      generatedAnnotation = "";
    }

    final String source =
        rawSource
            .replace("${GENERATED_IMPORT}", generatedAnnotationImport)
            .replace("${GENERATED_ANNOTATION}", generatedAnnotation);

    return JavaFileObjects.forSourceString(resourceName, source);
  }

  @Test
  public void testFoo() {
    final JavaFileObject source = JavaFileObjects.forResource("good/Foo.java");
    assert_()
        .about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(expectedSource("expected/FooBuilder.java"));
  }

  @Test
  public void testTopLevel() {
    final JavaFileObject source = JavaFileObjects.forResource("good/TopLevel.java");
    assert_()
        .about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(expectedSource("expected/TopLevelBuilder.java"));
  }

  @Test
  public void testNested() {
    final JavaFileObject source = JavaFileObjects.forResource("good/Nested.java");
    assert_()
        .about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(expectedSource("expected/NestedFoobarBuilder.java"));
  }

  @Test
  public void testPackageLocal() {
    final JavaFileObject source = JavaFileObjects.forResource("good/PackageLocal.java");
    assert_()
        .about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(expectedSource("expected/PackageLocalBuilder.java"));
  }

  @Test
  public void testNestedPackageLocal() {
    final JavaFileObject source = JavaFileObjects.forResource("good/NestedPackageLocal.java");
    assert_()
        .about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(expectedSource("expected/NestedPackageLocalFoobarBuilder.java"));
  }

  @Test
  public void verifyClassTargetFails() {
    final JavaFileObject source = JavaFileObjects.forResource("bad/ClassFoo.java");
    assert_()
        .about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .failsToCompile()
        .withErrorContaining("@AutoMatter target must be an interface");
  }

  @Test
  public void verifyUnkownFieldTypeFails() {
    final JavaFileObject source = JavaFileObjects.forResource("bad/UnknownFieldType.java");
    assert_()
        .about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .failsToCompile()
        .withErrorContaining(
            "Failed to generate @AutoMatter builder for UnknownFieldType "
                + "because some fields have unresolved types");
  }

  @Test
  public void verifyBadBuilderReturnTypeFails() {
    final JavaFileObject source = JavaFileObjects.forResource("bad/BadBuilderReturnType.java");
    assert_()
        .about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .failsToCompile()
        .withErrorContaining(
            "BadBuilderReturnType.builder() return type must be BadBuilderReturnTypeBuilder");
  }

  @Test
  public void testNullableFields() {
    assert_()
        .about(javaSources())
        .that(
            ImmutableSet.of(
                JavaFileObjects.forResource("good/NullableFields.java"),
                JavaFileObjects.forResource("good/Nullable.java")))
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(expectedSource("expected/NullableFieldsBuilder.java"));
  }

  @Test
  public void testCollectionFields() {
    assert_()
        .about(javaSource())
        .that(JavaFileObjects.forResource("good/CollectionFields.java"))
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(expectedSource("expected/CollectionFieldsBuilder.java"));
  }

  @Test
  public void testCollectionInterfaceField() {
    assert_()
        .about(javaSource())
        .that(JavaFileObjects.forResource("good/CollectionInterfaceField.java"))
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(expectedSource("expected/CollectionInterfaceFieldBuilder.java"));
  }

  @Test
  public void testNullableCollectionFields() {
    assert_()
        .about(javaSource())
        .that(JavaFileObjects.forResource("good/NullableCollectionFields.java"))
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(expectedSource("expected/NullableCollectionFieldsBuilder.java"));
  }

  @Test
  public void testSingularCollectionFields() {
    assert_()
        .about(javaSource())
        .that(JavaFileObjects.forResource("good/SingularCollectionFields.java"))
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError();
  }

  @Test
  public void testReservedCollectionFieldNames() {
    assert_()
        .about(javaSource())
        .that(JavaFileObjects.forResource("good/ReservedCollectionFieldNames.java"))
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError();
  }

  @Test
  public void testGuavaOptionalFields() {
    assert_()
        .about(javaSource())
        .that(JavaFileObjects.forResource("good/GuavaOptionalFields.java"))
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(expectedSource("expected/GuavaOptionalFieldsBuilder.java"));
  }

  @Test
  public void testJUTOptionalFields() {
    Assume.assumeTrue(hasJutOptional());
    assert_()
        .about(javaSource())
        .that(JavaFileObjects.forResource("good/JUTOptionalFields.java"))
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(expectedSource("expected/JUTOptionalFieldsBuilder.java"));
  }

  @Test
  public void testDefaultMethods() {
    Assume.assumeTrue(isJava8());
    assert_()
        .about(javaSource())
        .that(JavaFileObjects.forResource("good/DefaultMethods.java"))
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(expectedSource("expected/DefaultMethodsBuilder.java"));
  }

  @Test
  public void testPrivateMethods() {
    Assume.assumeTrue(isJava9());
    assert_()
        .about(javaSource())
        .that(JavaFileObjects.forResource("good/PrivateMethods.java"))
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(expectedSource("expected/PrivateMethodsBuilder.java"));
  }

  @Test
  public void testOverriddenDefaultMethods() {
    Assume.assumeTrue(isJava8());
    assert_()
        .about(javaSource())
        .that(JavaFileObjects.forResource("good/OverriddenBaseMethods.java"))
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(expectedSource("expected/OverriddenMethodsBuilder.java"));
  }

  @Test
  public void testGenericSingle() {
    final JavaFileObject source = JavaFileObjects.forResource("good/GenericSingle.java");
    assert_()
        .about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(expectedSource("expected/GenericSingleBuilder.java"));
  }

  @Test
  public void testGenericMultiple() {
    final JavaFileObject source = JavaFileObjects.forResource("good/GenericMultiple.java");
    assert_()
        .about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(expectedSource("expected/GenericMultipleBuilder.java"));
  }

  @Test
  public void testGenericCollection() {
    final JavaFileObject source = JavaFileObjects.forResource("good/GenericCollection.java");
    assert_()
        .about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(expectedSource("expected/GenericCollectionBuilder.java"));
  }

  @Test
  public void testGenericJUTOptionalFields() {
    Assume.assumeTrue(hasJutOptional());
    final JavaFileObject source = JavaFileObjects.forResource("good/GenericJUTOptionalFields.java");
    assert_()
        .about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(expectedSource("expected/GenericJUTOptionalFieldsBuilder.java"));
  }

  @Test
  public void testGenericJUTOptionalNested() {
    Assume.assumeTrue(hasJutOptional());
    final JavaFileObject source = JavaFileObjects.forResource("good/GenericJUTOptionalNested.java");
    assert_()
        .about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(expectedSource("expected/GenericJUTOptionalNestedBuilder.java"));
  }

  @Test
  public void testGenericNested() {
    Assume.assumeTrue(hasJutOptional());
    final JavaFileObject source = JavaFileObjects.forResource("good/GenericNested.java");
    assert_()
        .about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(expectedSource("expected/GenericNestedBuilder.java"));
  }

  @Test
  public void testGenericGuavaOptionalFields() {
    final JavaFileObject source =
        JavaFileObjects.forResource("good/GenericGuavaOptionalFields.java");
    assert_()
        .about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(expectedSource("expected/GenericGuavaOptionalFieldsBuilder.java"));
  }

  @Test
  public void testInheritance() {
    assert_()
        .about(javaSources())
        .that(
            ImmutableSet.of(
                JavaFileObjects.forResource("good/inheritance/Quux.java"),
                JavaFileObjects.forResource("good/inheritance/Foo.java"),
                JavaFileObjects.forResource("good/inheritance/Bar.java"),
                JavaFileObjects.forResource("good/inheritance/Foobar.java")))
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(expectedSource("expected/inheritance/FoobarBuilder.java"));
  }

  @Test
  public void testGenericInheritance() {
    assert_()
        .about(javaSources())
        .that(
            ImmutableSet.of(
                JavaFileObjects.forResource("good/inheritance/Foo.java"),
                JavaFileObjects.forResource("good/inheritance/Bar.java"),
                JavaFileObjects.forResource("good/inheritance/Quux.java"),
                JavaFileObjects.forResource("good/inheritance/GenericFoobar.java")))
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(
            expectedSource("expected/inheritance/QuuxBuilder.java"),
            expectedSource("expected/inheritance/GenericFoobarBuilder.java"));
  }

  @Test
  public void testConcreteCollectionInheritingFromGenericCollection() {
    assert_()
        .about(javaSources())
        .that(
            ImmutableSet.of(
                JavaFileObjects.forResource("good/inheritance/Bar.java"),
                JavaFileObjects.forResource("good/inheritance/Quux.java"),
                JavaFileObjects.forResource("good/inheritance/GenericSuperParent.java"),
                JavaFileObjects.forResource("good/inheritance/GenericCollectionParent.java"),
                JavaFileObjects.forResource(
                    "good/inheritance/ConcreteExtensionOfGenericParent.java")))
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(
            expectedSource("expected/inheritance/QuuxBuilder.java"),
            expectedSource("expected/inheritance/GenericSuperParentBuilder.java"),
            expectedSource("expected/inheritance/GenericCollectionParentBuilder.java"),
            expectedSource("expected/inheritance/ConcreteExtensionOfGenericParentBuilder.java"));
  }

  @Test
  public void testOtherPackageNestedInheritance() {
    assert_()
        .about(javaSources())
        .that(
            ImmutableSet.of(
                JavaFileObjects.forResource("good/inheritance/OtherPackage.java"),
                JavaFileObjects.forResource("good/inheritance/InheritingPackage.java")))
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(
            expectedSource("expected/inheritance/OtherPackageNestedInterfaceBuilder.java"),
            expectedSource("expected/inheritance/InheritingPackageBuilder.java"));
  }

  @Test
  public void testDeferredProcessing() {
    assert_()
        .about(javaSources())
        .that(
            ImmutableSet.of(
                JavaFileObjects.forResource("good/deferred-processing/Foo.java"),
                JavaFileObjects.forResource("good/deferred-processing/Bar.java")))
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
    assert_()
        .about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(expectedSource("expected/CustomToStringDefaultBuilder.java"));
  }

  @Test
  public void testCustomToStringStatic() {
    final JavaFileObject source = JavaFileObjects.forResource("good/CustomToStringStatic.java");
    assert_()
        .about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(expectedSource("expected/CustomToStringStaticBuilder.java"));
  }

  @Test
  public void testCustomCheckStatic() {
    final JavaFileObject source = JavaFileObjects.forResource("good/CustomCheckStatic.java");
    assert_()
        .about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(expectedSource("expected/CustomCheckStaticBuilder.java"));
  }

  @Test
  public void testCustomCheckDefault() {
    final JavaFileObject source = JavaFileObjects.forResource("good/CustomCheckDefault.java");
    assert_()
        .about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(expectedSource("expected/CustomCheckDefaultBuilder.java"));
  }

  @Test
  public void testRedactedFields() {
    final JavaFileObject source = JavaFileObjects.forResource("good/RedactedFields.java");
    assert_()
        .about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(expectedSource("expected/RedactedFieldsBuilder.java"));
  }

  @Test
  public void testDefaultMethodOverrideInheritance() {
    assert_()
        .about(javaSources())
        .that(
            ImmutableSet.of(
                JavaFileObjects.forResource("good/inheritance/DefaultMethodOverrideSuperType.java"),
                JavaFileObjects.forResource("good/inheritance/DefaultMethodOverrideSubType.java")))
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(
            expectedSource("expected/inheritance/DefaultMethodOverrideSubTypeBuilder.java"),
            expectedSource("expected/inheritance/DefaultMethodOverrideSuperTypeBuilder.java"));
  }

  @Test
  public void testJSpecifyFields() {
    final JavaFileObject source = JavaFileObjects.forResource("good/JSpecifyFields.java");
    assert_()
        .about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(expectedSource("expected/JSpecifyFieldsBuilder.java"));
  }

  @Test
  public void testJSpecifyNullMarkedFields() {
    assert_()
        .about(javaSources())
        .that(
            ImmutableSet.of(
                JavaFileObjects.forResource("good/jspecify/JSpecifyNullMarkedFields.java"),
                JavaFileObjects.forResource("good/jspecify/package-info.java")))
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(expectedSource("expected/JSpecifyNullMarkedFieldsBuilder.java"));
  }

  @Test
  public void testJSpecifyCollectionFields() {
    final JavaFileObject source = JavaFileObjects.forResource("good/JSpecifyCollectionFields.java");
    assert_()
        .about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(expectedSource("expected/JSpecifyCollectionFieldsBuilder.java"));
  }

  @Test
  public void testGetCanonicalTypeName() {
    final JavaFileObject source =
        JavaFileObjects.forResource("good/CanonicalTypeNameTestTypes.java");

    // Create a processor that captures type information and tests getCanonicalTypeName
    final AbstractProcessor testProcessor =
        new AbstractProcessor() {
          @Override
          public Set<String> getSupportedAnnotationTypes() {
            return Collections.singleton("*");
          }

          @Override
          public SourceVersion getSupportedSourceVersion() {
            return SourceVersion.latestSupported();
          }

          @Override
          public boolean process(
              Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
            if (roundEnv.processingOver()) {
              return false;
            }

            // Find the test interface
            TypeElement testInterface = null;
            for (Element element : roundEnv.getRootElements()) {
              if (element.getKind() == ElementKind.INTERFACE
                  && element.getSimpleName().toString().equals("CanonicalTypeNameTestTypes")) {
                testInterface = (TypeElement) element;
                break;
              }
            }

            if (testInterface == null) {
              return false;
            }

            // Test each method's return type
            for (Element enclosedElement : testInterface.getEnclosedElements()) {
              if (enclosedElement.getKind() == ElementKind.METHOD) {
                ExecutableElement method = (ExecutableElement) enclosedElement;
                String methodName = method.getSimpleName().toString();
                TypeMirror returnType = method.getReturnType();
                String canonicalName = AutoMatterProcessor.getCanonicalTypeName(returnType);

                // Verify expected canonical names
                switch (methodName) {
                  case "simpleString":
                    assertEquals("java.lang.String", canonicalName);
                    break;
                  case "simpleInteger":
                    assertEquals("java.lang.Integer", canonicalName);
                    break;
                  case "listOfStrings":
                    assertEquals("java.util.List<java.lang.String>", canonicalName);
                    break;
                  case "setOfIntegers":
                    assertEquals("java.util.Set<java.lang.Integer>", canonicalName);
                    break;
                  case "mapOfStringToInteger":
                    assertEquals(
                        "java.util.Map<java.lang.String, java.lang.Integer>", canonicalName);
                    break;
                  case "nestedList":
                    assertEquals("java.util.List<java.util.List<java.lang.String>>", canonicalName);
                    break;
                  case "mapWithListValue":
                    assertEquals(
                        "java.util.Map<java.lang.String, java.util.List<java.lang.Integer>>",
                        canonicalName);
                    break;
                  case "nullableString":
                    // TYPE_USE annotation should be stripped
                    assertEquals("java.lang.String", canonicalName);
                    break;
                  case "nullableList":
                    // TYPE_USE annotation should be stripped
                    assertEquals("java.util.List<java.lang.String>", canonicalName);
                    break;
                  case "nullableMap":
                    // TYPE_USE annotation should be stripped
                    assertEquals(
                        "java.util.Map<java.lang.String, java.lang.Integer>", canonicalName);
                    break;
                  case "listOfNullables":
                    // TYPE_USE annotation on type argument should be stripped
                    assertEquals("java.util.List<java.lang.String>", canonicalName);
                    break;
                }
              }
            }
            return false;
          }
        };

    assert_().about(javaSource()).that(source).processedWith(testProcessor).compilesWithoutError();
  }

  private boolean isJava8() {
    try {
      Class.forName("java.util.Optional");
      return true;
    } catch (ClassNotFoundException e) {
      return false;
    }
  }

  private boolean isJava9() {
    try {
      Runtime.class.getMethod("version");
      return true;
    } catch (NoSuchMethodException e) {
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
