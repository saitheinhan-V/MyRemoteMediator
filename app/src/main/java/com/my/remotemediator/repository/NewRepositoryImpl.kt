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

@ExperimentalPagingApi
class NewsRepositoryImpl @Inject constructor(
    private val newsApiService: NewsApiService,
    private val newsDatabase: NewsDatabase
): NewsRepository {
    override fun getNewsStream(country: String, apiKey: String): Pager<Int, ArticleEntity> {
        val pagingSourceFactory = { newsDatabase.articleDao().getAllArticles() }

        val config = PagingConfig(
            pageSize = 20, //load 20 item per page
            prefetchDistance = 5, //Starting loading next page when 5 item away from the end
            initialLoadSize = 30, //load 30 item initially -first time loading (must greater than pageSize)
            jumpThreshold = 1,
            enablePlaceholders = true, //placeholder for unloaded items
            maxSize = PagingConfig.MAX_SIZE_UNBOUNDED //Max number of item that should keep in memory
        )
        return Pager(
            config = config,
            remoteMediator = NewsRemoteMediator(newsApiService, newsDatabase, country, apiKey),
            pagingSourceFactory = pagingSourceFactory,
            initialKey = 1
        )
    }
}