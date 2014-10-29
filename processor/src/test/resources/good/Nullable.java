package foo;

import com.google.common.base.Optional;

@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface Nullable {

  String simple() default "";

  Class<?> complex();
}