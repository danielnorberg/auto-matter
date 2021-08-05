package foo;

import io.norberg.automatter.AutoMatter;
${GENERATED_IMPORT}

${GENERATED_ANNOTATION}
public final class SensitiveFieldsBuilder {

  private String userName;
  
  private String password;
  
  private String token;

  public SensitiveFieldsBuilder() {
  }

  private SensitiveFieldsBuilder(SensitiveFields v) {
    this.userName = v.userName();
    this.password = v.password();
    this.token = v.token();
  }

  private SensitiveFieldsBuilder(SensitiveFieldsBuilder v) {
    this.userName = v.userName();
    this.password = v.password();
    this.token = v.token();
  }

  public String userName() {
    return userName;
  }

  public SensitiveFieldsBuilder userName(String userName) {
    if (userName == null) {
      throw new NullPointerException("userName");
    }
    this.userName = userName;
    return this;
  }

  public String password() {
    return password;
  }

  public SensitiveFieldsBuilder password(String password) {
    if (password == null) {
      throw new NullPointerException("password");
    }
    this.password = password;
    return this;
  }

  public String token() {
    return token;
  }

  public SensitiveFieldsBuilder token(String token) {
    if (token == null) {
      throw new NullPointerException("token");
    }
    this.token = token;
    return this;
  }

  public SensitiveFields build() {
    return new Value(userName, password, token);
  }

  public static SensitiveFieldsBuilder from(SensitiveFields v) {
    return new SensitiveFieldsBuilder(v);
  }

  public static SensitiveFieldsBuilder from(SensitiveFieldsBuilder v) {
    return new SensitiveFieldsBuilder(v);
  }

  private static final class Value implements SensitiveFields {

    private final String userName;

    private final String password;

    private final String token;

    private Value(@AutoMatter.Field("userName") String userName,
        @AutoMatter.Field("password") String password,
        @AutoMatter.Field("token") String token) {
      if (userName == null) {
        throw new NullPointerException("userName");
      }
      if (password == null) {
        throw new NullPointerException("password");
      }
      if (token == null) {
        throw new NullPointerException("token");
      }
      this.userName = userName;
      this.password = password;
      this.token = token;
    }

    @AutoMatter.Field
    @Override
    public String userName() {
      return userName;
    }

    @AutoMatter.Field
    @Override
    public String password() {
      return password;
    }

    @AutoMatter.Field
    @Override
    public String token() {
      return token;
    }

    public SensitiveFieldsBuilder builder() {
      return new SensitiveFieldsBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof SensitiveFields)) {
        return false;
      }

      final SensitiveFields that = (SensitiveFields) o;

      if (userName != null ? !userName.equals(that.userName()) : that.userName() != null) {
        return false;
      }
      if (password != null ? !password.equals(that.password()) : that.password() != null) {
        return false;
      }
      if (token != null ? !token.equals(that.token()) : that.token() != null) {
        return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      long temp;

      result = 31 * result + (this.userName != null ? this.userName.hashCode() : 0);
      result = 31 * result + (this.password != null ? this.password.hashCode() : 0);
      result = 31 * result + (this.token != null ? this.token.hashCode() : 0);
      return result;
    }

    @Override
    public String toString() {
      return "SensitiveFields{" +
             "userName=" + userName +
             ", password=****" +
             ", token=...." +
             '}';
    }
  }
}
