package com.tahia.echojournal.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [JournalEntity::class], version = 1, exportSchema = false)
abstract class JournalDatabase : RoomDatabase() {
    abstract fun journalDao() : JournalDao

    companion object {
        @Volatile
        private var INSTANCE: JournalDatabase? = null

        fun getDatabase(context: Context): JournalDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    JournalDatabase::class.java,
                    "journal_db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}