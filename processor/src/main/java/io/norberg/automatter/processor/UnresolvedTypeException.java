package io.norberg.automatter.processor;

class UnresolvedTypeException extends AutoMatterProcessorException {

  private static final long serialVersionUID = 1L;

  UnresolvedTypeException(String type) {
    super("Unresolved type: " + type);
  }
}
