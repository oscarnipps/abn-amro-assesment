package com.android.details_data.di

import com.android.database.AppDatabase
import com.android.details_data.local.RepoDetailLocalDataSourceImpl
import com.android.details_data.local.RepoDetailsLocalDataSource
import com.android.details_data.mappers.RepoDetailMapper
import com.android.details_data.repo.RepoDetailsRepository
import com.android.details_data.repo.RepoDetailsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepoDetailsModule {

    @Provides
    @Singleton
    fun provideRepoDetailsLocalDataSource(appDatabase: AppDatabase): RepoDetailsLocalDataSource {
        return RepoDetailLocalDataSourceImpl(appDatabase)
    }

    @Provides
    @Singleton
    fun provideRepoDetailsRepository(
        localDataSource: RepoDetailsLocalDataSource,
        repoDetailMapper: RepoDetailMapper
    ): RepoDetailsRepository {
        return RepoDetailsRepositoryImpl(localDataSource, repoDetailMapper)
    }

    @Provides
    @Singleton
    fun provideRepoDetailsMapper(): RepoDetailMapper {
        return RepoDetailMapper()
    }
}