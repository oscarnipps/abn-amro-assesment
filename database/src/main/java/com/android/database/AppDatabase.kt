package com.android.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.database.repo.details.RepoDetailsDao
import com.android.database.repo.list.RepoListDao
import com.android.database.remote_keys.RepoRemoteKeysDao
import com.android.database.remote_keys.RepoRemoteKeysEntity
import com.android.database.repo.RepoEntity

@Database(
    entities = [
        RepoEntity::class,
        RepoRemoteKeysEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun repoListDao() : RepoListDao
    abstract fun repoDetailsDao() : RepoDetailsDao
    abstract fun repoRemoteKeysDao() : RepoRemoteKeysDao
}