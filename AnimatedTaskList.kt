package com.example.taskmanager.animations

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.taskmanager.data.Task

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedTaskList(tasks: List<Task>) {
    AnimatedVisibility(
        visible = tasks.isNotEmpty(),
        enter = slideInVertically(
            initialOffsetY = { 40 },
            animationSpec = tween(500)
        ),
        exit = slideOutVertically(
            targetOffsetY = { -40 },
            animationSpec = tween(500)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            tasks.forEach { task ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = task.title,
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Text(
                            text = task.description ?: "No Description",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}
