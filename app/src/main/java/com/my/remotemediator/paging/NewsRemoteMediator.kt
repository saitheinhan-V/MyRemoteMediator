package com.my.remotemediator.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.my.remotemediator.data.toKey
import com.my.remotemediator.database.NewsDatabase
import com.my.remotemediator.database.entity.ArticleEntity
import com.my.remotemediator.database.entity.KeyEntity
import com.my.remotemediator.service.NewsApiService
import kotlinx.coroutines.delay
import javax.inject.Inject

@ExperimentalPagingApi
class NewsRemoteMediator @Inject constructor(
    private val newsApiService: NewsApiService,
    private val newsDatabase: NewsDatabase,
    private val country: String,
    private val apiKey: String
) : RemoteMediator<Int, ArticleEntity>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ArticleEntity>
    ): MediatorResult {

        val pageState = getPage(
            loadType = loadType,
            state = state
        )

        val currentPage = when (pageState) {
            is PageState.Append -> pageState.page ?: return MediatorResult.Success(
                endOfPaginationReached = false
            )

            is PageState.Prepend -> pageState.page ?: return MediatorResult.Success(
                endOfPaginationReached = true
            )

            is PageState.Refresh -> pageState.page
        }

        return try {
            delay(1000)
            val response = newsApiService.getTopHeadlines(
                country,
                currentPage,
                state.config.pageSize,
                apiKey
            )
            val endOfPagination = response.articles.isEmpty()

            newsDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    newsDatabase.articleDao().clearAll()
                    newsDatabase.keyDao().deleteAll()
                }

                val prevKey = if (currentPage > 1) currentPage - 1 else null
                val nextKey = if (endOfPagination) null else currentPage + 1
                val articleEntity = response.articles

                newsDatabase.articleDao().insertAll(articleEntity)

                val articleList = newsDatabase.articleDao().getArticleList()

                val keys = articleList.map {
                    it.toKey(
                        nextPage = nextKey,
                        prevPage = prevKey,
                        currentPage = currentPage
                    )
                }
                newsDatabase.keyDao().insertKeys(keys)

                Log.i("mediator.page", currentPage.toString()+" Size = "+response.articles.size)

            }
            MediatorResult.Success(endOfPaginationReached = endOfPagination)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getPage(
        loadType: LoadType,
        state: PagingState<Int, ArticleEntity>
    ): PageState {

        return when (loadType) {

            // loading
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                val page = remoteKeys?.nextPage?.minus(1) ?: 1
                PageState.Refresh(page = page)
            }

            // has data, load more
            LoadType.APPEND -> {
                val remoteKeys = getLastRemoteKey(state)
                val page = remoteKeys?.nextPage
                PageState.Append(page = page)
            }

            // has data, load previous
            LoadType.PREPEND -> {
                val remoteKeys = getFirstRemoteKey(state)
                val page = remoteKeys?.prevPage
                PageState.Prepend(page = page)
            }
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, ArticleEntity>): KeyEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                newsDatabase.keyDao().getKey(id)
            }
        }
    }

    private suspend fun getLastRemoteKey(state: PagingState<Int, ArticleEntity>): KeyEntity? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { id ->
                newsDatabase.keyDao().getKey(id.id)
            }
    }

    private suspend fun getFirstRemoteKey(state: PagingState<Int, ArticleEntity>): KeyEntity? {
        return state.pages
            .firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { id ->
                newsDatabase.keyDao().getKey(id.id)
            }

    }
}
