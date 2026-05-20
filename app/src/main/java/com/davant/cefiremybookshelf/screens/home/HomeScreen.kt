package com.davant.cefiremybookshelf.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavKey
import com.davant.cefiremybookshelf.domain.model.Book

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    name: String,
    homeViewModel: HomeViewModel
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val contentIndex = homeViewModel.contentIndex.collectAsStateWithLifecycle(0)
    val bookList = homeViewModel.bookList.collectAsStateWithLifecycle(listOf())
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            HomeTopBar(
                scrollBehavior = scrollBehavior,
                user = getNameCleared(name),
                onBack = { homeViewModel.goBack() },
                goToEditScreen = { homeViewModel.goToEditScreen(Book()) }
            )
        },
        bottomBar = {
            HomeNavigationBar(contentIndex.value) {
                homeViewModel.onContentIndexChange(it)
            }
        },
        floatingActionButton = { HomeAddBookFAB(){ homeViewModel.goToEditScreen(Book()) } },
        floatingActionButtonPosition = FabPosition.Start
    ) { innerPadding ->
        HomeContent(innerPadding, bookList.value) {
            homeViewModel.goToEditScreen(it)
        }
    }
}
