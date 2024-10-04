package com.android.details_data.mappers

import com.android.database.remote_keys.RepoRemoteKeysEntity
import com.android.database.repo.RepoEntity
import com.android.details_data.entity.RepoDetail
import javax.inject.Inject

class RepoDetailMapper @Inject constructor() {
    fun mapToRepoRepoDetails(
        repoEntity: RepoEntity
    ): RepoDetail {
        return RepoDetail(
            id = repoEntity.id,
            name = repoEntity.name,
            fullName = repoEntity.fullName,
            ownerImageUrl = repoEntity.ownerImageUrl,
            htmlUrl = repoEntity.htmlUrl,
            description = repoEntity.description,
            visibility = repoEntity.visibility,
            isPrivate = repoEntity.isPrivate.toString(),
        )
    }
}