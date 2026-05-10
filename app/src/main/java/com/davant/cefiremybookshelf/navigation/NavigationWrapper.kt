package com.davant.cefiremybookshelf.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.davant.cefiremybookshelf.data.repository.LocalBooksRepository
import com.davant.cefiremybookshelf.domain.model.Book
import com.davant.cefiremybookshelf.navigation.Routes.*
import com.davant.cefiremybookshelf.screens.addedit.AddEditScreen
import com.davant.cefiremybookshelf.screens.addedit.AddEditViewModel
import com.davant.cefiremybookshelf.screens.home.HomeScreen
import com.davant.cefiremybookshelf.screens.home.HomeViewModel
import com.davant.cefiremybookshelf.screens.login.LoginScreen
import com.davant.cefiremybookshelf.screens.login.LoginViewModel

@Composable
fun NavigationWrapper() {
    val backStack = rememberNavBackStack(Login)
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeAt(backStack.lastIndex) },
        entryProvider = entryProvider {
            entry<Login> {
                LoginScreen(LoginViewModel()) { userName ->
                    backStack.add(Home(userName))
                }
            }
            entry<Home> { key ->
                HomeScreen(
                    name = key.name,
                    onBack = { backStack.removeAt(backStack.lastIndex) },
                    goToAddScreen = { backStack.add(AddEdit(Book())) },
                    goToEditScreen = { backStack.add(AddEdit()) },
                    homeViewModel = HomeViewModel(LocalBooksRepository())
                )
            }
            entry<AddEdit> { key ->
                AddEditScreen(AddEditViewModel(key.book))
            }
        }
    )
}

