package com.tahia.moodnotes.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tahia.moodnotes.data.local.JournalEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JournalDetailScreen(
    navController: NavController, //lets us go back to previous screen
    entry: JournalEntity, // the journal entry we want to display
    onDelete: (JournalEntity) -> Unit //lambda function that delets the entry when called
) {
    //Scaffold provides basic layout structure - header, content, optional bottom bar
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Entry Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Delete")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        onDelete(entry)
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).padding(16.dp)
        ) {
            Text(text = entry.content, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(16.dp))

            if (entry.mood.isNotEmpty()) {
                Text(
                    text = "Mood: ${entry.mood}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Text(
                text = "Time: ${java.text.SimpleDateFormat("MM/dd/yyyy HH:mm").format(entry.timestamp)}",
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}