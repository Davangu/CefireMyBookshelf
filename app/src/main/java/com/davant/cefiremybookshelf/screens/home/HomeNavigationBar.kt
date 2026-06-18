package com.davant.cefiremybookshelf.screens.home

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.davant.cefiremybookshelf.R
import com.davant.cefiremybookshelf.ui.theme.Main
import com.davant.cefiremybookshelf.ui.theme.Secondary


@Composable
fun HomeNavigationBar(
    contentIndex: Int,
    primaryColor: Color,
    secondaryColor: Color,
    onItemSelected: (Int) -> Unit) {
    val navBarItemColors = NavigationBarItemDefaults.colors(
        selectedIconColor = Color.White,
        selectedTextColor = Color.White,
        unselectedIconColor = Color.White,
        unselectedTextColor = Color.White,
        indicatorColor = secondaryColor)

    NavigationBar(containerColor = primaryColor) {
        NavigationBarItem(
            selected = contentIndex == 0 ,
            onClick = { onItemSelected(0) },
            icon = {
                Icon(painter = painterResource(R.drawable.ic_home),
                    contentDescription = "Home")
            },
            label = { Text("All")},
            colors = navBarItemColors
        )
        NavigationBarItem(
            selected = contentIndex == 1 ,
            onClick = { onItemSelected(1) },
            icon = {
                Icon(painter = painterResource(R.drawable.ic_not_fav),
                    contentDescription = "Favs")
            },
            label = { Text("Favs")},
            colors = navBarItemColors
        )
        NavigationBarItem(
            selected = contentIndex == 2 ,
            onClick = { onItemSelected(2) },
            icon = {
                Icon(painter = painterResource(R.drawable.ic_read),
                    contentDescription = "Read")
            },
            label = { Text("Read")},
            colors = navBarItemColors
        )
    }
}

