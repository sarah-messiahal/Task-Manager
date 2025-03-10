package com.example.taskmanager.dragdrop

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.taskmanager.data.Task

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DraggableTaskList(
    tasks: List<Task>,
    onTaskReorder: (fromIndex: Int, toIndex: Int) -> Unit
) {
    val listState = rememberLazyListState()
    var currentIndex by remember { mutableStateOf<Int?>(null) }

    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(tasks, key = { _, task -> task.id }) { index, task ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragStart = { currentIndex = index },
                            onDragEnd = { currentIndex = null },
                            onDragCancel = { currentIndex = null },
                            onDrag = { change, dragAmount ->
                                change.consume()
                                val newIndex = (index - (dragAmount.y / 60).toInt())
                                    .coerceIn(0, tasks.lastIndex)

                                if (newIndex != index) {
                                    onTaskReorder(index, newIndex)
                                }
                            }
                        )
                    }
                    .animateItemPlacement()
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
