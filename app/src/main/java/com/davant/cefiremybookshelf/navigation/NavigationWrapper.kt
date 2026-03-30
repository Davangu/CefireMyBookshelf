package com.davant.cefiremybookshelf.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.davant.cefiremybookshelf.navigation.Routes.*
import com.davant.cefiremybookshelf.screens.HomeScreen
import com.davant.cefiremybookshelf.screens.login.LoginScreen

@Composable
fun NavigationWrapper() {
    val backStack = rememberNavBackStack(Login)
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeAt(backStack.lastIndex) },
        entryProvider = entryProvider {
            entry<Login> {
                LoginScreen() { userName ->
                    backStack.add(Home(userName))
                }
            }
            entry<Home> { key ->
                HomeScreen(key.name) {
                    backStack.removeAt(backStack.lastIndex)
                }
            }
        }
    )
}

