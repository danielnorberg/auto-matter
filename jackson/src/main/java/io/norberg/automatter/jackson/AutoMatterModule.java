package io.norberg.automatter.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;

public class AutoMatterModule extends SimpleModule {

  @Override
  public void setupModule(final SetupContext context) {
    super.setupModule(context);
    context.addAbstractTypeResolver(new AutoMatterResolver());
  }
}
