package com.my.remotemediator.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.my.remotemediator.database.NewsDatabase
import com.my.remotemediator.database.entity.ArticleEntity
import com.my.remotemediator.paging.NewsRemoteMediator
import com.my.remotemediator.service.NewsApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface NewsRepository {
    fun getNewsStream(
        country: String,
        apiKey: String
    ): Pager<Int, ArticleEntity>
}

