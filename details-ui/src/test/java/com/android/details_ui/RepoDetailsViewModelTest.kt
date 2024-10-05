package com.android.details_ui

import app.cash.turbine.test
import com.android.details_data.entity.RepoDetail
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RepoDetailsViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule.create()

    private lateinit var viewModel: RepoDetailsViewModel

    @Before
    fun setUp() {
        viewModel = RepoDetailsViewModel(FakeRepoDetailsRepository())
    }

    @Test
    fun `when repo detail is fetched then ui state is success `() =
        runTest(StandardTestDispatcher()) {
            val expectedRepoDetail = RepoDetail(
                id = "1",
                name = "ABN repo name",
                fullName = "ABN repo full name",
                ownerImageUrl = "",
                htmlUrl = "",
                description = "ABN repo description",
                visibility = "ABN repo visibility",
                isPrivate = "ABN  repo is private"
            )

            viewModel.getRepoDetails("1")

            viewModel.uiState.test {
                assertEquals(RepoDetailScreenState.Loading, awaitItem())

                val actualRepoDetail = awaitItem()

                assert(actualRepoDetail is RepoDetailScreenState.Success)

                assertEquals(actualRepoDetail, expectedRepoDetail)

                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `when error occurred while fetching repo detail then ui state is error `() =
        runTest(StandardTestDispatcher()) {
            val expectedItem = RepoDetailScreenState.Error

            viewModel.getRepoDetails("")

            viewModel.uiState.test {
                assertEquals(RepoDetailScreenState.Loading, awaitItem())

                val actualItem = awaitItem()

                assert(actualItem is RepoDetailScreenState.Error)

                assertEquals(actualItem, expectedItem)

                cancelAndIgnoreRemainingEvents()
            }
        }
}