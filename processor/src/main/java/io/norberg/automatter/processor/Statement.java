package io.norberg.automatter.processor;

class Statement {
  final String statement;
  final Object[] args;

  Statement(String statement, Object... args) {
    this.statement = statement;
    this.args = args;
  }
}
