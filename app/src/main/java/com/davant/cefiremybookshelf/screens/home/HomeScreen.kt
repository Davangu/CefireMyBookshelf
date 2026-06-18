package com.davant.cefiremybookshelf.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.davant.cefiremybookshelf.domain.model.Book

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val contentIndex by homeViewModel.contentIndex.collectAsStateWithLifecycle(0)
    val bookList by homeViewModel.bookList.collectAsStateWithLifecycle(listOf())
    val preferences by homeViewModel.preferences.collectAsStateWithLifecycle()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            HomeTopBar(
                scrollBehavior = scrollBehavior,
                user = preferences.name,
                books = bookList.size,
                primaryColor = Color(preferences.primaryColor),
                secondaryColor = Color(preferences.secondaryColor),
                onBack = { homeViewModel.goBack() },
                goToEditScreen = { homeViewModel.goToAddEditScreen(Book()) },
                goToPreferencesScreen = { homeViewModel.goToPreferencesScreen(preferences) }
            )
        },
        bottomBar = {
            HomeNavigationBar(
                contentIndex = contentIndex,
                primaryColor = Color(preferences.primaryColor),
                secondaryColor = Color(preferences.secondaryColor)
            ) {
                homeViewModel.onContentIndexChange(it)
            }
        },
        floatingActionButton = {
            HomeAddBookFAB(
                primaryColor = Color(preferences.primaryColor),
                secondaryColor = Color(preferences.secondaryColor)
            ) {
                homeViewModel.goToAddEditScreen(Book())
            }
        },
        floatingActionButtonPosition = FabPosition.Start
    ) { innerPadding ->
        HomeContent(innerPadding, bookList) {
            homeViewModel.goToAddEditScreen(it)
        }
    }
}
