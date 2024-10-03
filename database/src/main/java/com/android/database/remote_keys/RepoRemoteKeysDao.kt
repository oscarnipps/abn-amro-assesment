package com.android.database.remote_keys

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RepoRemoteKeysDao {
    @Query("SELECT * FROM repo_remote_keys WHERE id = :id ")
    suspend fun getRemoteKey(id: String): RepoRemoteKeysEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRemoteKeys(remoteKeys: List<RepoRemoteKeysEntity>)

    @Query("DELETE FROM repo_remote_keys")
    suspend fun deleteRemoteKeys()
}