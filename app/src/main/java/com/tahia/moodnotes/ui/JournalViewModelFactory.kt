package com.tahia.moodnotes.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tahia.moodnotes.data.local.repository.JournalRepository
import com.tahia.moodnotes.ui.viewmodel.JournalViewModel

//Factory used to pass repository into ViewModel constructor
class JournalViewModelFactory(private val repository: JournalRepository) : ViewModelProvider.Factory {
    //this function tells android how to construct your custom ViewModel
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        //check if the requested ViewModel is of the right type
        if (modelClass.isAssignableFrom(JournalViewModel::class.java)) {
            return JournalViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
