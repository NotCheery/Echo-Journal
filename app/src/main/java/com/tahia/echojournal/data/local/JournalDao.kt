package com.tahia.echojournal.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
//Dao: Data Access Object
@Dao //tells Room that this interface will contain SQL queries
interface JournalDao {

    //retrieves all queries sorted by time -> newest first
    @Query("SELECT * FROM journal_entries ORDER BY timestamp DESC")
    fun getAllEntries(): Flow<List<JournalEntity>> //allows UI to observe changes in real-time

    //adds entry to the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntry(entry: JournalEntity)

    //removes an entry
    @Delete
    suspend fun deleteEntry(entry: JournalEntity)
}
