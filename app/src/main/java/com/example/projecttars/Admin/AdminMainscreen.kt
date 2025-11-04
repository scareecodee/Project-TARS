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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.projecttars.Admin.Profile.AdminProfileScreen
import com.example.projecttars.Admin.Resources.AdminResScreen
import com.example.projecttars.DataModels.MemberDetail
import com.example.projecttars.Members.Home.AdminHome
import com.example.projecttars.Members.UiElements.BottomNavBar
import com.example.projecttars.ViewModels.Firebase.AuthVM
import com.example.projecttars.ViewModels.Firebase.MembersVM
import com.example.projecttars.ViewModels.Firebase.ResourcesVM
import com.example.projecttars.ViewModels.NavigationData.ResourcesNavVM
import com.example.projecttars.ui.theme.DarkSlate

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AdminMainScreen(navController: NavHostController,resourcesVM: ResourcesVM,resourcesNavVM: ResourcesNavVM,authVM: AuthVM,membersVM: MembersVM) {

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
                "home" ->AdminHome(
                    onProjectCompletedClick = { navController.navigate("AdminCompletedProjectScreen") },
                    onProjectOngoingClick = { navController.navigate("AdminOngoingProjectScreen") },
                    onAchievementsClick = { navController.navigate("AdminAchievementsScreen") },
                    onMembersClick = { navController.navigate("AdminTarsMemberScreen") },
                    onSocialHandleClick = { navController.navigate("AdminSocialMediaScreen") }
                )
                "resources" -> AdminResScreen(
                    resourcesVM = resourcesVM,
                    onAddClick = {
                        resourcesNavVM.clearSelection()
                        navController.navigate("AddEquipment")
                    },
                    isAdmin = true,
                    onCardClick = {
                        resourcesNavVM.selectEquipment(it)
                        navController.navigate("AdminResDetailScreen")
                    }
                )
                "profile" -> AdminProfileScreen(
                    onLogoutClick = {
                        authVM.signout()
                                    navController.navigate("Role_Selection")
                                    },
                    onNotificationsViewClick = { navController.navigate("AdminNotificationScreen") },
                    onAboutSocietyClick = { navController.navigate("AboutSocietyScreen") },
                    onManageAdminsClick = { navController.navigate("ManageAdminScreen") },
                    imageUrl = currentUserDetail?.imageUrl.orEmpty(),
                    username = currentUserDetail?.name ?: "Unknown User",
                    email = currentUserMailId?:"Unknown User"
                )

            }
        }
    }
}
