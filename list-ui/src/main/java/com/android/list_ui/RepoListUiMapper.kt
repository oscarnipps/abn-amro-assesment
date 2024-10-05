package com.android.list_ui

import com.android.database.repo.RepoEntity

object RepoListUiMapper {
    fun toRepoUiItem(repoEntity : RepoEntity) : Repo{
        return Repo(
            id = repoEntity.id,
            name = repoEntity.name,
            visibility = repoEntity.visibility,
            isPrivate = repoEntity.isPrivate.toString(),
            ownerImageUrl = repoEntity.ownerImageUrl
        )
    }
}