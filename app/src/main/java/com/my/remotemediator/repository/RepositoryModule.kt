package com.my.remotemediator.repository

import androidx.paging.ExperimentalPagingApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @OptIn(ExperimentalPagingApi::class)
    @Binds
    @Singleton
    fun bindRepository(
        repo: NewsRepositoryImpl
    ): NewsRepository
}