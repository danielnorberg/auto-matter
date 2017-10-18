${GENERATED_IMPORT}

${GENERATED_ANNOTATION}
public final class NestedFoobarBuilder {

  public NestedFoobarBuilder() {
  }

  private NestedFoobarBuilder(Nested.NestedFoobar v) {
  }

  private NestedFoobarBuilder(NestedFoobarBuilder v) {
  }

  public Nested.NestedFoobar build() {
    return new Value();
  }

  public static NestedFoobarBuilder from(Nested.NestedFoobar v) {
    return new NestedFoobarBuilder(v);
  }

  public static NestedFoobarBuilder from(NestedFoobarBuilder v) {
    return new NestedFoobarBuilder(v);
  }

  private static final class Value
      implements Nested.NestedFoobar {

    private Value() {
    }

    public NestedFoobarBuilder builder() {
      return new NestedFoobarBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof Nested.NestedFoobar)) {
        return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      long temp;
      return result;
    }

    @Override
    public String toString() {
      return "Nested.NestedFoobar{" +
             '}';
    }
  }
}
