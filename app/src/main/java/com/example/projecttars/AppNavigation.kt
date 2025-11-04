package com.example.projecttars

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.projecttars.Admin.Achievements.AddAchievementScreen
import com.example.projecttars.Admin.Achievements.AdminAchievementDetail
import com.example.projecttars.Admin.Achievements.AdminAchievementsScreen
import com.example.projecttars.Admin.AdminMainScreen
import com.example.projecttars.Admin.Login.AdminLogin
import com.example.projecttars.Admin.Members.AddTarsMemberScreen
import com.example.projecttars.Admin.Profile.AdminNotificationScreen
import com.example.projecttars.Admin.Profile.SendNotificationScreen
import com.example.projecttars.Admin.Projects.Completed.AdminCompletedProjectDetail
import com.example.projecttars.Admin.Projects.Completed.AdminCompletedProjectsScreen
import com.example.projecttars.Admin.Projects.Ongoing.AdminOngoingProjectDetail
import com.example.projecttars.Admin.Projects.Ongoing.AdminOngoingProjectScreen
import com.example.projecttars.Admin.Resources.AddCompletedProjectScreen
import com.example.projecttars.Admin.Resources.AddEquipment
import com.example.projecttars.Admin.Resources.AddOngoingProjectScreen
import com.example.projecttars.Admin.Resources.AdminResDetailScreen
import com.example.projecttars.Admin.SocialMedia.AdminSocialMedia
import com.example.projecttars.Admin.TarsMembers.AdminTarsMemberDetail
import com.example.projecttars.Admin.TarsMembers.AdminTarsMemberScreen
import com.example.projecttars.Common.RoleSelectionScreen
import com.example.projecttars.Members.Achievements.AchievementDetailScreen
import com.example.projecttars.Members.MainScreen
import com.example.projecttars.Members.Projects.Completed.CompletedProjectsScreen
import com.example.projecttars.Members.Login.MembersLogin
import com.example.projecttars.Members.Profile.NotificationScreen
import com.example.projecttars.Members.Projects.Completed.AchievementScreen
import com.example.projecttars.Members.Projects.Completed.CompletedProjectDetailScreen
import com.example.projecttars.Members.Projects.Ongoing.OngoingProjectDetailScreen
import com.example.projecttars.Members.Projects.Ongoing.OngoingProjectsScreen
import com.example.projecttars.Members.SocialMedia.SocialMediaScreen
import com.example.projecttars.Members.TarsMembers.MemberDetailScreen
import com.example.projecttars.Members.TarsMembers.TarsMembersScreen
import com.example.projecttars.ViewModels.Firebase.ResourcesVM
import com.example.projecttars.ViewModels.NavigationData.ResourcesNavVM
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.projecttars.Admin.Achievements.EditAchievement
import com.example.projecttars.Admin.Profile.ManageAdmins.AddAdminScreen
import com.example.projecttars.Admin.Profile.ManageAdmins.ManageAdminSCreen
import com.example.projecttars.Admin.Projects.Completed.EditCompletedProjectScreen
import com.example.projecttars.Admin.Projects.Ongoing.EditOngoingProject
import com.example.projecttars.Admin.Resources.EditResource
import com.example.projecttars.Admin.TarsMembers.EditMember
import com.example.projecttars.Common.SplashScreen
import com.example.projecttars.Members.Resources.EquipmentDetailScreen
import com.example.projecttars.ViewModels.Firebase.AchievementsVM
import com.example.projecttars.ViewModels.Firebase.AdminVM
import com.example.projecttars.ViewModels.Firebase.AuthState
import com.example.projecttars.ViewModels.Firebase.AuthVM
import com.example.projecttars.ViewModels.Firebase.CompletedProjectVM
import com.example.projecttars.ViewModels.Firebase.MembersVM
import com.example.projecttars.ViewModels.Firebase.NotificationVM
import com.example.projecttars.ViewModels.Firebase.OngoingProjectVM
import com.example.projecttars.ViewModels.NavigationData.AchievementsNavVM
import com.example.projecttars.ViewModels.NavigationData.CompletedProjectNavVM
import com.example.projecttars.ViewModels.NavigationData.MemberNavVM
import com.example.projecttars.ViewModels.NavigationData.OngoingProjectNavVM
import com.example.projecttars.ui.screens.AboutSocietyScreen
import kotlinx.coroutines.delay

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavGraph(navController: NavHostController) {
    val resourcesVM: ResourcesVM = viewModel()
    val membersVM: MembersVM = viewModel()
    val resourcesNavVM: ResourcesNavVM = viewModel()
    val membersNavVM: MemberNavVM = viewModel()
    val achievementsVM: AchievementsVM = viewModel()
    val achievementsNavVM: AchievementsNavVM = viewModel()
    val completedProjectVM: CompletedProjectVM = viewModel()
    val completedProjectNavVM: CompletedProjectNavVM = viewModel()
    val ongoingProjectVM: OngoingProjectVM = viewModel()
    val ongoingProjectNavVM: OngoingProjectNavVM = viewModel()
    val adminVM: AdminVM = viewModel()
    val authVM: AuthVM = viewModel()
    val notificationVM: NotificationVM=viewModel()


    NavHost(navController, startDestination = "splash") {
     composable("splash") {
         SplashScreen(navController,authVM)
     }
        defaultComposable("role_selection") {
            RoleSelectionScreen { selectedRole ->
                authVM.setUserRole(selectedRole)
                when (selectedRole) {
                    "Members" -> navController.navigate("MembersLogin")
                    "Admin" -> navController.navigate("AdminLogin")
                }
            }
        }

        defaultComposable("MembersLogin") {
            val authState by authVM.authState.observeAsState(AuthState.Unauthenticated)
             MembersLogin(
                onLoginClick = { username, password ->
                    if (username.isNotBlank() && password.isNotBlank()) {
                                authVM.login(username, password)
                            }
                        }
            )

            when (authState) {
                is AuthState.Loading -> {
                    Toast.makeText(
                        navController.context,
                        "Waiting...",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is AuthState.Authenticated -> {

                    LaunchedEffect(Unit) {
                        delay(2000)
                        navController.navigate("MembersMainScreen") {
                            popUpTo("AdminLogin") { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                }

                is AuthState.Error -> {
                    Toast.makeText(
                        navController.context,
                        "Invalid Credential",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {}
            }
        }



        defaultComposable("AdminLogin") {
            val authState by authVM.authState.observeAsState(AuthState.Unauthenticated)

            AdminLogin(
                onLoginClick = { username, password ->
                    if (username.isNotBlank() && password.isNotBlank()) {
                        adminVM.isAdmin(username) { isAdmin ->
                            if (isAdmin) {
                                authVM.login(username, password)
                            } else {
                                Toast.makeText(
                                    navController.context,
                                    "Invalid Credentials",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                },
            )

            when (authState) {
                is AuthState.Loading -> {
                    Toast.makeText(
                        navController.context,
                        "Waiting...",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is AuthState.Authenticated -> {

                    LaunchedEffect(Unit) {
                        delay(2000)
                        navController.navigate("AdminMainScreen") {
                            popUpTo("AdminLogin") { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                }

                is AuthState.Error -> {
                    Toast.makeText(
                        navController.context,
                        "Invalid Credential",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {}
            }
        }



        defaultComposable("MembersMainScreen") {
            MainScreen(
                navController,
                resourcesVM,
                resourcesNavVM,
                authVM,
                membersVM
            )
        }

        defaultComposable("CompletedProjectsScreen") {
            CompletedProjectsScreen(
                completedProjectsVM = completedProjectVM,
                onViewDetail = {
                    completedProjectNavVM.selectCompletedProject(it)
                    navController.navigate("CompletedProjectDetailScreen")
                },
                onBack = { navController.navigate("MembersMainScreen") },
            )
        }

        defaultComposable("OngoingProjectsScreen") {
            OngoingProjectsScreen(
                onViewDetail = {
                    ongoingProjectNavVM.selectOngoingProject(it)
                    navController.navigate("OngoingProjectDetailScreen")
                },
                onBack = { navController.navigate("MembersMainScreen") },
                ongoingProjectVM = ongoingProjectVM
            )
        }

        defaultComposable("TarsMemberScreen") {
            TarsMembersScreen(
                onViewDetail = {
                    membersNavVM.selectMember(it)
                    navController.navigate("MemberDetailScreen")
                },
                onBack = { navController.navigate("MembersMainScreen") },
                membersVM = membersVM
            )
        }

        defaultComposable("AchievementScreen") {
            AchievementScreen(
                onViewDetail = {
                    achievementsNavVM.selectAchievement(it)
                    navController.navigate("AchievementDetailScreen")
                },
                onBack = { navController.navigate("MembersMainScreen") },
                achievementsVM = achievementsVM
            )
        }

        defaultComposable("EquipmentDetailScreen") {
            val equipment = resourcesNavVM.selectedEquipment.collectAsState().value
            equipment?.let {
                EquipmentDetailScreen(
                    equipment = it,
                    onBackClick = { navController.popBackStack() },
                    onDeleteClick = {},
                    onEditClick = {}
                )
            }
        }

        defaultComposable("CompletedProjectDetailScreen") {
            val completedProject =
                completedProjectNavVM.selectedCompletedProject.collectAsState().value
            completedProject?.let {
                CompletedProjectDetailScreen(
                    project = it,
                    onBackClick = { navController.navigate("CompletedProjectsScreen") },
                    onDeleteClick = {},
                    onEditClick = {},
                    isAdmin = false
                )
            }
        }

        defaultComposable("OngoingProjectDetailScreen") {
            val ongoingProject = ongoingProjectNavVM.selectedOngoingProject.collectAsState().value
            ongoingProject?.let {
                OngoingProjectDetailScreen(
                    project = it,
                    onBackClick = { navController.popBackStack() },
                    isAdmin = false
                )
            }
        }

        defaultComposable("MemberDetailScreen") {
            val member = membersNavVM.selectedMember.collectAsState().value
            member?.let {
                MemberDetailScreen(
                    member = member,
                    onBackClick = {
                        membersNavVM.clearSelection()
                        navController.navigate("TarsMemberScreen")
                    }
                )
            }
        }

        defaultComposable("SocialMediaScreen") {
            SocialMediaScreen(onBackClick = { navController.navigate("MembersMainScreen") })
        }

        defaultComposable("AchievementDetailScreen") {
            val achievement = achievementsNavVM.selectedAchievement.collectAsState().value
            achievement?.let {
                AchievementDetailScreen(
                    achievement = achievement,
                    onBackClick = { navController.navigate("AchievementScreen") }
                )
            }
        }

        defaultComposable("NotificationScreen") {
            NotificationScreen(
              isAdmin = false,
                notificationVM = notificationVM,
                onBackClick = { navController.popBackStack() }
            )
        }


        defaultComposable("AdminMainScreen") {
            AdminMainScreen(navController, resourcesVM, resourcesNavVM, authVM, membersVM)
        }

        defaultComposable("AdminResDetailScreen") {
            val equipment = resourcesNavVM.selectedEquipment.collectAsState().value
            equipment?.let {
                AdminResDetailScreen(
                    equipment = it,
                    onBackClick = {
                        resourcesNavVM.clearSelection()
                        navController.navigate("AdminMainScreen")
                    },
                    onDeleteClick = {
                        resourcesVM.deleteEquipment(
                            equipmentId = it.id,
                            onResult = { result ->
                                if (result) {
                                    Toast.makeText(
                                        navController.context,
                                        "Item Deleted",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    navController.navigate("AdminMainScreen")
                                } else {
                                    Toast.makeText(
                                        navController.context,
                                        "Failed",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        )
                    },
                    onEditClick = { navController.navigate("EditResource") },
                    isAdmin = true
                )
            }
        }



        defaultComposable("EditResource") {
            EditResource(
                onBackClick = { navController.popBackStack() },
                onSaveClick = { updatedEquipment ->
                    resourcesVM.updateEquipment(updatedEquipment) { result ->
                        if (result) {
                            resourcesNavVM.selectEquipment(updatedEquipment)

                            Toast.makeText(
                                navController.context,
                                "Updated successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                            navController.popBackStack()
                        } else {
                            Toast.makeText(
                                navController.context,
                                "Update failed",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                },
                resourcesNavVM = resourcesNavVM
            )
        }

        defaultComposable("EditMember") {
            EditMember(
                onBackClick = { navController.popBackStack() },
                onSaveClick = { updatedMember ->
                    membersVM.updateMember(
                        updatedMember,
                        onResult = { result ->
                            if (result) {
                                membersNavVM.selectMember(updatedMember)
                                Toast.makeText(
                                    navController.context,
                                    "Updated successfully",
                                    Toast.LENGTH_SHORT
                                ).show()
                                navController.popBackStack()
                            } else {
                                Toast.makeText(
                                    navController.context,
                                    "Update failed",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    )
                },
                memberNavVM = membersNavVM
            )
        }


        defaultComposable("AdminCompletedProjectScreen") {
            AdminCompletedProjectsScreen(
                onViewDetail = {
                    completedProjectNavVM.selectCompletedProject(it)
                    navController.navigate("AdminCompletedProjectDetail")
                },
                onBack = { navController.navigate("AdminMainScreen") },
                onAddProject = {
                    completedProjectNavVM.clearSelection()
                    navController.navigate("AddCompletedProject")
                },
                completedProjectVM = completedProjectVM
            )
        }

        defaultComposable("AdminCompletedProjectDetail") {
            val completedProject =
                completedProjectNavVM.selectedCompletedProject.collectAsState().value
            completedProject?.let {
                AdminCompletedProjectDetail(
                    project = it,
                    onBackClick = { navController.navigate("AdminCompletedProjectScreen") },
                    onDeleteClick = {
                        completedProjectVM.deleteCompletedProject(
                            id = it.id,
                            onResult = { result ->
                                if (result) {
                                    Toast.makeText(
                                        navController.context,
                                        "Item Deleted",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    navController.navigate("AdminCompletedProjectScreen")
                                } else {
                                    Toast.makeText(
                                        navController.context,
                                        "Failed",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        )
                    },
                    onEditClick = { navController.navigate("EditCompletedProject") }
                )
            }
        }

        defaultComposable("AdminOngoingProjectScreen") {
            AdminOngoingProjectScreen(
                onViewDetail = {
                    ongoingProjectNavVM.selectOngoingProject(it)
                    navController.navigate("AdminOngoingProjectDetail")
                },
                onBack = { navController.navigate("AdminMainScreen") },
                onAddProjectClick = {
                    ongoingProjectNavVM.clearSelection()
                    navController.navigate("AddOngoingProject")
                },
                ongoingProjectVM = ongoingProjectVM
            )
        }

        defaultComposable("AdminOngoingProjectDetail") {
            val ongoingProject = ongoingProjectNavVM.selectedOngoingProject.collectAsState().value
            ongoingProject?.let {
                AdminOngoingProjectDetail(
                    project = it,
                    onBackClick = { navController.popBackStack() },
                    onDeleteClick = {
                        ongoingProjectVM.deleteOngoingProject(
                            id = it.id,
                            onResult = { result ->
                                if (result) {
                                    Toast.makeText(
                                        navController.context,
                                        "Item Deleted",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    navController.popBackStack()
                                } else {
                                    Toast.makeText(
                                        navController.context,
                                        "Failed",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                            }
                        )
                    },
                    onEditClick = {
                        navController.navigate("EditOngoingProject")
                    }
                )
            }

        }

        defaultComposable("EditOngoingProject") {
            EditOngoingProject(
                onBackClick = { navController.popBackStack() },
                onSaveClick = { updated ->
                    ongoingProjectVM.updateOngoingProject(
                        id = updated.id,
                        updated = updated,
                        onResult = { result ->
                            if (result) {
                                ongoingProjectNavVM.selectOngoingProject(updated)
                                Toast.makeText(navController.context, "success", Toast.LENGTH_SHORT)
                                    .show()
                                navController.popBackStack()
                            } else {
                                Toast.makeText(navController.context, "failed", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    )
                },
                ongoingProjectNavVM = ongoingProjectNavVM
            )
        }

        defaultComposable("AdminAchievementsScreen") {

            AdminAchievementsScreen(
                onViewDetail = {
                    achievementsNavVM.selectAchievement(it)
                    navController.navigate("AdminAchievementDetail")
                },
                onBack = { navController.navigate("AdminMainScreen") },
                onAddAchievement = {
                    achievementsNavVM.clearSelection()
                    navController.navigate("AddAchievementScreen") },
                achievementsVM = achievementsVM
            )
        }

        defaultComposable("AdminAchievementDetail") {
            val achievement = achievementsNavVM.selectedAchievement.collectAsState().value
            achievement?.let {
                AdminAchievementDetail(
                    achievement = achievement,
                    onBackClick = { navController.navigate("AdminAchievementsScreen") },
                    onDeleteClick = {
                        achievementsVM.deleteAchievement(
                            id=it.id,
                            onResult = { result ->
                                if (result) {
                                    Toast.makeText(navController.context, "success", Toast.LENGTH_SHORT)
                                        .show()
                                    navController.popBackStack()
                                } else {
                                    Toast.makeText(navController.context, "failed", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        )
                    },
                    onEditClick = { navController.navigate("EditAchievement") }
                )
            }

        }

        defaultComposable("AdminTarsMemberScreen") {
            AdminTarsMemberScreen(
                onViewDetail = {
                    membersNavVM.selectMember(it)
                    navController.navigate("AdminTarsMemberDetailScreen")
                },
                onBack = { navController.navigate("AdminMainScreen") },
                onAddMember = {
                    membersNavVM.clearSelection()
                    navController.navigate("AddTarsMemberScreen")
                },
                membersVM = membersVM
            )
        }

        defaultComposable("AdminTarsMemberDetailScreen") {
            val member = membersNavVM.selectedMember.collectAsState().value
            member?.let {
                AdminTarsMemberDetail(
                    member = member,
                    onBackClick = { navController.navigate("AdminTarsMemberScreen") },
                    onDeleteClick = {
                        membersVM.deleteMember(
                            memberId = it.id,
                            onResult = { result ->
                                if (result) {
                                    Toast.makeText(
                                        navController.context,
                                        "Member Deleted",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    navController.navigate("AdminTarsMemberScreen")
                                } else {
                                    Toast.makeText(
                                        navController.context,
                                        "Failed",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        )
                    },
                    onEditClick = { navController.navigate("EditMember") }
                )
            }
        }

        defaultComposable("AdminSocialMediaScreen") {
            AdminSocialMedia(
                onBackClick = { navController.navigate("AdminMainScreen") },
                onEditClick = {}
            )
        }

        defaultComposable("AdminNotificationScreen") {
            AdminNotificationScreen(
                notificationVM = notificationVM,
                onBackClick = { navController.navigate("AdminMainScreen") },
                onSendNotificationClick = { navController.navigate("SendNotificationScreen") }
            )
        }

        defaultComposable("SendNotificationScreen") {
            SendNotificationScreen(
                onBackClick = { navController.navigate("AdminNotificationScreen") },
                onSendClick = { title, description ->
                    notificationVM.createNotification(
                        title = title,
                        description = description,
                        onResult = { result ->
                            if (result) {
                                Toast.makeText(navController.context, "Notification Sent", Toast.LENGTH_SHORT)
                                    .show()
                            }
                            else{
                                Toast.makeText(navController.context, "Failed", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                        )
                }
            )
        }

        defaultComposable("AddEquipment") {
            AddEquipment(
                resourcesNavVM = resourcesNavVM,
                onBackClick = {
                    navController.popBackStack()
                },
                onSaveClick = {
                    resourcesVM.saveEquipment(it) { success ->
                        if (success) {
                            Log.d("AddEquipment", "Equipment Added")
                            Toast.makeText(
                                navController.context,
                                "Equipment Added",
                                Toast.LENGTH_SHORT
                            ).show()
                            navController.popBackStack()
                        } else {
                            Log.d("AddEquipment", "Failed to Add Equipment")
                            Toast.makeText(
                                navController.context,
                                "Failed to Add Equipment",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            )
        }


        defaultComposable("AddCompletedProject") {
            AddCompletedProjectScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onSaveClick = {
                    completedProjectVM.addCompletedProject(
                        completedProject = it,
                        onResult = { result ->
                            if (result) {
                                Toast.makeText(navController.context, "success", Toast.LENGTH_SHORT)
                                    .show()
                                navController.popBackStack()
                            } else {
                                Toast.makeText(navController.context, "failed", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    )
                },
                heading = "Add Project",
                completedProjectNavVM = completedProjectNavVM
            )
        }
        defaultComposable("EditCompletedProject") {
            EditCompletedProjectScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onSaveClick = { updated ->
                    completedProjectVM.updateCompletedProject(
                        id = updated.id,
                        updated = updated,
                        onResult = { result ->
                            if (result) {
                                completedProjectNavVM.selectCompletedProject(updated)
                                Toast.makeText(navController.context, "success", Toast.LENGTH_SHORT)
                                    .show()
                                navController.popBackStack()
                            } else {
                                Toast.makeText(navController.context, "failed", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    )
                },
                completedProjectNavVM = completedProjectNavVM
            )
        }
        defaultComposable("AddOngoingProject") {
            AddOngoingProjectScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onSaveClick = {
                    ongoingProjectVM.addOngoingProject(
                        ongoingProject = it,
                        onResult = { result ->
                            if (result) {
                                Toast.makeText(navController.context, "success", Toast.LENGTH_SHORT)
                                    .show()
                                navController.popBackStack()
                            } else {
                                Toast.makeText(navController.context, "failed", Toast.LENGTH_SHORT)
                                    .show()

                            }
                        }
                    )
                },
                ongoingProjectNavVM = ongoingProjectNavVM,
                heading = "Add Ongoing Project"
            )
        }


        defaultComposable("AddTarsMemberScreen") {
            AddTarsMemberScreen(
                memberNavVM = membersNavVM,
                onBackClick = {
                    navController.popBackStack()
                },
                onSaveClick = {
                    membersVM.addMember(
                        member = it,
                        onResult = { result ->
                            if (result) {
                                Toast.makeText(navController.context, "success", Toast.LENGTH_SHORT)
                                    .show()
                                navController.popBackStack()
                            } else {
                                Toast.makeText(navController.context, "failed", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    )
                }
            )
        }

        defaultComposable("AddAchievementScreen") {
            AddAchievementScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onSaveClick = {
                    achievementsVM.addAchievement(
                        achievement = it,
                        onResult = { result ->
                            if (result) {
                                Toast.makeText(navController.context, "success", Toast.LENGTH_SHORT)
                                    .show()
                                navController.popBackStack()
                            } else {
                                Toast.makeText(navController.context, "failed", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    )
                },
                heading = "Add Achievement",
                achievementsNavVM = achievementsNavVM
            )
        }
        defaultComposable("EditAchievement") {
            EditAchievement(
                onBackClick = {
                    navController.popBackStack()
                },
                onSaveClick = {
                    achievementsVM.updateAchievement(
                        id = it.id,
                        updated = it,
                        onResult = { result ->
                            if (result) {
                                achievementsNavVM.selectAchievement(it)
                                Toast.makeText(navController.context, "success", Toast.LENGTH_SHORT)
                                    .show()
                                navController.popBackStack()
                            }
                            else{
                                Toast.makeText(navController.context, "failed", Toast.LENGTH_SHORT).show()
                            }
                        }
                    )
                },
                        achievementsNavVM = achievementsNavVM
            )

        }

        defaultComposable("ManageAdminScreen"){
            ManageAdminSCreen(
                onBack = { navController.popBackStack() },
                onAddAdmin = { navController.navigate("AddAdminScreen") },
                adminVM = adminVM
            )
        }

        defaultComposable("AddAdminScreen"){
            AddAdminScreen(
                adminVM = adminVM,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        defaultComposable("AboutSocietyScreen"){
            AboutSocietyScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

