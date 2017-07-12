package io.norberg.automatter.processor;

class BuildStatements {
  final Iterable<Statement> statements;
  final String parameter;

  BuildStatements(Iterable<Statement> statements, String parameter) {
    this.statements = statements;
    this.parameter = parameter;
  }
}
