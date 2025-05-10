package com.tahia.echojournal.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.AndroidViewModel
import com.tahia.echojournal.data.local.JournalEntity
import com.tahia.echojournal.ui.viewmodel.JournalViewModel

@Composable
fun JournalScreen(viewModel: JournalViewModel) {
    val entries by viewModel.allEntries.collectAsState(initial = emptyList())
    var text by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("What's on your mind?") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (text.isNotBlank()) {
                    val entry = JournalEntity(content = text)
                    viewModel.addEntry(entry)
                    text = ""
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Add")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Your Journal Entries", style = MaterialTheme.typography.titleMedium)

        LazyColumn {
            items(entries) { entry ->
                Text(
                    text = entry.content,
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .fillMaxWidth()
                )
                HorizontalDivider()
            }
        }
    }
}