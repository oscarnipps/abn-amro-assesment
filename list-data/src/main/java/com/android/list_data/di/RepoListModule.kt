package com.android.list_data.di

import com.android.database.AppDatabase
import com.android.list_data.local.RepoListLocalDataSource
import com.android.list_data.local.RepoListLocalDataSourceImpl
import com.android.list_data.mappers.RepoMapper
import com.android.list_data.paging.RepoRemoteMediator
import com.android.list_data.remote.RepoListRemoteDataSource
import com.android.list_data.remote.RepoListRemoteDataSourceImpl
import com.android.list_data.remote.RepoService
import com.android.list_data.repo.RepoListRepository
import com.android.list_data.repo.RepoListRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepoListModule {
    @Provides
    @Singleton
    fun provideRepoRepository(
        repoMapper: RepoMapper,
        repoListLocalDataSource: RepoListLocalDataSource,
        repoListRemoteDataSource: RepoListRemoteDataSource,
    ): RepoListRepository {
        return RepoListRepositoryImpl(repoMapper, repoListRemoteDataSource, repoListLocalDataSource)
    }

    @Provides
    @Singleton
    fun provideRepoService(retrofit: Retrofit): RepoService {
        return retrofit.create(RepoService::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(repoService: RepoService): RepoListRemoteDataSource {
        return RepoListRemoteDataSourceImpl(repoService)
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(appDatabase: AppDatabase): RepoListLocalDataSource {
        return RepoListLocalDataSourceImpl(appDatabase)
    }

    @Provides
    @Singleton
    fun provideRepoMapper(): RepoMapper {
        return RepoMapper()
    }

    @Provides
    @Singleton
    fun provideRepoRemoteMediator(
        repoMapper: RepoMapper,
        repoListLocalDataSource: RepoListLocalDataSource,
        repoListRemoteDataSource: RepoListRemoteDataSource,
    ): RepoRemoteMediator {
        return RepoRemoteMediator(repoMapper, repoListRemoteDataSource, repoListLocalDataSource)
    }
}