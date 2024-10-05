package com.android.list_ui

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable


@Serializable
object RepoListRoute

fun NavGraphBuilder.repoListScreen(
    onRepoItemClicked: (String) -> Unit = {}
) {
    composable<RepoListRoute> {
        RepoListScreen(onRepoItemClicked = onRepoItemClicked)
    }
}