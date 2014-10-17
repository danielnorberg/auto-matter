package io.norberg.automatter;

import com.google.testing.compile.JavaFileObjects;

import org.junit.Test;

import javax.tools.JavaFileObject;

import static com.google.common.truth.Truth.assert_;
import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;

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

//  @Test
//  public void testTopLevel() {
//    final JavaFileObject source = JavaFileObjects.forResource("TopLevel.java");
//    assert_().about(javaSource())
//        .that(source)
//        .processedWith(new AutoMatterProcessor())
//        .compilesWithoutError()
//        .and().generatesSources(JavaFileObjects.forResource("TopLevelBuilder.java"));
//  }
}
