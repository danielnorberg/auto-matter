package io.norberg.automatter;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(TYPE)
@Retention(RUNTIME)
public @interface AutoMatter {

  @Target({PARAMETER, METHOD})
  @Retention(RUNTIME)
  @interface Field {

    String value() default "";
  }

  @Target(METHOD)
  @Retention(RUNTIME)
  @interface ToString {}

  @Target(METHOD)
  @Retention(RUNTIME)
  @interface Check {}

  @Target(METHOD)
  @Retention(RUNTIME)
  @interface Redacted {

    String value() default "****";
  }
}
