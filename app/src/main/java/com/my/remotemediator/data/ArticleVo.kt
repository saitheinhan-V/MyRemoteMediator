package com.my.remotemediator.data

import kotlin.random.Random

data class ArticleVo(
    val id: Int,
//    val source: Source,
    val author: String?="",
    val title: String,
    val description: String?="",
    val url: String,
    val urlToImage: String?="",
    val publishedAt: String?="",
    val content: String?=""
)