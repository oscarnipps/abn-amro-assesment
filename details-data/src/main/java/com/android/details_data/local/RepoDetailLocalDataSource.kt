package com.android.details_data.local

import com.android.database.repo.RepoEntity

interface RepoDetailsLocalDataSource {
    //todo: refactor to fetch a subset from the database in a separate entity
    fun getRepoDetails(id: String): RepoEntity
}