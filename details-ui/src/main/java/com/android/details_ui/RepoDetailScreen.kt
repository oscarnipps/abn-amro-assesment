package com.android.details_ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.android.details_data.entity.RepoDetail

@Composable
fun RepoDetailScreen(
    repoDetailsViewModel: RepoDetailsViewModel = hiltViewModel(),
    repoId: String,
    onBackNavigationClicked: () -> Unit = {},
    onViewProfileButtonClicked: (String) -> Unit = {}
) {
    val uiState = repoDetailsViewModel.uiState.collectAsStateWithLifecycle()

    repoDetailsViewModel.getRepoDetails(id = repoId)

    when (val state = uiState.value) {
        RepoDetailScreenState.Error -> {
            //todo: show error state view
        }

        RepoDetailScreenState.Loading -> {
            //todo: show loading composable
        }

        is RepoDetailScreenState.Success -> {
            RepoDetailScreenContent(
                onBackNavigationClicked = onBackNavigationClicked,
                onViewProfileButtonClicked = onViewProfileButtonClicked,
                repoDetail = state.repoDetail
            )
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun RepoDetailScreenContent(
    modifier: Modifier = Modifier,
    onBackNavigationClicked: () -> Unit = {},
    onViewProfileButtonClicked: (String) -> Unit = {},
    repoDetail: RepoDetail
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        TopAppBar(
            title = {
                Text(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp),
                    text = stringResource(R.string.repo_details_app_bar_title),
                    fontSize = 32.sp
                )
            },
            navigationIcon = {
                IconButton(onClick = {
                    onBackNavigationClicked()
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack, contentDescription = stringResource(
                            R.string.content_description_navigate_back
                        )
                    )
                }
            }
        )

        RepoDetailImage(imageUrl = repoDetail.ownerImageUrl)

        RepoDetailItemText(
            title = stringResource(R.string.detail_name_title),
            subtitle = repoDetail.name
        )

        RepoDetailItemText(
            title = stringResource(R.string.detail_full_name_title),
            subtitle = repoDetail.fullName
        )

        RepoDetailItemText(
            title = stringResource(R.string.detail_description_title),
            subtitle = repoDetail.description
        )

        RepoDetailItemText(
            title = stringResource(R.string.detail_visibility_title),
            subtitle = repoDetail.visibility
        )

        RepoDetailItemText(
            title = stringResource(R.string.detail_repo_private_title),
            subtitle = repoDetail.isPrivate
        )

        Button(
            modifier = modifier.align(Alignment.CenterHorizontally),
            onClick = { onViewProfileButtonClicked(repoDetail.htmlUrl) }
        ) {
            Text(text = stringResource(R.string.view_github_profile))
        }
    }
}

//todo: refactor to a common module so as to make reusable across
@Composable
private fun RepoDetailImage(
    modifier: Modifier = Modifier,
    imageUrl: String

) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .diskCachePolicy(CachePolicy.ENABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .error(LocalContext.current.getDrawable(R.drawable.default_repo_icon))
            .build()
    )

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = modifier
                .size(100.dp)
                .clip(CircleShape),
            painter = painter,
            contentDescription = "ABN repo detail profile image",
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
private fun RepoDetailItemText(
    modifier: Modifier = Modifier,
    title: String = "",
    subtitle: String = "",
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = modifier,
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            text = title
        )

        Text(
            modifier = modifier,
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            text = subtitle
        )

        Spacer(modifier = modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun RepoListScreenPreview() {
    RepoDetailScreenContent(
        onBackNavigationClicked = {},
        repoDetail = RepoDetail(
            id = " ",
            name = "ABN repo name",
            fullName = "ABN repo full name",
            ownerImageUrl = "",
            htmlUrl = "",
            description = "ABN repo description",
            visibility = "ABN repo visibility",
            isPrivate = "ABN  repo is private"
        )
    )
}