package com.tahia.moodnotes.ui.viewmodel

import androidx.lifecycle.*
import com.tahia.moodnotes.data.local.JournalEntity
import com.tahia.moodnotes.data.local.repository.JournalRepository
import kotlinx.coroutines.launch

//ViewModel file, part of MVVM (Model-View-ViewModel)
//this file sits between UI and DB and contains logic for interacting with repository and updating UI state

//handles logic for UI and survives configuration changes
class JournalViewModel(private val repository: JournalRepository) : ViewModel() {


    val allEntries = repository.allEntries

    //add entry to database
    fun addEntry(entry: JournalEntity) {
        viewModelScope.launch {
            repository.insertEntry(entry)
        }
    }

    //remove an entry from the database
    fun deleteEntry(entry: JournalEntity) {
        viewModelScope.launch {
            repository.deleteEntry(entry)
        }
    }
}
