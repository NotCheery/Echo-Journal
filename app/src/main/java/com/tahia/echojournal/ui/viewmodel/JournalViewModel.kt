package com.tahia.echojournal.ui.viewmodel

import androidx.lifecycle.*
import com.tahia.echojournal.data.local.JournalEntity
import com.tahia.echojournal.data.local.repository.JournalRepository
import kotlinx.coroutines.launch

//handles logic for UI
class JournalViewModel(private val repository: JournalRepository) : ViewModel() {

    val allEntries = repository.allEntries


    fun addEntry(entry: JournalEntity) {
        viewModelScope.launch {
            repository.insertEntry(entry)
        }
    }

    fun deleteEntry(entry: JournalEntity) {
        viewModelScope.launch {
            repository.deleteEntry(entry)
        }
    }
}
