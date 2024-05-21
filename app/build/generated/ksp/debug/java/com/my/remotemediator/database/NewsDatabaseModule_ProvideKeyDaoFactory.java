package com.my.remotemediator.database;

import com.my.remotemediator.database.dao.KeyDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
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
public final class NewsDatabaseModule_ProvideKeyDaoFactory implements Factory<KeyDao> {
  private final Provider<NewsDatabase> databaseProvider;

  public NewsDatabaseModule_ProvideKeyDaoFactory(Provider<NewsDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public KeyDao get() {
    return provideKeyDao(databaseProvider.get());
  }

  public static NewsDatabaseModule_ProvideKeyDaoFactory create(
      Provider<NewsDatabase> databaseProvider) {
    return new NewsDatabaseModule_ProvideKeyDaoFactory(databaseProvider);
  }

  public static KeyDao provideKeyDao(NewsDatabase database) {
    return Preconditions.checkNotNullFromProvides(NewsDatabaseModule.INSTANCE.provideKeyDao(database));
  }
}
