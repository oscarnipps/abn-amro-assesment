package com.android.database.repo.details

import androidx.room.Dao
import androidx.room.Query
import com.android.database.repo.RepoEntity

@Dao
interface RepoDetailsDao {
    @Query("SELECT * FROM repo WHERE id = :id ")
     fun getRepoDetails(id: String): RepoEntity
}