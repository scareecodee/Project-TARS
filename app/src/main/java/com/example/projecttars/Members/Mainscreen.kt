package com.example.projecttars.Members

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.projecttars.DataModels.TarsLabComponent
import com.example.projecttars.Members.UiElements.BottomNavBar
import com.example.projecttars.Members.Home.MembersHome
import com.example.projecttars.Members.Profile.ProfileScreen
import com.example.projecttars.Members.Resources.ResourcesScreen
import com.example.projecttars.ui.theme.DarkSlate
import com.example.projecttars.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController) {
    val components = listOf(
        TarsLabComponent(R.drawable.tarslogo, "Oscilloscope", true),
        TarsLabComponent(R.drawable.tarslogo, "Multimeter", false),
        TarsLabComponent(R.drawable.tarslogo, "Soldering Kit", true),
        TarsLabComponent(R.drawable.tarslogo, "Power Supply", true),
        TarsLabComponent(R.drawable.tarslogo, "Oscilloscope", true),
        TarsLabComponent(R.drawable.tarslogo, "Multimeter", false),
        TarsLabComponent(R.drawable.tarslogo, "Soldering Kit", true),
        TarsLabComponent(R.drawable.tarslogo, "Power Supply", true),
        TarsLabComponent(R.drawable.tarslogo, "Oscilloscope", true),
        TarsLabComponent(R.drawable.tarslogo, "Multimeter", false),
        TarsLabComponent(R.drawable.tarslogo, "Soldering Kit", true),
        TarsLabComponent(R.drawable.tarslogo, "Power Supply", true),
        TarsLabComponent(R.drawable.tarslogo, "Oscilloscope", true),
        TarsLabComponent(R.drawable.tarslogo, "Multimeter", false),
        TarsLabComponent(R.drawable.tarslogo, "Soldering Kit", true),
        TarsLabComponent(R.drawable.tarslogo, "Power Supply", true)

    )

    val activity = (LocalContext.current as? Activity)

//    BackHandler {
//        activity?.finish()
//    }

    BackHandler {
        navController.popBackStack()
    }
    var selectedScreen by remember { mutableStateOf("home") }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier.fillMaxWidth()
                    .windowInsetsTopHeight(WindowInsets.statusBars)
                    .background(DarkSlate)

            )
        },
        bottomBar = {
            BottomNavBar(
                selectedScreen = selectedScreen,
                onItemSelected = { selectedScreen = it }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            when (selectedScreen) {
                "home" -> MembersHome(
                    onProjectCompletedClick = { navController.navigate("CompletedProjectsScreen") },
                    onProjectOngoingClick = { navController.navigate("OngoingProjectsScreen") },
                    onAchievementsClick = { navController.navigate("AchievementScreen") },
                    onMembersClick = {navController.navigate("TarsMemberScreen") },
                    onSocialHandleClick = { navController.navigate("SocialMediaScreen") }
                )
                "resources" -> ResourcesScreen(
                    components = components,
                    navController = navController
                )
                "profile" -> ProfileScreen (
                    onNotificationsClick = {
                        navController.navigate("NotificationScreen")
                    },
                    onMessageAdminClick ={
                        navController.navigate("MessageAdminScreen")
                    }
                )
            }
        }
    }
}
