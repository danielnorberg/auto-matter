package foo;

import io.norberg.automatter.AutoMatter;

public class BazRecordContainer {

  @AutoMatter
  public record BazRecord(String baz1, int a, String b, boolean c, long d, String baz2)
      implements Foobar1, Foobar2 {
    public int baz() {
      return 1;
    }
  }

  @AutoMatter
  public interface Foobar1 {
    int a();

    String b();

    static int foobar1() {
      return 1;
    }
  }

  @AutoMatter
  public interface Foobar2 {
    boolean c();

    long d();

    static int foobar2() {
      return 2;
    }
  }
}
