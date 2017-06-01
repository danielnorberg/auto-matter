package io.norberg.automatter.processor;

public class BuildStatements {
  Iterable<Statement> statements;
  String parameter;

  public BuildStatements(Iterable<Statement> statements, String parameter) {
    this.statements = statements;
    this.parameter = parameter;
  }
}
