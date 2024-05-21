package com.my.remotemediator.data

import com.my.remotemediator.database.entity.ArticleEntity
import com.my.remotemediator.database.entity.KeyEntity
import kotlin.random.Random

//fun ArticleDTO.toEntity() = ArticleEntity(
//    author = this.author,
//    title = this.title,
//    description = this.description,
//    url = this.url,
//    urlToImage = this.urlToImage,
//    publishedAt = this.publishedAt,
//    content = this.content
//)

fun ArticleEntity.toVo() = ArticleVo(
    id = this.id,
    author = this.author,
    title = this.title,
    description = this.description,
    url = this.url,
    urlToImage = this.urlToImage,
    publishedAt = this.publishedAt,
    content = this.content
)

fun ArticleEntity.toKey(
    nextPage: Int?, prevPage: Int?, currentPage: Int
) = KeyEntity(
    id = this.id,
    nextPage = nextPage,
    prevPage = prevPage,
    currentPage = currentPage,
)