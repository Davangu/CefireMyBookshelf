package com.davant.cefiremybookshelf.data.local.entity

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.davant.cefiremybookshelf.domain.model.Preferences
import com.davant.cefiremybookshelf.ui.theme.Main
import com.davant.cefiremybookshelf.ui.theme.Secondary

@Entity(tableName = "preferences")
data class PreferencesEntity(
    @PrimaryKey
    val id: String = "",
    val name: String = "",
    @ColumnInfo("primary_color")
    val primaryColor: Int = Main.toArgb(),
    @ColumnInfo("secondary_color")
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

