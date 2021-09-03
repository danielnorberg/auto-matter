package io.norberg.automatter;

import static com.google.common.truth.Truth.assert_;
import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;

import com.google.testing.compile.JavaFileObjects;
import io.norberg.automatter.processor.AutoMatterProcessor;
import javax.tools.JavaFileObject;
import org.junit.Test;

public class RecordProcessingTest {

  @Test
  public void testRecord() {
    final JavaFileObject source = JavaFileObjects.forResource("good/FoobarRecord.java");
    assert_()
        .about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(JavaFileObjects.forResource("expected/FoobarRecordBuilder.java"));
  }

  @Test
  public void testNestedRecord() {
    final JavaFileObject source = JavaFileObjects.forResource("good/BazRecordContainer.java");
    assert_()
        .about(javaSource())
        .that(source)
        .processedWith(new AutoMatterProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(JavaFileObjects.forResource("expected/BazRecordBuilder.java"));
  }
}
