package com.android.details_ui

import com.android.details_data.entity.RepoDetail

sealed class RepoDetailScreenState {
    object Loading : RepoDetailScreenState()

    data class Success(val repoDetail: RepoDetail) : RepoDetailScreenState()

    object Error : RepoDetailScreenState()
}