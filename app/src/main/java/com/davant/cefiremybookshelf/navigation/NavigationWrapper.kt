package com.davant.cefiremybookshelf.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.davant.cefiremybookshelf.MainActivity
import com.davant.cefiremybookshelf.data.firestore.FirebaseBooksRepository
import com.davant.cefiremybookshelf.data.local.LocalPreferencesRepository
import com.davant.cefiremybookshelf.data.local.database.PreferencesDatabase
import com.davant.cefiremybookshelf.data.openlibrary.covers.CoverOLApi
import com.davant.cefiremybookshelf.data.openlibrary.covers.OpenLibraryRepository
import com.davant.cefiremybookshelf.data.openlibrary.search.OLSearchApi
import com.davant.cefiremybookshelf.data.openlibrary.search.OLSearchRepository
import com.davant.cefiremybookshelf.navigation.Routes.*
import com.davant.cefiremybookshelf.screens.addedit.AddEditScreen
import com.davant.cefiremybookshelf.screens.addedit.AddEditViewModel
import com.davant.cefiremybookshelf.screens.home.HomeScreen
import com.davant.cefiremybookshelf.screens.home.HomeViewModel
import com.davant.cefiremybookshelf.screens.login.LoginScreen
import com.davant.cefiremybookshelf.screens.login.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun NavigationWrapper(context: MainActivity) {
    val auth = remember { FirebaseAuth.getInstance() }
    val firestore = remember { FirebaseFirestore.getInstance() }
    val repository = remember(firestore) { FirebaseBooksRepository(firestore) }
    val coversRepo = remember { OpenLibraryRepository(CoverOLApi.coverOLService) }
    val searchRepo = remember { OLSearchRepository(OLSearchApi.searchService) }
    val preferencesRepository = remember { LocalPreferencesRepository(
        preferencesDao = PreferencesDatabase.getDatabase(context).preferencesDao()
    )}

    val backStack = rememberNavBackStack(Login)
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeAt(backStack.lastIndex) },
        entryProvider = entryProvider {
            entry<Login> {
                LoginScreen(viewModel {
                    LoginViewModel(
                        auth = auth,
                        navigateToHome = { userName ->
                            backStack.add(Home(userName))
                        })
                })
            }
            entry<Home> { key ->
                HomeScreen(viewModel {
                    HomeViewModel(
                        repository = repository,
                        userName = key.name,
                        userId = auth.currentUser?.uid ?: "",
                        goToAddEditScreen = { backStack.add(AddEdit(it)) },
                        goBack = { backStack.removeAt(backStack.lastIndex) }
                    )
                })
            }
            entry<AddEdit> { key ->
                AddEditScreen(viewModel(key = key.book.id) {
                    AddEditViewModel(
                        inBook = key.book,
                        repository = repository,
                        coversRepository = coversRepo,
                        searchRepository = searchRepo,
                        userId = auth.currentUser?.uid ?: "",
                        navigateBack = { backStack.removeAt(backStack.lastIndex) }
                    )
                })
            }
        }
    )
}
