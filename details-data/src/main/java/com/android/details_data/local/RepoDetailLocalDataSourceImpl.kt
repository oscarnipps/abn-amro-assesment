package com.android.details_data.local

import com.android.database.AppDatabase
import com.android.database.repo.RepoEntity
import javax.inject.Inject


class RepoDetailLocalDataSourceImpl @Inject constructor(
    private val database: AppDatabase
) : RepoDetailsLocalDataSource {
    override fun getRepoDetails(id: String): RepoEntity {
        return database.repoDetailsDao().getRepoDetails(id)
    }
}