package com.example.taskmanager.screens

import android.renderscript.RenderScript.Priority
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.rememberScrollState

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanager.data.Task
import com.example.taskmanager.data.TaskDatabase
import java.time.LocalDate
import java.time.ZoneId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskCreationScreen(
    onTaskCreated: (Task) -> Unit,
    onBackPressed: () -> Unit
) {
    val context = LocalContext.current
    val taskDatabase = TaskDatabase.getDatabase(context)
    val taskDao = taskDatabase.taskDao()
    val coroutineScope = rememberCoroutineScope()

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var priority by remember { mutableStateOf(Priority.LOW) }
    var date by remember { mutableStateOf(LocalDate.now()) }
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") }
        )

        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") }
        )

        ExposedDropdownMenuBox(
            modifier = Modifier.fillMaxWidth(),
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                readOnly = true,
                value = priority.name,
                onValueChange = { },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                }
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.wrapContentSize(),
                scrollState = rememberScrollState(),
                matchAnchorWidth = true,
                shape = MaterialTheme.shapes.extraLarge,
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 2.dp,
                shadowElevation = 4.dp,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
            ) {
                Priority.values().forEach { p ->
                    DropdownMenuItem(
                        onClick = {
                            priority = p
                            expanded = false
                        },
                        text = { Text(p.name) }
                    )
                }
            }
        }

        Button(onClick = {
            val task = Task(
                0,
                title,
                description,
                priority,
                date.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
            )
            coroutineScope.launch(Dispatchers.IO) {
                taskDao.insert(task)
                onTaskCreated(task)
            }
        }) {
            Text("Create Task")
        }

        Button(onClick = onBackPressed) {
            Text("Back")
        }
    }
}

@Preview
@Composable
fun PreviewTaskCreationScreen() {
    TaskCreationScreen({}, {})
}
