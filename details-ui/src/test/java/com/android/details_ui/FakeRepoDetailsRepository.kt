package com.android.details_ui

import com.android.details_data.entity.RepoDetail
import com.android.details_data.repo.RepoDetailsRepository

class FakeRepoDetailsRepository : RepoDetailsRepository {
    override suspend fun getRepoDetails(id: String) : RepoDetail {

        if (id.isEmpty()) {
            throw Exception("Error getting detail repo")
        }

        return RepoDetail(
            id = "1",
            name = "ABN repo name",
            fullName = "ABN repo full name",
            ownerImageUrl = "",
            htmlUrl = "",
            description = "ABN repo description",
            visibility = "ABN repo visibility",
            isPrivate = "ABN  repo is private"
        )
    }
}