package com.davant.cefiremybookshelf.data.local.entity

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.room.Entity
import com.davant.cefiremybookshelf.domain.model.Preferences
import com.davant.cefiremybookshelf.ui.theme.Main
import com.davant.cefiremybookshelf.ui.theme.Secondary

@Entity(tableName = "books")
data class PreferencesEntity(
    val id: String = "",
    val name: String = "",
    val primaryColor: Int = Main.toArgb(),
    val secondaryColor: Int = Secondary.toArgb()
)

fun PreferencesEntity.toPreferences() =
    Preferences(
        id = id,
        name = name,
        primaryColor = Color(primaryColor),
        secondaryColor = Color(secondaryColor)
    )

fun Preferences.toPreferencesEntity() =
    PreferencesEntity(
        id = id,
        name = name,
        primaryColor = primaryColor.toArgb(),
        secondaryColor = secondaryColor.toArgb()
    )

