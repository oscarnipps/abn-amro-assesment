package com.android.list_data.remote

import com.android.list_data.entity.RepoDto
import com.android.network.Constants.REPO_LIST_ENDPOINT
import retrofit2.http.GET
import retrofit2.http.Query

interface RepoService {

    @GET(REPO_LIST_ENDPOINT)
    suspend fun getRepoListFromApi(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ) : ArrayList<RepoDto>
}