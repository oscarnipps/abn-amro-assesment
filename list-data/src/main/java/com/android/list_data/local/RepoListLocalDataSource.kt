package com.android.list_data.local

import androidx.paging.PagingSource
import com.android.database.remote_keys.RepoRemoteKeysEntity
import com.android.database.repo.RepoEntity

interface RepoListLocalDataSource {
    fun getRemoteKey(id: String): RepoRemoteKeysEntity

    suspend fun upsertCache(
        shouldClearCache: Boolean,
        remoteKeys: List<RepoRemoteKeysEntity>,
        repos: List<RepoEntity>
    )

    fun getRemoteKeys() : PagingSource<Int, RepoEntity>
}