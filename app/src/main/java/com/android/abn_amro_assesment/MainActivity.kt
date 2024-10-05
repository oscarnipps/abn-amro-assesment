package com.android.abn_amro_assesment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import androidx.paging.ExperimentalPagingApi
import com.android.abn_amro_assesment.ui.theme.AbnamroassesmentTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalPagingApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AbnamroassesmentTheme {
                val navController = rememberNavController()
                RepoAppNavGraph(navController)
            }
        }
    }
}

