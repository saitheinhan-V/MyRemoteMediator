package com.my.remotemediator.paging;

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
public final class NewsRemoteMediator_Factory implements Factory<NewsRemoteMediator> {
  private final Provider<NewsApiService> newsApiServiceProvider;

  private final Provider<NewsDatabase> newsDatabaseProvider;

  private final Provider<String> countryProvider;

  private final Provider<String> apiKeyProvider;

  public NewsRemoteMediator_Factory(Provider<NewsApiService> newsApiServiceProvider,
      Provider<NewsDatabase> newsDatabaseProvider, Provider<String> countryProvider,
      Provider<String> apiKeyProvider) {
    this.newsApiServiceProvider = newsApiServiceProvider;
    this.newsDatabaseProvider = newsDatabaseProvider;
    this.countryProvider = countryProvider;
    this.apiKeyProvider = apiKeyProvider;
  }

  @Override
  public NewsRemoteMediator get() {
    return newInstance(newsApiServiceProvider.get(), newsDatabaseProvider.get(), countryProvider.get(), apiKeyProvider.get());
  }

  public static NewsRemoteMediator_Factory create(Provider<NewsApiService> newsApiServiceProvider,
      Provider<NewsDatabase> newsDatabaseProvider, Provider<String> countryProvider,
      Provider<String> apiKeyProvider) {
    return new NewsRemoteMediator_Factory(newsApiServiceProvider, newsDatabaseProvider, countryProvider, apiKeyProvider);
  }

  public static NewsRemoteMediator newInstance(NewsApiService newsApiService,
      NewsDatabase newsDatabase, String country, String apiKey) {
    return new NewsRemoteMediator(newsApiService, newsDatabase, country, apiKey);
  }
}
