package com.tahia.moodnotes.data.local.repository

import kotlinx.coroutines.flow.Flow
import com.tahia.moodnotes.data.local.JournalDao
import com.tahia.moodnotes.data.local.JournalEntity

//acts as middleman between ViewModel and DAO
//the repository encapsulates database logic so viewmodel and UI don't have to handle it directly

//Clean API to fetch and store data
class JournalRepository(private val dao: JournalDao) {

//    Flow stream of all journal entries from the database
    val allEntries: Flow<List<JournalEntity>> = dao.getAllEntries() //expose journal data as a Flow

    //insert new journal entry into DB
    suspend fun insertEntry(entry: JournalEntity) {
        dao.insertEntry(entry)
    }
    //delete a journal entry from DB
    suspend fun deleteEntry(entry: JournalEntity) {
        dao.deleteEntry(entry)
    }
}
