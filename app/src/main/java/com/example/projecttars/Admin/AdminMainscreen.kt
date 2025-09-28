package com.example.projecttars.Admin

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.projecttars.Admin.Profile.AdminProfileScreen
import com.example.projecttars.Admin.Resources.AdminResScreen
import com.example.projecttars.DataModels.TarsLabComponent
import com.example.projecttars.Members.Home.AdminHome
import com.example.projecttars.Members.UiElements.BottomNavBar
import com.example.projecttars.R
import com.example.projecttars.ui.theme.DarkSlate

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AdminMainScreen(navController: NavHostController) {
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
                "home" ->AdminHome(
                    onProjectCompletedClick = { navController.navigate("AdminCompletedProjectScreen") },
                    onProjectOngoingClick = { navController.navigate("AdminOngoingProjectScreen") },
                    onAchievementsClick = { /*TODO*/ },
                    onMembersClick = { /*TODO*/ },
                    onSocialHandleClick = { /*TODO*/ }
                )
                "resources" -> AdminResScreen(
                    components = components,
                    navController = navController,
                    onAddClick = {
                        navController.navigate("EquipmentAddScreen")
                    }
                )
                "profile" -> AdminProfileScreen (
                    onEditProfileClick = { /*TODO*/ },
                    onLogoutClick = { /*TODO*/ },
                    onNotificationsViewClick = { /*TODO*/ },
                    onViewMessageClick = { /*TODO*/ }
                )
            }
        }
    }
}
