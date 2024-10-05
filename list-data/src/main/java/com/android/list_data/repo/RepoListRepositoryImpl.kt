package com.android.list_data.repo

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.android.database.repo.RepoEntity
import com.android.list_data.local.RepoListLocalDataSource
import com.android.list_data.mappers.RepoMapper
import com.android.list_data.paging.RepoRemoteMediator
import com.android.list_data.remote.RepoListRemoteDataSource
import com.android.network.Constants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class RepoListRepositoryImpl @Inject constructor(
    private val mapper: RepoMapper,
    private val repoListRemoteDataSource: RepoListRemoteDataSource,
    private val repoListLocalDataSource: RepoListLocalDataSource
) : RepoListRepository{
    override fun getAllRepos(): Flow<PagingData<RepoEntity>> {
        val pagingSourceFactory = {repoListLocalDataSource.getRemoteKeys()}

        return Pager(
            config = PagingConfig(pageSize = Constants.PER_PAGE_SIZE),
            remoteMediator = RepoRemoteMediator(
                mapper = mapper,
                repoListRemoteDataSource = repoListRemoteDataSource,
                repoListLocalDataSource = repoListLocalDataSource,
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

}