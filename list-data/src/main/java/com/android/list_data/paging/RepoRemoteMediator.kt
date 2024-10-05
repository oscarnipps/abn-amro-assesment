package com.android.list_data.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.android.database.remote_keys.RepoRemoteKeysEntity
import com.android.database.repo.RepoEntity
import com.android.list_data.local.RepoListLocalDataSource
import com.android.list_data.mappers.RepoMapper
import com.android.list_data.remote.RepoListRemoteDataSource
import com.android.network.Constants.INITIAL_PAGE_SIZE
import com.android.network.Constants.PER_PAGE_SIZE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class RepoRemoteMediator @Inject constructor(
    private val mapper: RepoMapper,
    private val repoListRemoteDataSource: RepoListRemoteDataSource,
    private val repoListLocalDataSource: RepoListLocalDataSource
) : RemoteMediator<Int, RepoEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RepoEntity>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: INITIAL_PAGE_SIZE
                }

                //out of scope for assessment
                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyTheForLastItem(state)
                    val nextPage = remoteKeys?.nextPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    nextPage
                }
            }

            val response =
                repoListRemoteDataSource.getReposFromApi(page = currentPage, perPage = PER_PAGE_SIZE)

            val endOfPaginationReached = response.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1

            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            repoListLocalDataSource.upsertCache(
                shouldClearCache = loadType == LoadType.REFRESH,
                remoteKeys = mapper.mapToRepoRemoteKeyEntityList(
                    repoDtoList = response,
                    prevPage = prevPage,
                    nextPage = nextPage
                ),
                repos = mapper.mapToRepoEntityList(response)
            )

            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            //todo: only return success if exception is http
            Log.d("RemoteMediator", "exception $e")
            MediatorResult.Success(endOfPaginationReached = true)
            //MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, RepoEntity>
    ): RepoRemoteKeysEntity? {
        // this is the position of the item closest to the viewport
        val anchorPosition = state.anchorPosition ?: return null

        val closestItem = state.closestItemToPosition(anchorPosition) ?: return null

        return withContext(Dispatchers.IO) {
            repoListLocalDataSource.getRemoteKey(id = closestItem.id)
        }
    }

    private suspend fun getRemoteKeyTheForLastItem(
        state: PagingState<Int, RepoEntity>
    ): RepoRemoteKeysEntity? {
        val lastPageWithData = state.pages.lastOrNull { it.data.isNotEmpty() } ?: return null

        val lastItemInPage = lastPageWithData.data.lastOrNull() ?: return null

        return withContext(Dispatchers.IO) {
            repoListLocalDataSource.getRemoteKey(id = lastItemInPage.id)
        }
    }
}