package com.example.taskmanager.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskmanager.screens.SettingsScreen
import com.example.taskmanager.screens.TaskCreationScreen
import com.example.taskmanager.screens.TaskDetailScreen
import com.example.taskmanager.screens.TaskListScreen
import com.example.taskmanager.viewmodel.TaskViewModel

@Composable
fun TaskManagerNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: TaskViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "taskList"
    ) {
        composable("taskList") {
            TaskListScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable("createTask") {
            TaskCreationScreen(
                onTaskCreated = { task ->
                    viewModel.addTask(task)
                    navController.popBackStack()
                },
                onBackPressed = { navController.popBackStack() }
            )
        }

        composable("taskDetails/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull()
            val task = viewModel.getTaskById(taskId ?: 0)

            TaskDetailScreen(
                task = task,
                onDelete = { task?.let { viewModel.deleteTask(it) } },
                onBackPressed = { navController.popBackStack() }
            )
        }

        composable("settings") {
            SettingsScreen(onBackPressed = { navController.popBackStack() })
        }
    }
}

private fun TaskViewModel.getTaskById(i: kotlin.Int) {}
