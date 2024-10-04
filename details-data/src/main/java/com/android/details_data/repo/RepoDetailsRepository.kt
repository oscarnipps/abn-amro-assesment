package com.android.details_data.repo

import com.android.details_data.entity.RepoDetail

interface RepoDetailsRepository {
   suspend fun getRepoDetails(id : String) : RepoDetail
}