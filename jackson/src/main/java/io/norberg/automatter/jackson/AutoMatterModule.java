package io.norberg.automatter.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;

public class AutoMatterModule extends SimpleModule {

  private static final long serialVersionUID = 1L;

  @Override
  public void setupModule(final SetupContext context) {
    super.setupModule(context);
    final ValueTypeCache typeCache = new ValueTypeCache(context.getTypeFactory());
    context.addAbstractTypeResolver(new AutoMatterResolver(typeCache));
    context.appendAnnotationIntrospector(new AutoMatterAnnotationIntrospector(typeCache));
  }
}
