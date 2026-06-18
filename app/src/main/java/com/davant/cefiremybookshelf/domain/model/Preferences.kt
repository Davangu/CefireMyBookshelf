package com.davant.cefiremybookshelf.domain.model

import androidx.compose.ui.graphics.Color
import com.davant.cefiremybookshelf.ui.theme.Main
import com.davant.cefiremybookshelf.ui.theme.Secondary

data class Preferences(
    val id:String = "",
    val name:String = "",
    val primaryColor:Color = Main,
    val secondaryColor: Color = Secondary
)
