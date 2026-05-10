package com.davant.cefiremybookshelf.screens.addedit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.davant.cefiremybookshelf.ui.theme.Main
import com.davant.cefiremybookshelf.ui.theme.Secondary

@Composable
fun AddEditScreen() {
    Box(modifier = Modifier.fillMaxSize()
        .background(Main),
        contentAlignment = Alignment.Center) {
        Text("AddEditScreen", color = Secondary)
    }
}