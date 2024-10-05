package com.android.abn_amro_assesment

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.paging.ExperimentalPagingApi
import com.android.details_ui.navigateToRepoDetails
import com.android.details_ui.repoDetailScreen
import com.android.list_ui.RepoListRoute
import com.android.list_ui.repoListScreen


@ExperimentalPagingApi
@Composable
fun RepoAppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = RepoListRoute
    ) {
        repoListScreen(
            onRepoItemClicked = { id -> navController.navigateToRepoDetails(id) }
        )

        repoDetailScreen(
            onBackNavigationClicked = { navController.popBackStack()}
        )
    }
}