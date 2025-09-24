package com.example.projecttars.Members.Components

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import com.example.projecttars.R
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.projecttars.ui.theme.AccentBlue
import com.example.projecttars.ui.theme.DarkSlate

@Composable
fun BottomNavBar(navController: NavHostController) {

    val items = listOf(
        "Home" to Icons.Filled.Home,
        "Resources" to Icons.Filled.Search,
        "Profile" to Icons.Filled.Person
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = DarkSlate,
        contentColor = Color.White
    ) {
        items.forEach { (label, icon) ->
            NavigationBarItem(
                selected = currentRoute == label.lowercase(),
                onClick = {
                    navController.navigate(label.lowercase()) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = label,
                        tint = if (currentRoute == label.lowercase()) AccentBlue else Color.White
                    )
                },
                label = {
                    Text(
                        text = label,
                        fontFamily = FontFamily(Font(R.font.poppinsregular)),
                        color = if (currentRoute == label.lowercase()) AccentBlue else Color.White
                    )
                },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}
