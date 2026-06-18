package com.davant.cefiremybookshelf.screens.preferences

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.davant.cefiremybookshelf.R


@Composable
fun PreferencesScreen(viewModel: PreferencesViewModel) {
    val uiState = viewModel.uiState

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(uiState.prefs.primaryColor))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.prefs.name,
                onValueChange = { viewModel.onNameChanged(it) },
                placeholder = { Text("Name") },
                isError = uiState.error != null
            )
            Text(
                "Choose theme's primary color:",
                color = Color.White
            )
            ColorPicker(uiState, viewModel)

            Text(
                "Choose theme's secondary color:",
                color = Color.White
            )
            ColorPicker(uiState, viewModel, false)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp))
            {
                Button(onClick = { viewModel.resetPreferences() }) {
                    Text("Reset")
                }
                Button(onClick = { viewModel.savePreferences() }) {
                    Text("Save")
                }
            }
        }
    }
}

@Composable
fun ColorPicker(
    uiState: PreferencesUiState,
    viewModel: PreferencesViewModel,
    primary: Boolean = true
) {
    val colorOptions = listOf(
        Color(0xFFE80D0D), // Red
        Color(0xFFE91E63), // Pink
        Color(0xFF9C27B0), // Purple
        Color(0xFF2196F3), // Blue
        Color(0xFF4CAF50), // Green
        Color(0xFFFFEB3B), // Yellow
        Color(0xFFFF9800), // Orange
        Color(0xFF3F51B5)  // Indigo
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        colorOptions.forEach { color ->
            val isSelected = if (primary)
                Color(uiState.prefs.primaryColor) == color
            else Color(uiState.prefs.secondaryColor) == color

            Box(
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
                    .background(color)
                    .border(
                        width = if (isSelected) 3.dp else 1.dp,
                        color = if (isSelected) Color.White else Color.Black,
                        shape = CircleShape
                    )
                    .clickable { viewModel.onColorChanged(color, primary) },
                contentAlignment = Alignment.Center
            ) {
                if (isSelected) {
                    Icon(
                        painter = painterResource(R.drawable.ic_check),
                        contentDescription = "Selected",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}