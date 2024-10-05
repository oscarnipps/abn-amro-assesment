package com.android.list_data.remote

import android.util.Log
import com.android.list_data.entity.RepoDto
import javax.inject.Inject

class RepoListRemoteDataSourceImpl @Inject constructor(
    private val repoService: RepoService
) : RepoListRemoteDataSource {
    override suspend fun getReposFromApi(page: Int, perPage: Int): List<RepoDto> {
        Log.d("response", repoService.getRepoListFromApi(page,perPage).toString())
        return repoService.getRepoListFromApi(page,perPage)
    }
}