package com.davant.cefiremybookshelf.screens.preferences

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation3.runtime.NavKey
import com.davant.cefiremybookshelf.data.local.LocalPreferencesRepository
import com.davant.cefiremybookshelf.domain.model.Preferences
import kotlinx.coroutines.launch


class PreferencesViewModel(
    private val initialPreferences: Preferences,
    private val preferencesRepository: LocalPreferencesRepository,
    val navigateBack: () -> NavKey
) : ViewModel() {
    var uiState: PreferencesUiState by mutableStateOf(
        PreferencesUiState(initialPreferences)
    )

    fun onNameChanged(newName: String) {
        uiState = uiState.copy(prefs = uiState.prefs.copy(name = newName))
    }

    fun onColorChanged(newColor: Color, primary: Boolean) {
        uiState =
            if (primary)
                uiState.copy(prefs = uiState.prefs.copy(primaryColor = newColor.toArgb()))
            else
                uiState.copy(prefs = uiState.prefs.copy(secondaryColor = newColor.toArgb()))
    }

    fun savePreferences() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            try {
                preferencesRepository.updatePreferences(uiState.prefs)
                uiState = uiState.copy(isLoading = false, isSaveSuccess = true)
                navigateBack()
            } catch (e: Exception) {
                uiState = uiState.copy(isLoading = false, error = e.message)
            }
        }
    }

    fun resetPreferences() {
        uiState = uiState.copy(prefs = Preferences(id = initialPreferences.id))
    }
}

data class PreferencesUiState(
    val prefs: Preferences,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSaveSuccess: Boolean = false
)
