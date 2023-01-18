package inheritance;

import io.norberg.automatter.AutoMatter;
${GENERATED_IMPORT}
import other.OtherPackage;
import other.OtherPackageNestedInterfaceBuilder;

${GENERATED_ANNOTATION}
@AutoMatter.Generated
final class InheritingPackageBuilder {
  private String foo;

  private String bar;

  public InheritingPackageBuilder() {
  }

  private InheritingPackageBuilder(InheritingPackage v) {
    this.foo = v.foo();
    this.bar = v.bar();
  }

  private InheritingPackageBuilder(OtherPackage.OtherPackageNestedInterface v) {
    this.foo = v.foo();
  }

  private InheritingPackageBuilder(InheritingPackageBuilder v) {
    this.foo = v.foo();
    this.bar = v.bar();
  }

  private InheritingPackageBuilder(OtherPackageNestedInterfaceBuilder v) {
    this.foo = v.foo();
  }

  public String foo() {
    return foo;
  }

  public InheritingPackageBuilder foo(String foo) {
    if (foo == null) {
      throw new NullPointerException("foo");
    }
    this.foo = foo;
    return this;
  }

  public String bar() {
    return bar;
  }

  public InheritingPackageBuilder bar(String bar) {
    if (bar == null) {
      throw new NullPointerException("bar");
    }
    this.bar = bar;
    return this;
  }

  public InheritingPackage build() {
    return new Value(foo, bar);
  }

  public static InheritingPackageBuilder from(InheritingPackage v) {
    return new InheritingPackageBuilder(v);
  }

  public static InheritingPackageBuilder from(OtherPackage.OtherPackageNestedInterface v) {
    return new InheritingPackageBuilder(v);
  }

  public static InheritingPackageBuilder from(InheritingPackageBuilder v) {
    return new InheritingPackageBuilder(v);
  }

  public static InheritingPackageBuilder from(OtherPackageNestedInterfaceBuilder v) {
    return new InheritingPackageBuilder(v);
  }

  @AutoMatter.Generated
  private static final class Value implements InheritingPackage {
    private final String foo;

    private final String bar;

    private Value(@AutoMatter.Field("foo") String foo, @AutoMatter.Field("bar") String bar) {
      if (foo == null) {
        throw new NullPointerException("foo");
      }
      if (bar == null) {
        throw new NullPointerException("bar");
      }
      this.foo = foo;
      this.bar = bar;
    }

    @AutoMatter.Field
    @Override
    public String foo() {
      return foo;
    }

    @AutoMatter.Field
    @Override
    public String bar() {
      return bar;
    }

    public InheritingPackageBuilder builder() {
      return new InheritingPackageBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof InheritingPackage)) {
        return false;
      }
      final InheritingPackage that = (InheritingPackage) o;
      if (foo != null ? !foo.equals(that.foo()) : that.foo() != null) {
        return false;
      }
      if (bar != null ? !bar.equals(that.bar()) : that.bar() != null) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + (this.foo != null ? this.foo.hashCode() : 0);
      result = 31 * result + (this.bar != null ? this.bar.hashCode() : 0);
      return result;
    }

    @Override
    public String toString() {
      return "InheritingPackage{" +
          "foo=" + foo +
          ", bar=" + bar +
          '}';
    }
  }
}
