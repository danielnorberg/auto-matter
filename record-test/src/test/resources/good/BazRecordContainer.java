package foo;

import io.norberg.automatter.AutoMatter;

public class BazRecordContainer {
  @AutoMatter
  public record BazRecord(int a, String b) {}
}

