package com.my.remotemediator.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.my.remotemediator.database.entity.ArticleEntity
import kotlinx.serialization.Serializable
import kotlin.random.Random

data class NewsResponse(
    @SerializedName("status") val status: String,
    @SerializedName("totalResults") val totalResults: Int,
    @SerializedName("articles") val articles: List<ArticleEntity>
)

data class ArticleDTO(
//    @SerializedName("source") val source: Source,
    @SerializedName("author") val author: String?,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String?,
    @SerializedName("url") val url: String,
    @SerializedName("urlToImage") val urlToImage: String?,
    @SerializedName("publishedAt") val publishedAt: String,
    @SerializedName("content") val content: String?
)

data class Source(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String
)
