package com.android.list_ui

import androidx.paging.PagingData
import com.android.database.repo.RepoEntity
import com.android.list_data.repo.RepoListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeRepoListRepository : RepoListRepository {

    override fun getAllRepos(): Flow<PagingData<RepoEntity>> {
        val fakeRepoList = listOf(
            RepoEntity(
                id = "1",
                name = "ABN repo name 1",
                visibility = "ABN repo visibility 1",
                isPrivate = false,
                ownerImageUrl = "",
                fullName = "ABN repo full name 1",
                description = "ABN repo description 1",
                htmlUrl = ""
            )
        )

        return flowOf(PagingData.from(fakeRepoList))
    }
}