package com.android.list_data.repo

import androidx.paging.PagingData
import com.android.database.repo.RepoEntity
import kotlinx.coroutines.flow.Flow

interface RepoListRepository {
    fun getAllRepos() : Flow<PagingData<RepoEntity>>
}