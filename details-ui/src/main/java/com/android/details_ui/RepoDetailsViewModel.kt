package com.android.details_ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.details_data.repo.RepoDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoDetailsViewModel @Inject constructor(
    private val repository: RepoDetailsRepository
) : ViewModel() {
    private val _uiState : MutableStateFlow<RepoDetailScreenState> = MutableStateFlow(
        RepoDetailScreenState.Loading
    )

    val uiState = _uiState.asStateFlow()

    fun getRepoDetails(id: String) {
        //todo: create coroutine dispatcher module and inject here if there's time
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val repoDetail = repository.getRepoDetails(id)
                _uiState.update {
                    RepoDetailScreenState.Success(repoDetail)
                }
            }catch (e : Exception){
                _uiState.update { RepoDetailScreenState.Error }
            }
        }
    }
}