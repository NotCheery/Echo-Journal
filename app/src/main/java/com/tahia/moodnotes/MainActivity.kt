package com.tahia.moodnotes

import androidx.activity.compose.setContent
import androidx.activity.ComponentActivity
import android.os.Bundle
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.tahia.moodnotes.data.local.*
import com.tahia.moodnotes.data.local.repository.JournalRepository
import com.tahia.moodnotes.ui.*
import com.tahia.moodnotes.ui.screens.*
import com.tahia.moodnotes.ui.viewmodel.*
import com.tahia.moodnotes.ui.theme.EchoJournalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Step 1: Get database and DAO
        val database = JournalDatabase.getDatabase(applicationContext)

        // Step 2: Create repository
        val repository = JournalRepository(database.journalDao())

        // Step 3: Create ViewModel using Factory
        val viewModelFactory = JournalViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(JournalViewModel::class.java)

        // Step 4: Set Compose content
        setContent {
            EchoJournalTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "journal_list") {
                    composable("journal_list") {
                        JournalScreen(
                            viewModel = viewModel,
                            onEntryClick = { entry ->
                                navController.navigate("journal_detail/${entry.id}")
                            }
                        )
                    }
                    composable(
                        route = "journal_detail/{entryId}",
                        arguments = listOf(navArgument("entryId") { type = NavType.IntType })
                    ) {backStackEntry ->
                            val entryId = backStackEntry.arguments?.getInt("entryId") ?: return@composable

                            //get actual entry from ViewModel
                            val entry = viewModel.allEntries.collectAsState(initial = emptyList()).value.find { it.id == entryId }

                            entry?.let {
                                JournalDetailScreen(
                                    navController = navController,
                                    entry = it,
                                    onDelete = { viewModel.deleteEntry(it) }
                                )
                            }
                        }


                }
            }
        }
    }
}
