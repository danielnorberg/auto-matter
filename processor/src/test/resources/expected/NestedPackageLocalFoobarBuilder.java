package foo;

${GENERATED_IMPORT}

${GENERATED_ANNOTATION}
final class NestedPackageLocalFoobarBuilder {

  public NestedPackageLocalFoobarBuilder() {
  }

  private NestedPackageLocalFoobarBuilder(NestedPackageLocal.NestedPackageLocalFoobar v) {
  }

  private NestedPackageLocalFoobarBuilder(NestedPackageLocalFoobarBuilder v) {
  }

  public NestedPackageLocal.NestedPackageLocalFoobar build() {
    return new Value();
  }

  public static NestedPackageLocalFoobarBuilder from(NestedPackageLocal.NestedPackageLocalFoobar v) {
    return new NestedPackageLocalFoobarBuilder(v);
  }

  public static NestedPackageLocalFoobarBuilder from(NestedPackageLocalFoobarBuilder v) {
    return new NestedPackageLocalFoobarBuilder(v);
  }

  private static final class Value
      implements NestedPackageLocal.NestedPackageLocalFoobar {

    private Value() {
    }

    public NestedPackageLocalFoobarBuilder builder() {
      return new NestedPackageLocalFoobarBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof NestedPackageLocal.NestedPackageLocalFoobar)) {
        return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      return result;
    }

    @Override
    public String toString() {
      return "NestedPackageLocal.NestedPackageLocalFoobar{" +
             '}';
    }
  }
}
