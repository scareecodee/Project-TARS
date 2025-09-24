package com.example.projecttars

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.projecttars.Common.RoleSelectionScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "role_selection"
    ) {

        composable("role_selection") {
            RoleSelectionScreen { selectedRole ->
                if (selectedRole == "Members") {
                    navController.navigate("MembersLogin")
                } else if (selectedRole == "Admin") {
                    navController.navigate("AdminLogin")
                }
            }
        }



    }
}