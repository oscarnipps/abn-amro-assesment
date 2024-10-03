package com.android.database.repo.list

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.database.repo.RepoEntity

@Dao
interface RepoListDao {
    @Query("SELECT * FROM repo")
    fun getRepos() : PagingSource<Int, RepoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRepos(repos : List<RepoEntity>)

    @Query("DELETE FROM repo")
    suspend fun deleteRepos()
}