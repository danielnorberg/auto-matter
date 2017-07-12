package io.norberg.automatter.processor;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;

import javax.lang.model.element.ExecutableElement;

interface FieldProcessor {
  FieldSpec builderField(Descriptor d, ExecutableElement field) throws AutoMatterProcessorException;
  FieldSpec valueField(Descriptor d, ExecutableElement field) throws AutoMatterProcessorException;

  Iterable<MethodSpec> accessors(Descriptor d, ExecutableElement field) throws AutoMatterProcessorException;

  Iterable<Statement> defaultConstructor(Descriptor d, ExecutableElement field);
  Iterable<Statement> copyValueConstructor(Descriptor d, ExecutableElement field) throws AutoMatterProcessorException;
  Iterable<Statement> copyBuilderConstructor(Descriptor d, ExecutableElement field) throws AutoMatterProcessorException;
  BuildStatements build(Descriptor d, ExecutableElement field) throws AutoMatterProcessorException;
  Iterable<Statement> valueConstructor(Descriptor d, ExecutableElement field) throws AutoMatterProcessorException;
}
