package com.davant.cefiremybookshelf.navigation

import androidx.navigation3.runtime.NavKey
import com.davant.cefiremybookshelf.domain.model.Book
import com.davant.cefiremybookshelf.domain.model.Preferences
import kotlinx.serialization.Serializable

sealed class Routes: NavKey {
    @Serializable
    data object Login:Routes()

    @Serializable
    data class Home(val name:String):Routes()

    @Serializable
    data class AddEdit(val book: Book = Book()):Routes()

    @Serializable
    data class PreferencesUI(val preferences: Preferences):Routes()
}