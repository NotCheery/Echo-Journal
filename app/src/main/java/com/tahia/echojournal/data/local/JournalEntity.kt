package com.tahia.echojournal.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "journal_entries")  //tells Room to treat this as DB table
data class JournalEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, //unique id for each journal entry
    val content: String, //text of journal entry
    val mood: String = "", //user's emotion
    val timestamp: Long = System.currentTimeMillis() //when it was written (number)

)