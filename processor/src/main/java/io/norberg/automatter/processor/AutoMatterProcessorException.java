package io.norberg.automatter.processor;

import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.tools.Diagnostic;

class AutoMatterProcessorException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private final Element element;

  public AutoMatterProcessorException(final String message, final Element element) {
    super(message);
    this.element = element;
  }

  public AutoMatterProcessorException(
      final String message, final Throwable cause, final Element element) {
    super(message, cause);
    this.element = element;
  }

  public AutoMatterProcessorException(final String message) {
    super(message);
    this.element = null;
  }

  public AutoMatterProcessorException(final String message, final Throwable cause) {
    super(message, cause);
    this.element = null;
  }

  public void print(Messager messager) {
    if (element != null) {
      messager.printMessage(Diagnostic.Kind.ERROR, getMessage(), element);
    } else {
      messager.printMessage(Diagnostic.Kind.ERROR, getMessage());
    }
  }
}
