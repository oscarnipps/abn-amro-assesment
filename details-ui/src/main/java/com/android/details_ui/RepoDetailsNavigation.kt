package com.android.details_ui

import android.content.Intent
import android.net.Uri
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
data class RepoDetailsRoute(val id: String)

fun NavController.navigateToRepoDetails(id: String, navOptions: NavOptionsBuilder.() -> Unit = {}) {
    navigate(route = RepoDetailsRoute(id), navOptions)
}

fun NavGraphBuilder.repoDetailScreen(
    onBackNavigationClicked: () -> Unit = {}
) {
    composable<RepoDetailsRoute> { backStackEntry ->
        val repoId = backStackEntry.toRoute<RepoDetailsRoute>()
        val context = LocalContext.current
        RepoDetailScreen(
            repoId = repoId.id,
            onBackNavigationClicked = onBackNavigationClicked,
            onViewProfileButtonClicked = { uri ->
                val browserIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(uri)
                )
                startActivity(context, browserIntent, null)
            }
        )
    }
}