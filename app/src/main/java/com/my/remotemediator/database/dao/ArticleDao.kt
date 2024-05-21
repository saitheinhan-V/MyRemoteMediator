package com.my.remotemediator.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.my.remotemediator.database.entity.ArticleEntity

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(articles: List<ArticleEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: ArticleEntity)

    @Query("SELECT * FROM ${ArticleEntity.ARTICLE_TABLE}")
    fun getAllArticles(): PagingSource<Int, ArticleEntity>

    @Query("SELECT * FROM ${ArticleEntity.ARTICLE_TABLE}")
    fun getArticleList(): List<ArticleEntity>

    @Query("DELETE FROM ${ArticleEntity.ARTICLE_TABLE}")
    suspend fun clearAll()
}
