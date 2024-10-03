package com.android.database.remote_keys

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.database.Constants.REPO_REMOTE_KEYS_TABLE_NAME

// This table would be used by the 'RemoteMediator' to know which page to get from the api
@Entity(tableName = REPO_REMOTE_KEYS_TABLE_NAME)
data class RepoRemoteKeysEntity(
    @PrimaryKey(autoGenerate = false)
    val id : String,
    val prevPage : Int?,
    val nextPage : Int?
)
