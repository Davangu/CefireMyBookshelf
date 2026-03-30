package com.davant.cefiremybookshelf.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Routes: NavKey {
    @Serializable
    data object Login:Routes()

    @Serializable
    data class Home(val name:String):Routes()
}