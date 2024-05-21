package com.my.remotemediator.database;

import com.my.remotemediator.database.dao.ArticleDao;
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
public final class NewsDatabaseModule_ProvideArticleDaoFactory implements Factory<ArticleDao> {
  private final Provider<NewsDatabase> databaseProvider;

  public NewsDatabaseModule_ProvideArticleDaoFactory(Provider<NewsDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public ArticleDao get() {
    return provideArticleDao(databaseProvider.get());
  }

  public static NewsDatabaseModule_ProvideArticleDaoFactory create(
      Provider<NewsDatabase> databaseProvider) {
    return new NewsDatabaseModule_ProvideArticleDaoFactory(databaseProvider);
  }

  public static ArticleDao provideArticleDao(NewsDatabase database) {
    return Preconditions.checkNotNullFromProvides(NewsDatabaseModule.INSTANCE.provideArticleDao(database));
  }
}
