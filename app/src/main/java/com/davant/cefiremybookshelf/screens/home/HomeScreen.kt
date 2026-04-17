package com.davant.cefiremybookshelf.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation3.runtime.NavKey
import com.davant.cefiremybookshelf.model.Book
import com.davant.cefiremybookshelf.model.getInitialBooks

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    name: String,
    onBack: () -> NavKey,
    goToAddScreen: () -> Unit,
    goToEditScreen: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val contentIndex = rememberSaveable { mutableStateOf(0) }
    val bookList = rememberSaveable { mutableStateOf(getInitialBooks()) }
    val bookListFiltered = filterBookList(bookList.value, contentIndex.value)
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            HomeTopBar(
                scrollBehavior = scrollBehavior,
                user = getNameCleared(name),
                onBack = onBack,
                goToEditScreen = goToEditScreen
            )
        },
        bottomBar = { HomeNavigationBar(contentIndex.value) { contentIndex.value = it } },
        floatingActionButton = { HomeAddBookFAB(goToAddScreen) },
        floatingActionButtonPosition = FabPosition.Start
    ) { innerPadding ->
        HomeContent(innerPadding, bookListFiltered)
    }
}
