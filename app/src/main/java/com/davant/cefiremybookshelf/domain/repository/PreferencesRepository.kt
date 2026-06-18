package com.davant.cefiremybookshelf.domain.repository

import com.davant.cefiremybookshelf.domain.model.Preferences
import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {
    fun getPreferencesByUser(userId: String): Flow<Preferences>
    suspend fun addPreferences(preferences: Preferences)
    suspend fun updatePreferences(preferences: Preferences)
    suspend fun deletePreferences(preferences: Preferences)
}