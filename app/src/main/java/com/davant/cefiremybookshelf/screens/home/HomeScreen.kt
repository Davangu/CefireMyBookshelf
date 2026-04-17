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

fun filterBookList(bookList: List<Book>, value: Int) =
    when (value) {
        1 -> bookList.filter {
            it.fav
        }
        2 -> bookList.filter {
            it.read
        }
        else -> bookList
    }


fun getNameCleared(oldName: String): String {
    val listOfParts = oldName.split('@')
    return listOfParts[0]
}

/*
Box(modifier = Modifier.fillMaxSize(),
contentAlignment = Alignment.Center) {
    Card(modifier = Modifier
        .width(300.dp)
        .height(125.dp),
        shape = RoundedCornerShape(25.dp),
        colors = CardDefaults.cardColors(
            containerColor = Main,
            contentColor = Secondary
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        border = BorderStroke(3.dp, Secondary)) {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
            Text(text = "Bienvenid@ ${getNameCleared(name)}!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center)
        }
    }
    Button(modifier = Modifier
        .padding(bottom = 24.dp)
        .align(Alignment.BottomCenter),
        onClick = { onBack() }) {
        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back to Login page")
    }
}
*/