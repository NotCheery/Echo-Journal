package com.tahia.echojournal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.tahia.echojournal.data.local.JournalDatabase
import com.tahia.echojournal.data.local.repository.JournalRepository
import com.tahia.echojournal.ui.JournalViewModelFactory
import com.tahia.echojournal.ui.screens.JournalScreen
import com.tahia.echojournal.ui.viewmodel.JournalViewModel
import com.tahia.echojournal.ui.theme.EchoJournalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Step 1: Get database and DAO
        val database = JournalDatabase.getDatabase(applicationContext)
        val dao = database.journalDao()

        // Step 2: Create repository
        val repository = JournalRepository(dao)

        // Step 3: Create ViewModel using Factory
        val viewModelFactory = JournalViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(JournalViewModel::class.java)

        // Step 4: Set Compose content
        setContent {
            EchoJournalTheme {
                JournalScreen(viewModel = viewModel)
            }
        }
    }
}
