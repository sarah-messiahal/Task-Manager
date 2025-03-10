package com.example.taskmanager.screens

import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskmanager.data.Task
import com.example.taskmanager.viewmodel.TaskViewModel
import kotlinx.coroutines.Job

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    navController: NavController,
    viewModel: TaskViewModel
) {
    val tasks by viewModel.tasks.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Task Manager") },
                actions = {
                    IconButton(onClick = { navController.navigate("settings") }) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("createTask") },
                modifier = Modifier.animateContentSize()
            ) {
                Icon(Icons.Default.Add, "Add Task")
            }
        }
    ) { padding ->
        if (tasks.isEmpty()) {
            EmptyState()
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(tasks, key = { it.id }) { task ->
                    SwipeableTaskItem(
                        task = task,
                        onDelete = { viewModel.deleteTask(task) },
                        onComplete = { viewModel.updateTask(task.copy(isCompleted = true)) },
                        onClick = { navController.navigate("taskDetails/${task.id}") }
                    )
                }
            }
        }
    }
}

@Composable
fun SwipeableTaskItem(task: Task, onDelete: () -> Job, onComplete: () -> Job, onClick: () -> Unit) {
    TODO("Not yet implemented")
}

@Composable
fun EmptyState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("No tasks found!", style = MaterialTheme.typography.headlineMedium)
            Text("Tap + to create a new task", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
