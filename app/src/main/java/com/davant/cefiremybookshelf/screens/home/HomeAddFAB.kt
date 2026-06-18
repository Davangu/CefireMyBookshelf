package com.davant.cefiremybookshelf.screens.home


import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.davant.cefiremybookshelf.R


@Composable
fun HomeAddBookFAB(
    primaryColor: Color,
    secondaryColor: Color,
    goToAddScreen: () -> Unit) {
    FloatingActionButton(
        onClick = { goToAddScreen() },
        containerColor = primaryColor,
        contentColor = secondaryColor
    ) {
        Icon(modifier = Modifier.size(40.dp),
            painter = painterResource(R.drawable.ic_add),
            contentDescription = "Add book")
    }
}