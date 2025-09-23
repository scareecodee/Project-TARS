package com.example.projecttars.Student.Components

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
fun BottomNavBar() {
    var selectedItem by remember { mutableIntStateOf(0) }

    val items = listOf(
        "Home" to Icons.Filled.Home,
        "Resources" to Icons.Filled.Search,
        "Profile" to Icons.Filled.Person
    )

    NavigationBar(
        containerColor = DarkSlate,
        contentColor = Color.White
    ) {
        items.forEachIndexed { index, (label, icon) ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = { selectedItem = index },
                icon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = label,
                        tint = if (selectedItem == index) AccentBlue else Color.White
                    )
                },
                label = {
                    Text(
                        text = label,
                        fontFamily = FontFamily(Font(R.font.poppinsregular)),
                        color = if (selectedItem == index) AccentBlue else Color.White
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

