package com.tahia.echojournal.data.local.repository

import kotlinx.coroutines.flow.Flow
import com.tahia.echojournal.data.local.JournalDao
import com.tahia.echojournal.data.local.JournalEntity

//acts as middleman between UI and database
//the repository encapsulates database logic so viewmodel and UI don't have to handle it directly
class JournalRepository(private val dao: JournalDao) {

    val allEntries: Flow<List<JournalEntity>> = dao.getAllEntries()

    suspend fun insertEntry(entry: JournalEntity) {
        dao.insertEntry(entry)
    }

    suspend fun deleteEntry(entry: JournalEntity) {
        dao.deleteEntry(entry)
    }
}
