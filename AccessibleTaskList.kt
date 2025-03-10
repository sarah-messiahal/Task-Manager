package com.example.taskmanager.accessibility

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.taskmanager.data.Task

@Composable
fun AccessibleTaskList(tasks: List<Task>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {
        tasks.forEach { task ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = task.title,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.semantics {
                            contentDescription = "Task Title: ${task.title}"
                        }
                    )
                    Text(
                        text = task.description ?: "No Description",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.semantics {
                            contentDescription = "Task Description: ${task.description ?: "No Description"}"
                        }
                    )
                }
            }
        }
    }
}
