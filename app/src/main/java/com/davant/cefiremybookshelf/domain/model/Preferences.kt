package com.davant.cefiremybookshelf.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.davant.cefiremybookshelf.ui.theme.Main
import com.davant.cefiremybookshelf.ui.theme.Secondary
import kotlinx.serialization.Serializable

@Serializable
data class Preferences(
    val id:String = "",
    val name:String = "",
    val primaryColor:Int = Main.toArgb(),
    val secondaryColor: Int = Secondary.toArgb()
)
