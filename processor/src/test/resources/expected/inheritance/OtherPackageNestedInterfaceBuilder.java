package other;

import io.norberg.automatter.AutoMatter;
${GENERATED_IMPORT}

${GENERATED_ANNOTATION}
@AutoMatter.Generated
public final class OtherPackageNestedInterfaceBuilder {
  private String foo;

  public OtherPackageNestedInterfaceBuilder() {
  }

  private OtherPackageNestedInterfaceBuilder(OtherPackage.OtherPackageNestedInterface v) {
    this.foo = v.foo();
  }

  private OtherPackageNestedInterfaceBuilder(OtherPackageNestedInterfaceBuilder v) {
    this.foo = v.foo();
  }

  public String foo() {
    return foo;
  }

  public OtherPackageNestedInterfaceBuilder foo(String foo) {
    if (foo == null) {
      throw new NullPointerException("foo");
    }
    this.foo = foo;
    return this;
  }

  public OtherPackage.OtherPackageNestedInterface build() {
    return new Value(foo);
  }

  public static OtherPackageNestedInterfaceBuilder from(
      OtherPackage.OtherPackageNestedInterface v) {
    return new OtherPackageNestedInterfaceBuilder(v);
  }

  public static OtherPackageNestedInterfaceBuilder from(OtherPackageNestedInterfaceBuilder v) {
    return new OtherPackageNestedInterfaceBuilder(v);
  }

  @AutoMatter.Generated
  private static final class Value implements OtherPackage.OtherPackageNestedInterface {
    private final String foo;

    private Value(@AutoMatter.Field("foo") String foo) {
      if (foo == null) {
        throw new NullPointerException("foo");
      }
      this.foo = foo;
    }

    @AutoMatter.Field
    @Override
    public String foo() {
      return foo;
    }

    public OtherPackageNestedInterfaceBuilder builder() {
      return new OtherPackageNestedInterfaceBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof OtherPackage.OtherPackageNestedInterface)) {
        return false;
      }
      final OtherPackage.OtherPackageNestedInterface that = (OtherPackage.OtherPackageNestedInterface) o;
      if (foo != null ? !foo.equals(that.foo()) : that.foo() != null) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + (this.foo != null ? this.foo.hashCode() : 0);
      return result;
    }

    @Override
    public String toString() {
      return "OtherPackage.OtherPackageNestedInterface{" +
          "foo=" + foo +
          '}';
    }
  }
}
