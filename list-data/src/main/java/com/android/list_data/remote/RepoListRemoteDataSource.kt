package com.android.list_data.remote

import com.android.list_data.entity.RepoDto

interface RepoListRemoteDataSource {
    suspend fun getReposFromApi(page :Int , perPage : Int) : List<RepoDto>
}