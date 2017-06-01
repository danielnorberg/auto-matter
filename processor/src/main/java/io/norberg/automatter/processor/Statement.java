package io.norberg.automatter.processor;

public class Statement {
  String statement;
  Object[] args;

  public Statement(String statement, Object... args) {
    this.statement = statement;
    this.args = args;
  }
}
