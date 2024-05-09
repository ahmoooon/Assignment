package com.example.assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.assignment.parentscreen.CreateParent
import com.example.assignment.teacherscreen.CreateTeach
import com.example.assignment.teacherscreen.EditTeach
import com.example.assignment.ui.theme.AssignmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AssignmentTheme {
                //HomePage()
                SuperScreen()
            }
        }
    }
}

enum class SuperNav(@StringRes val title: Int){
    Start(title = R.string.start_screen),
    CreatePar(title = R.string.create_parent),
    CreateTea(title = R.string.create_teach),
    EditTea(title = R.string.edit_teach)
}
@Composable
fun SuperScreen(
    navController: NavHostController = rememberNavController()
){
    NavHost(
        navController = navController,
        startDestination = SuperNav.Start.name,
        modifier = Modifier){

        //START
        composable(route = SuperNav.Start.name){
            SuperHome(
                createTeacherClick = { navController.navigate(SuperNav.CreateTea.name) },
                editTeacherClick = { navController.navigate(SuperNav.EditTea.name) },
                createParentClick = { navController.navigate(SuperNav.CreatePar.name)},
                editParentClick = { /*TODO*/ },
                createTimetable = { /*TODO*/ },
                editTimetable = { /*TODO*/ })
        }

        //CREATE TEACHER SCREEN
        composable(route = SuperNav.CreateTea.name){
            CreateTeach(onBackClick = {navController.navigate(SuperNav.Start.name)}, modifier = Modifier)
        }

        //EDIT TEACHER SCREEN
        composable(route = SuperNav.EditTea.name){
            EditTeach(onBackClicked = { navController.navigate(SuperNav.Start.name)}, modifier = Modifier)
        }

        composable(route = SuperNav.CreatePar.name){
            CreateParent(onCreateParentClicked = { /*TODO*/ }, onBackClick = {navController.navigate(SuperNav.Start.name)}, modifier = Modifier)
        }

    }

}



