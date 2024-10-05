package com.android.list_data.local

import androidx.paging.PagingSource
import androidx.room.withTransaction
import com.android.database.AppDatabase
import com.android.database.remote_keys.RepoRemoteKeysEntity
import com.android.database.repo.RepoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepoListLocalDataSourceImpl (
    private val database: AppDatabase
) : RepoListLocalDataSource {
    override  fun getRemoteKey(id: String): RepoRemoteKeysEntity {
        return database.repoRemoteKeysDao().getRemoteKey(id)

    }

    override suspend fun upsertCache(
        shouldClearCache: Boolean,
        remoteKeys: List<RepoRemoteKeysEntity>,
        repos: List<RepoEntity>
    ) {
        withContext(Dispatchers.IO) {
            database.withTransaction {
                if (shouldClearCache) {
                    database.repoListDao().deleteRepos()
                    database.repoRemoteKeysDao().deleteRemoteKeys()
                }

                database.repoRemoteKeysDao().addRemoteKeys(remoteKeys)

                database.repoListDao().addRepos(repos)
            }
        }
    }

    override fun getRemoteKeys(): PagingSource<Int, RepoEntity> {
        return database.repoListDao().getRepos()
    }
}