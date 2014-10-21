import com.google.auto.value.AutoValue;

import io.norberg.automatter.AutoMatter;

@AutoValue
@AutoMatter
public abstract class AutoValueFoobar {
  public abstract String foo();
  public abstract int bar();

  public static AutoValueFoobar create(String foo, int bar) {
    return new AutoValue_AutoValueFoobar(foo, bar);
  }
}