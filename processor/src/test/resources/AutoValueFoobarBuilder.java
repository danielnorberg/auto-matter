import java.util.Arrays;
import javax.annotation.Generated;

@Generated("io.norberg.automatter.processor.AutoMatterProcessor")
public final class AutoValueFoobarBuilder {

  private String foo;
  private int bar;

  public AutoValueFoobarBuilder() {
  }

  private AutoValueFoobarBuilder(AutoValueFoobar v) {
    this.foo = v.foo();
    this.bar = v.bar();
  }

  private AutoValueFoobarBuilder(AutoValueFoobarBuilder v) {
    this.foo = v.foo;
    this.bar = v.bar;
  }

  public AutoValueFoobarBuilder foo(String foo) {
    this.foo = foo;
    return this;
  }

  public AutoValueFoobarBuilder bar(int bar) {
    this.bar = bar;
    return this;
  }

  public AutoValueFoobar build() {
    return new AutoValue_AutoValueFoobar(foo, bar);
  }

  public static AutoValueFoobarBuilder from(AutoValueFoobar v) {
    return new AutoValueFoobarBuilder(v);
  }

  public static AutoValueFoobarBuilder from(AutoValueFoobarBuilder v) {
    return new AutoValueFoobarBuilder(v);
  }
}