package io.norberg.automatter.processor;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;

import java.util.ArrayList;
import java.util.Collections;

import static io.norberg.automatter.processor.Common.assertNotNull;
import static io.norberg.automatter.processor.Common.builderType;
import static io.norberg.automatter.processor.Fields.fieldName;
import static io.norberg.automatter.processor.Fields.fieldType;
import static io.norberg.automatter.processor.Fields.isPrimitive;
import static io.norberg.automatter.processor.Fields.shouldEnforceNonNull;
import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PRIVATE;
import static javax.lang.model.element.Modifier.PUBLIC;
import static javax.lang.model.type.TypeKind.DECLARED;
import static javax.lang.model.type.TypeKind.TYPEVAR;

class DefaultProcessor implements FieldProcessor {
  @Override
  public FieldSpec builderField(Descriptor d, ExecutableElement field) throws AutoMatterProcessorException {
    return FieldSpec.builder(fieldType(d, field), fieldName(field), PRIVATE).build();
  }

  @Override
  public FieldSpec valueField(Descriptor d, ExecutableElement field) throws AutoMatterProcessorException {
    return FieldSpec.builder(fieldType(d, field), fieldName(field), PRIVATE, FINAL).build();
  }

  @Override
  public Iterable<MethodSpec> accessors(Descriptor d, ExecutableElement field) throws AutoMatterProcessorException {
    ArrayList<MethodSpec> methods = new ArrayList<>();
    methods.add(getter(d, field));
    methods.add(setter(d, field));
    return methods;
  }

  @Override
  public Iterable<Statement> defaultConstructor(Descriptor d, ExecutableElement field) {
    return Collections.emptyList();
  }

  @Override
  public Iterable<Statement> copyValueConstructor(Descriptor d, ExecutableElement field)
    throws AutoMatterProcessorException {
    ArrayList<Statement> statements = new ArrayList<>();
    String fieldName = fieldName(field);
    TypeName fieldType = fieldType(d, field);
    if (isFieldTypeParameterized(field)) {
      statements.add(new Statement("@SuppressWarnings(\"unchecked\") $T _$N = ($T) v.$N()",
                                   fieldType, fieldName, fieldType, fieldName));
      statements.add(new Statement("this.$N = _$N", fieldName, fieldName));
    } else {
      statements.add(new Statement("this.$N = v.$N()", fieldName, fieldName));
    }
    return statements;
  }

  @Override
  public Iterable<Statement> copyBuilderConstructor(Descriptor d, ExecutableElement field)
    throws AutoMatterProcessorException {
    ArrayList<Statement> statements = new ArrayList<>();
    String fieldName = fieldName(field);
    TypeName fieldType = fieldType(d, field);
    if (isFieldTypeParameterized(field)) {
      statements.add(new Statement("@SuppressWarnings(\"unchecked\") $T _$N = ($T) v.$N()",
                                   fieldType, fieldName, fieldType, fieldName));
      statements.add(new Statement("this.$N = _$N", fieldName, fieldName));
    } else {
      statements.add(new Statement("this.$N = v.$N", fieldName, fieldName));
    }
    return statements;
  }

  @Override
  public BuildStatements build(Descriptor d, ExecutableElement field) throws AutoMatterProcessorException {
    return new BuildStatements(Collections.emptyList(), fieldName(field));
  }

  @Override
  public Iterable<Statement> valueConstructor(Descriptor d, ExecutableElement field)
    throws AutoMatterProcessorException {
    String fieldName = fieldName(field);
    return Collections.singletonList(new Statement("this.$N = $N", fieldName, fieldName));
  }

  private boolean isFieldTypeParameterized(final ExecutableElement field) {
    final TypeMirror returnType = field.getReturnType();
    if (returnType.getKind() != DECLARED) {
      return false;
    }
    final DeclaredType declaredType = (DeclaredType) returnType;
    for (final TypeMirror typeArgument : declaredType.getTypeArguments()) {
      if (typeArgument.getKind() == TYPEVAR) {
        return true;
      }
    }
    return false;
  }

  private MethodSpec setter(final Descriptor d, final ExecutableElement field) throws AutoMatterProcessorException {
    String fieldName = fieldName(field);

    ParameterSpec.Builder parameterSpecBuilder =
            ParameterSpec.builder(fieldType(d, field), fieldName);
    if (!isPrimitive(field)) {
      AnnotationMirror nullableAnnotation = Fields.nullableAnnotation(field);
      if (nullableAnnotation != null) {
        parameterSpecBuilder.addAnnotation(AnnotationSpec.get(nullableAnnotation));
      }
    }
    MethodSpec.Builder setter = MethodSpec.methodBuilder(fieldName)
        .addModifiers(PUBLIC)
        .addParameter(parameterSpecBuilder.build())
        .returns(builderType(d));

    if (shouldEnforceNonNull(field)) {
      assertNotNull(setter, fieldName);
    }

    setter.addStatement("this.$N = $N", fieldName, fieldName);
    return setter.addStatement("return this").build();
  }

  protected MethodSpec getter(final Descriptor d, final ExecutableElement field) throws AutoMatterProcessorException {
    String fieldName = fieldName(field);

    MethodSpec.Builder getter = MethodSpec.methodBuilder(fieldName)
        .addModifiers(PUBLIC)
        .returns(fieldType(d, field));

    getter.addStatement("return $N", fieldName);

    return getter.build();
  }

}
