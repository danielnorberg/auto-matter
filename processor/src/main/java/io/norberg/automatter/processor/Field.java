package io.norberg.automatter.processor;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.TypeMirror;

class Field {
  final ExecutableElement method;
  final TypeMirror type;

  Field(final ExecutableElement method, final TypeMirror type) {
    this.method = method;
    this.type = type;
  }
}
