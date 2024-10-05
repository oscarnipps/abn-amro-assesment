package com.android.list_ui

import androidx.paging.PagingData
import androidx.paging.map
import app.cash.turbine.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RepoListViewModelTest{

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule.create()

    private lateinit var repoListViewModel: RepoListViewModel

    @Before
    fun setUp() {
        repoListViewModel = RepoListViewModel(
            FakeRepoListRepository()
        )
    }

    @Test
    fun `when repo list is fetched then paging data is mapped correctly`() = runTest {
        val expectedRepoItem = Repo(
            id = "1",
            name = "ABN repo name 1",
            visibility = "ABN repo visibility 1",
            isPrivate = "ABN repo is private 1",
            ownerImageUrl = ""
        )

        repoListViewModel.repoList.test {
            val pagingData = awaitItem()

            val repoListItems = pagingData.collectData()

            val actualRepoItem = repoListItems[0]

            assert(actualRepoItem.id == expectedRepoItem.id)

            assert(actualRepoItem.name == expectedRepoItem.name)

            assert(actualRepoItem.visibility == expectedRepoItem.visibility)

            assert(actualRepoItem.isPrivate == expectedRepoItem.isPrivate)

            cancelAndIgnoreRemainingEvents()
        }

    }

    private suspend fun <T : Any> PagingData<T>.collectData(): List<T> {
        val items = mutableListOf<T>()
        this.map { items.add(it) }.collect()
        return items
    }
}