package com.my.remotemediator.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.random.Random

@Entity(tableName = ArticleEntity.ARTICLE_TABLE)
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Int,
//    val source: Source,
    @ColumnInfo("author")
    val author: String?,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("description")
    val description: String?,
    @ColumnInfo("url")
    val url: String,
    @ColumnInfo("urlToImage")
    val urlToImage: String?,
    @ColumnInfo("publishedAt")
    val publishedAt: String,
    @ColumnInfo("content")
    val content: String?
){
    companion object{
        const val ARTICLE_TABLE = "article"
    }
}