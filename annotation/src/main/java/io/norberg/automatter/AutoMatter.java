package io.norberg.automatter;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)
public @interface AutoMatter {

  @Target({PARAMETER, METHOD})
  @Retention(RUNTIME)
  @interface Field {
    String value() default "";
  }
}
