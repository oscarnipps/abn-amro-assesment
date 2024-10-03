package com.android.database.repo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.database.Constants.REPO_TABLE_NAME

@Entity(tableName = REPO_TABLE_NAME)
data class RepoEntity(
    @PrimaryKey(autoGenerate = false)
    val id : String,
    val name : String,
    val fullName : String,
    val ownerImageUrl : String,
    val htmlUrl : String,
    val description : String,
    val visibility : String,
    val isPrivate : Boolean,
)
