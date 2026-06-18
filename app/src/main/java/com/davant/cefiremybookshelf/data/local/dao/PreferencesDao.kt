package com.davant.cefiremybookshelf.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.davant.cefiremybookshelf.data.local.entity.PreferencesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PreferencesDao {

    @Query("SELECT * FROM preferences WHERE id = :id")
    fun getPreferencesByUser(id:String): Flow<PreferencesEntity>

    @Insert
    suspend fun insertPreferences(preferences: PreferencesEntity)

    @Update
    suspend fun updatePreferences(preferences: PreferencesEntity)

    @Delete
    suspend fun deletePreferences(preferences: PreferencesEntity)
}