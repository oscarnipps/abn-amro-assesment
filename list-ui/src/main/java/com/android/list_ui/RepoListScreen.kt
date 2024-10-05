package com.android.list_ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.flowOf

@Composable
fun RepoListScreen(
    repoListViewModel: RepoListViewModel = hiltViewModel(),
    onRepoItemClicked: (String) -> Unit = {}
) {
    val context = LocalContext.current

    val repoItems = repoListViewModel.repoList.collectAsLazyPagingItems()

    val listState = rememberLazyListState()

    val networkStatusTracker = remember { AppNetworkMonitor(context) }

    val isOnline by networkStatusTracker.isOnline.collectAsState(initial = false)

    LaunchedEffect(key1 = isOnline) {
        if (isOnline) {
            Log.d("AppNetwork", "network is on")
            repoItems.refresh()
        }
    }

    // if need be , handle unhappy scenarios for when there are errors
    // via the 'repoItems.loadState' i.e progress bar for refresh loading or error state for append error
    // for now only show content
    RpoListItemContent(
        repoItems = repoItems,
        listState = listState,
        onRepoItemClicked = onRepoItemClicked
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RpoListItemContent(
    modifier: Modifier = Modifier,
    repoItems: LazyPagingItems<Repo>,
    listState: LazyListState,
    onRepoItemClicked: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = {
                Text(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp),
                    text = stringResource(R.string.repo_list_app_bar_title),
                    fontSize = 32.sp
                )
            }
        )

        LazyColumn(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            state = listState
        ) {
            items(count = repoItems.itemCount) { index ->
                val repo = repoItems.get(index = index)
                repo?.let {
                    RepoListItem(
                        repo = it,
                        onRepoItemClicked = onRepoItemClicked,
                    )
                }
            }

            item {
                if (repoItems.loadState.append is LoadState.Loading) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RepoListScreenPreview() {
    RpoListItemContent(
        repoItems = flowOf(
            PagingData.from(
                listOf(
                    Repo(
                        id = "",
                        name = "ABN repo name 1",
                        visibility = "ABN repo visibility 1",
                        isPrivate = "ABN repo is private 1",
                        ownerImageUrl = ""
                    ),
                    Repo(
                        id = "",
                        name = "ABN repo name 2",
                        visibility = "ABN repo visibility 2",
                        isPrivate = "ABN repo is private 2",
                        ownerImageUrl = ""
                    ),
                    Repo(
                        id = "",
                        name = "ABN repo name 3",
                        visibility = "ABN repo visibility 3",
                        isPrivate = "ABN repo is private 3",
                        ownerImageUrl = ""
                    )
                )
            )
        ).collectAsLazyPagingItems(),
        listState = rememberLazyListState(),
        onRepoItemClicked = {}
    )
}