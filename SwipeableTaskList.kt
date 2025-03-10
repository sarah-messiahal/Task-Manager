package com.example.taskmanager.swipegestures

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.taskmanager.data.Task

@Composable
fun SwipeableTaskList(
    tasks: List<Task>,
    onDelete: (Task) -> Unit,
    onComplete: (Task) -> Unit
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
                    .padding(bottom = 8.dp)
            ) {
                SwipeableTaskItem(
                    task = task,
                    onDelete = onDelete as () -> Unit,
                    onComplete = onComplete as () -> Unit
                )
            }
        }
    }
}

@Composable
fun SwipeableTaskItem(
    task: Task,
    onDelete: () -> Unit,
    onComplete: () -> Unit
) {
    var swipeState by remember { mutableStateOf(0f) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { },
                    onDragEnd = {
                        if (swipeState > 0.5f) {
                            onComplete()
                        } else if (swipeState < -0.5f) {
                            onDelete()
                        }
                    },
                    onDragCancel = { },
                    onDrag = { change, dragAmount ->
                        swipeState += dragAmount.x
                        change.consume()
                    }
                )
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(x = swipeState.dp)
                .padding(16.dp)
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

        if (swipeState > 0.5f) {
            Text(
                text = "Complete",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterEnd)
            )
        } else if (swipeState < -0.5f) {
            Text(
                text = "Delete",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterStart)
            )
        }
    }
}
