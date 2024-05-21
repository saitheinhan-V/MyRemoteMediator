package com.my.remotemediator.database;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class NewsDatabaseModule_ProvideDatabaseFactory implements Factory<NewsDatabase> {
  private final Provider<Context> contextProvider;

  public NewsDatabaseModule_ProvideDatabaseFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public NewsDatabase get() {
    return provideDatabase(contextProvider.get());
  }

  public static NewsDatabaseModule_ProvideDatabaseFactory create(
      Provider<Context> contextProvider) {
    return new NewsDatabaseModule_ProvideDatabaseFactory(contextProvider);
  }

  public static NewsDatabase provideDatabase(Context context) {
    return Preconditions.checkNotNullFromProvides(NewsDatabaseModule.INSTANCE.provideDatabase(context));
  }
}
