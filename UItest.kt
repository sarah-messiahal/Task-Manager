//
//import androidx.compose.ui.test.assertIsDisplayed
//import androidx.compose.ui.test.junit4.createComposeRule
//import androidx.compose.ui.test.onNodeWithText
//import androidx.compose.ui.test.performClick
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//import com.example.taskmanager.screens.TaskCreationScreen
//
//@RunWith(AndroidJUnit4::class)
//class TaskListTest {
//
//    @get:Rule
//    val composeTestRule = createComposeRule()
//
//    @Test
//    fun testTaskCreationFlow() {
//        composeTestRule.setContent {
//            TaskCreationScreen(
//                onTaskCreated = {  },
//                onBackPressed = {  }
//            )
//        }
//
//        composeTestRule.onNodeWithText("Create Task").performClick()
//
//        composeTestRule.onNodeWithText("Title").assertIsDisplayed()
//    }
//}
