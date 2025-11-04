package com.example.projecttars.Members

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.projecttars.DataModels.MemberDetail
import com.example.projecttars.DataModels.TarsLabComponent
import com.example.projecttars.Members.UiElements.BottomNavBar
import com.example.projecttars.Members.Home.MembersHome
import com.example.projecttars.Members.Profile.ProfileScreen
import com.example.projecttars.Members.Resources.ResourcesScreen
import com.example.projecttars.ui.theme.DarkSlate
import com.example.projecttars.R
import com.example.projecttars.ViewModels.Firebase.AuthVM
import com.example.projecttars.ViewModels.Firebase.MembersVM
import com.example.projecttars.ViewModels.Firebase.ResourcesVM
import com.example.projecttars.ViewModels.NavigationData.ResourcesNavVM

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController, resourcesVM: ResourcesVM, resourcesNavVM: ResourcesNavVM, authVM: AuthVM, membersVM: MembersVM) {


    val activity = (LocalContext.current as? Activity)

    BackHandler {
        activity?.finish()
    }
    var currentUserMailId: String? by remember { mutableStateOf("") }
    var currentUserDetail: MemberDetail? by remember { mutableStateOf(null) }


    LaunchedEffect(authVM) {
        val email = authVM.getCurrentUserMail()
        currentUserMailId = email

        val id = email?.take(7)?.replaceFirstChar { it.uppercase() }
        if (!id.isNullOrEmpty()) {
            membersVM.getMemberById(id) { member ->
                if (member != null) {
                    currentUserDetail = member
                }
            }
        }
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
                    navController = navController,
                    resourcesVM = resourcesVM,
                    onAddClick = {},
                    resourcesNavVM = resourcesNavVM
                )
                "profile" -> ProfileScreen (
                    onNotificationsClick = {
                        navController.navigate("NotificationScreen")
                    },
                    onAboutSocietyClick = {
                        navController.navigate("AboutSocietyScreen")
                    },
                    imageUrl = currentUserDetail?.imageUrl.orEmpty(),
                    username = currentUserDetail?.name ?: "Unknown User",
                    email = currentUserMailId?:"Unknown User",
                    onLogoutClick = {
                        authVM.signout()
                        navController.navigate("Role_Selection")
                    }
                )
            }
        }
    }
}
