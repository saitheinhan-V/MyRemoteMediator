package com.my.remotemediator.database

import android.content.Context
import androidx.room.Room
import com.my.remotemediator.database.dao.ArticleDao
import com.my.remotemediator.database.dao.KeyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsDatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NewsDatabase = Room.databaseBuilder(
        context, NewsDatabase::class.java, "article_db"
    ).build()

    @Provides
    @Singleton
    fun provideArticleDao(database: NewsDatabase): ArticleDao {
        return database.articleDao()
    }

    @Provides
    @Singleton
    fun provideKeyDao(database: NewsDatabase): KeyDao{
        return database.keyDao()
    }
}