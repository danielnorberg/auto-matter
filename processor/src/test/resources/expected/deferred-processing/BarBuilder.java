package foo;

import io.norberg.automatter.AutoMatter;
${GENERATED_IMPORT}

${GENERATED_ANNOTATION}
@AutoMatter.Generated
public final class BarBuilder {
  public BarBuilder() {
  }

  private BarBuilder(Bar v) {
  }

  private BarBuilder(BarBuilder v) {
  }

  public Bar build() {
    return new Value();
  }

  public static BarBuilder from(Bar v) {
    return new BarBuilder(v);
  }

  public static BarBuilder from(BarBuilder v) {
    return new BarBuilder(v);
  }

  private static final class Value implements Bar {
    private Value() {
    }

    public BarBuilder builder() {
      return new BarBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof Bar)) {
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
      return "Bar{" +
          '}';
    }
  }
}
