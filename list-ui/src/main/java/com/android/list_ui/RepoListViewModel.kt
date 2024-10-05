package com.android.list_ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.android.list_data.repo.RepoListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class RepoListViewModel @Inject constructor(
    repoListRepository: RepoListRepository
) : ViewModel() {
    val repoList = repoListRepository.getAllRepos().map { pagingData ->
        pagingData.map {
            RepoListUiMapper.toRepoUiItem(it)
        }
    }.cachedIn(viewModelScope)
}