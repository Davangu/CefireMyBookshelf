package com.davant.cefiremybookshelf.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.davant.cefiremybookshelf.data.local.dao.PreferencesDao
import com.davant.cefiremybookshelf.data.local.entity.PreferencesEntity

@Database(entities = [PreferencesEntity::class], version = 2, exportSchema = false)
abstract class PreferencesDatabase: RoomDatabase() {
    abstract fun preferencesDao(): PreferencesDao
    companion object {
        @Volatile
        private var Instance: PreferencesDatabase? = null

        fun getDatabase(context: Context): PreferencesDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    PreferencesDatabase::class.java,
                    "preferences_database"
                )
                    .fallbackToDestructiveMigration(true)
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
