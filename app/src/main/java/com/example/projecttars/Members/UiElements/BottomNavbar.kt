package com.example.projecttars.Members.UiElements

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
import com.example.projecttars.ui.theme.AccentBlue
import com.example.projecttars.ui.theme.DarkSlate





@Composable
fun BottomNavBar(
    selectedScreen: String,
    onItemSelected: (String) -> Unit
) {
    val items = listOf(
        "home" to Icons.Filled.Home,
        "resources" to Icons.Filled.Search,
        "profile" to Icons.Filled.Person
    )

    NavigationBar(
        containerColor = DarkSlate,
        contentColor = Color.White
    ) {
        items.forEach { (label, icon) ->
            NavigationBarItem(
                selected = selectedScreen == label,
                onClick = { onItemSelected(label) },
                icon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = label,
                        tint = if (selectedScreen == label) AccentBlue else Color.White
                    )
                },
                label = {
                    Text(
                        text = label.replaceFirstChar { it.uppercase() },
                        fontFamily = FontFamily(Font(R.font.poppinsregular)),
                        color = if (selectedScreen == label) AccentBlue else Color.White
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
