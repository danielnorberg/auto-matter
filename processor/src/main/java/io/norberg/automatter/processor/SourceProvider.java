package io.norberg.automatter.processor;

import com.sun.tools.javac.code.Symbol.ClassSymbol;
import java.io.IOException;
import java.util.Objects;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;

class SourceProvider {

  private final ProcessingEnvironment processingEnvironment;

  SourceProvider(ProcessingEnvironment processingEnvironment) {
    this.processingEnvironment = Objects.requireNonNull(processingEnvironment, "processingEnvironment");
  }

  String source(TypeElement element) {
    // TODO: do not rely on javac internals
    try {
      return ((ClassSymbol) element).sourcefile.getCharContent(true).toString();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
