package com.android.details_data.repo

import com.android.details_data.entity.RepoDetail
import com.android.details_data.local.RepoDetailsLocalDataSource
import com.android.details_data.mappers.RepoDetailMapper
import javax.inject.Inject

class RepoDetailsRepositoryImpl @Inject constructor(
    private val localDataSource: RepoDetailsLocalDataSource,
    private val mapper: RepoDetailMapper,
) : RepoDetailsRepository {
    override suspend fun getRepoDetails(id: String): RepoDetail {
        return mapper.mapToRepoRepoDetails(
            localDataSource.getRepoDetails(id)
        )
    }
}