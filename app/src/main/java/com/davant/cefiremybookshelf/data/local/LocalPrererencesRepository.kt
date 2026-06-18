package com.davant.cefiremybookshelf.data.local

import com.davant.cefiremybookshelf.data.local.dao.PreferencesDao
import com.davant.cefiremybookshelf.data.local.entity.toPreferences
import com.davant.cefiremybookshelf.data.local.entity.toPreferencesEntity
import com.davant.cefiremybookshelf.domain.model.Preferences
import com.davant.cefiremybookshelf.domain.repository.PreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalPreferencesRepository(
    private val preferencesDao: PreferencesDao): PreferencesRepository {
    override fun getPreferencesByUser(userId: String): Flow<Preferences> =
        preferencesDao.getPreferencesByUser(userId).map { it.toPreferences() }

    override suspend fun addPreferences(preferences: Preferences) {
        preferencesDao.insertPreferences(preferences.toPreferencesEntity())
    }

    override suspend fun updatePreferences(preferences: Preferences) {
        preferencesDao.updatePreferences(preferences.toPreferencesEntity())
    }

    override suspend fun deletePreferences(preferences: Preferences) {
        preferencesDao.deletePreferences(preferences.toPreferencesEntity())
    }

}