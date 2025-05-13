package com.tahia.moodnotes.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tahia.moodnotes.data.local.JournalEntity
import com.tahia.moodnotes.ui.viewmodel.JournalViewModel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.text.style.TextOverflow

import com.tahia.moodnotes.TextUtils

@SuppressLint("SimpleDateFormat")
@Composable
fun JournalScreen(
    //Add a new parameter
    viewModel: JournalViewModel,
    onEntryClick: (JournalEntity) -> Unit
) {
    //Collect the list of entries from the ViewModel using JetPack Compose state system
    val entries by viewModel.allEntries.collectAsState(initial = emptyList())

    //local state to store what user types into TextField
    var text by remember { mutableStateOf("") }

    //word count from TextUtils
    val wordCount = TextUtils.getWordCount(text)
    val charCount = TextUtils.getCharacterCount(text)

    //Main column layout for journal entry
    Column(modifier = Modifier.padding(16.dp)) {
        //Text input for writing journal entry
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("What's on your mind?") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

//        Word Count
        Text(
            text = "Words: $wordCount | Characters: $charCount",
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.height(8.dp))

        //Button for adding a journal entry
        Button(
            onClick = {
                if (text.isNotBlank()) {
                    val entry = JournalEntity(content = text) // only the content, other fields use default
                    viewModel.addEntry(entry) //tells viewmodel to save this text entry to DB
                    text = "" //clear the field after adding
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Add")
        }

        Spacer(modifier = Modifier.height(16.dp))

        //Title above the list of journal entries
        Text("Your Journal Entries", style = MaterialTheme.typography.titleMedium)

        //List of existing journal entries using LazyColumn in Card format + delete icon button
        LazyColumn {
            items(entries) { entry ->
                Card(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onEntryClick(entry) }.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        //column for text info
                        Column (
                            modifier = Modifier.weight(1f).padding(end=8.dp)
                        ) {
                            Text(text = entry.content,
                                maxLines = 2, overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.bodyLarge,
                            )
                            if (entry.mood.isNotEmpty()) {
                                Text(
                                    text = "Mood : ${entry.mood}", style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                            Text(
                                text = "Time: ${java.text.SimpleDateFormat("MM/dd/yyyy HH:mm").format(entry.timestamp)}",
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                        //Delete button icon
                        IconButton(onClick = {viewModel.deleteEntry(entry)}) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete Entry",
                                tint = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }

            }
        }
    }
}




