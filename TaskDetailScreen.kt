package com.example.taskmanager.screens
import android.renderscript.RenderScript.Priority
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanager.data.Task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(
    task: Task,
    onDelete: () -> Unit,
    onBackPressed: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = task.title,
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = task.description ?: "No Description",
            style = MaterialTheme.typography.bodySmall
        )

        Button(onClick = onDelete) {
            Text("Delete")
        }

        Button(onClick = onBackPressed) {
            Text("Back")
        }
    }
}

@Preview
@Composable
fun PreviewTaskDetailScreen() {
    TaskDetailScreen(Task(1, "Test", null, Priority.LOW, 0), {}, {})
}
