package io.norberg.automatter.processor;

class UnresolvedTypeException extends AutoMatterProcessorException {

  UnresolvedTypeException(String type) {
    super("Unresolved type: " + type);
  }
}
