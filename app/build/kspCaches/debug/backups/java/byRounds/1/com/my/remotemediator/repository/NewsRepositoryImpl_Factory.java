package com.my.remotemediator.repository;

import com.my.remotemediator.database.NewsDatabase;
import com.my.remotemediator.service.NewsApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class NewsRepositoryImpl_Factory implements Factory<NewsRepositoryImpl> {
  private final Provider<NewsApiService> newsApiServiceProvider;

  private final Provider<NewsDatabase> newsDatabaseProvider;

  public NewsRepositoryImpl_Factory(Provider<NewsApiService> newsApiServiceProvider,
      Provider<NewsDatabase> newsDatabaseProvider) {
    this.newsApiServiceProvider = newsApiServiceProvider;
    this.newsDatabaseProvider = newsDatabaseProvider;
  }

  @Override
  public NewsRepositoryImpl get() {
    return newInstance(newsApiServiceProvider.get(), newsDatabaseProvider.get());
  }

  public static NewsRepositoryImpl_Factory create(Provider<NewsApiService> newsApiServiceProvider,
      Provider<NewsDatabase> newsDatabaseProvider) {
    return new NewsRepositoryImpl_Factory(newsApiServiceProvider, newsDatabaseProvider);
  }

  public static NewsRepositoryImpl newInstance(NewsApiService newsApiService,
      NewsDatabase newsDatabase) {
    return new NewsRepositoryImpl(newsApiService, newsDatabase);
  }
}
