package com.android.list_data.mappers

import com.android.database.remote_keys.RepoRemoteKeysEntity
import com.android.database.repo.RepoEntity
import com.android.list_data.entity.RepoDto
import javax.inject.Inject

class RepoMapper @Inject constructor() {
    fun mapToRepoRemoteKeyEntityList(
        repoDtoList: List<RepoDto>,
        prevPage: Int?,
        nextPage: Int?
    ): List<RepoRemoteKeysEntity> {
        if (repoDtoList.isEmpty()) {
            return emptyList()
        }

        return repoDtoList
            .filter { it.id != null }   //ensure only entries with id's
            .map { dto ->
                RepoRemoteKeysEntity(
                    id = dto.id.toString(),
                    prevPage = prevPage,
                    nextPage = nextPage
                )
            }
    }

    fun mapToRepoEntityList(repoDtoList: List<RepoDto>): List<RepoEntity> {
        if (repoDtoList.isEmpty()) {
            return emptyList()
        }

        return repoDtoList
            .filter { it.id != null }
            .map { dto ->
                RepoEntity(
                    id = dto.id.toString(),
                    name = dto.name.orEmpty(),
                    fullName = dto.fullName.orEmpty(),
                    ownerImageUrl = dto.owner?.avatarUrl.orEmpty(),
                    description = dto.description.orEmpty(),
                    htmlUrl = dto.htmlUrl.orEmpty(),
                    visibility = dto.visibility.orEmpty(),
                    isPrivate = dto.private ?: false
                )
            }
    }
}