package com.example.projecttars

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.runtime.Composable
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
import com.example.projecttars.DataModels.Achievement
import com.example.projecttars.DataModels.CompletedProjectDetail
import com.example.projecttars.DataModels.MemberDetail
import com.example.projecttars.DataModels.NotificationItem
import com.example.projecttars.DataModels.OngoingProjectDetail
import com.example.projecttars.DataModels.Project
import com.example.projecttars.Members.Achievements.AchievementDetailScreen
import com.example.projecttars.Members.MainScreen
import com.example.projecttars.Members.Projects.Completed.CompletedProjectsScreen
import com.example.projecttars.Members.Login.MembersLogin
import com.example.projecttars.Members.Profile.MessageAdminScreen
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.projecttars.Admin.Resources.EditResource
import com.example.projecttars.Admin.TarsMembers.EditMember
import com.example.projecttars.Members.Resources.EquipmentDetailScreen
import com.example.projecttars.ViewModels.Firebase.AchievementsVM
import com.example.projecttars.ViewModels.Firebase.MembersVM
import com.example.projecttars.ViewModels.NavigationData.AchievementsNavVM
import com.example.projecttars.ViewModels.NavigationData.MemberNavVM

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavGraph(navController: NavHostController) {
    val resourcesVM: ResourcesVM = viewModel()
    val membersVM: MembersVM=viewModel()
    val resourcesNavVM: ResourcesNavVM=viewModel()
    val membersNavVM: MemberNavVM=viewModel()
    val achievementsVM: AchievementsVM=viewModel()
    val achievementsNavVM: AchievementsNavVM =viewModel()
    NavHost(navController, startDestination = "role_selection") {

        composable("role_selection") {
            RoleSelectionScreen { selectedRole ->
                when (selectedRole) {
                    "Members" -> navController.navigate("MembersLogin")
                    "Admin" -> navController.navigate("AdminLogin")
                }
            }
        }

        defaultComposable("MembersLogin") {
            MembersLogin { username, password ->
                if (username.isNotBlank() && password.isNotBlank()) {
                    navController.navigate("MembersMainScreen") {
                        popUpTo("MembersLogin") { inclusive = true }
                        launchSingleTop = true
                    }
                }
            }
        }

        defaultComposable("AdminLogin") {
            AdminLogin { username, password ->
                if (username.isNotBlank() && password.isNotBlank()) {
                    navController.navigate("AdminMainScreen") {
                        popUpTo("AdminLogin") { inclusive = true }
                        launchSingleTop = true
                    }
                }
            }
        }

        defaultComposable("MembersMainScreen") { MainScreen(navController,resourcesVM,resourcesNavVM) }

        defaultComposable("CompletedProjectsScreen") {
            val projects = listOf(
                Project(1, R.drawable.tarslogo, "Project 1", "Short description"),
                Project(2, R.drawable.tarslogo, "Project 2", "Short description"),
                Project(3, R.drawable.tarslogo, "Project 3", "Short description")
            )
            CompletedProjectsScreen(
                projects = projects,
                onViewDetail = { navController.navigate("CompletedProjectDetailScreen") },
                onBack = { navController.navigate("MembersMainScreen") },
            )
        }

        defaultComposable("OngoingProjectsScreen") {
            val projects = listOf(
                Project(1, R.drawable.tarslogo, "Project 1", "Short description"),
                Project(2, R.drawable.tarslogo, "Project 2", "Short description"),
                Project(3, R.drawable.tarslogo, "Project 3", "Short description")
            )
            OngoingProjectsScreen(
                projects = projects,
                onViewDetail = { navController.navigate("OngoingProjectDetailScreen") },
                onBack = { navController.navigate("MembersMainScreen") },
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
            val equipment=resourcesNavVM.selectedEquipment.collectAsState().value
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
            CompletedProjectDetailScreen(
                project = CompletedProjectDetail(
                    name = "Project Name",
                    developers = listOf("Developer 1", "Developer 2"),
                    guidedBy = listOf("Guided By 1", "Guided By 2"),
                    equipmentUsed = listOf("Equipment 1", "Equipment 2"),
                    techStack = listOf("Tech 1", "Tech 2"),
                    problemSolved = "Problem Solved",
                    youtubeUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ",
                    imageUrl = "https://res.cloudinary.com/dnewwgoua/image/upload/v1758696099/STK-20230722-WA0326_bajags.webp"
                ),
                onBackClick = { navController.navigate("CompletedProjectsScreen") },
                onEditClick = {},
                isAdmin = false,
                onDeleteClick = {}
            )
        }

        defaultComposable("OngoingProjectDetailScreen") {
            OngoingProjectDetailScreen(
                project = OngoingProjectDetail(
                    name = "Project Name",
                    imageUrl = "https://res.cloudinary.com/dnewwgoua/image/upload/v1758696099/STK-20230722-WA0326_bajags.webp",
                    youtubeUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ",
                    developers = listOf("Developer 1", "Developer 2"),
                    guidedBy = listOf("Guide 1", "Guide 2"),
                    equipmentUsed = listOf("Equipment 1", "Equipment 2"),
                    techStack = listOf("Tech 1", "Tech 2"),
                    problemSolved = "Problem Solved Here"
                ),
                onBackClick = { navController.navigate("OngoingProjectsScreen") }
            )
        }

        defaultComposable("MemberDetailScreen") {
            val member =membersNavVM.selectedMember.collectAsState().value
            member?.let {
                MemberDetailScreen(
                    member = member,
                    onBackClick = {
                        membersNavVM.clearSelection()
                        navController.navigate("TarsMemberScreen") }
                )
            }
        }

        defaultComposable("SocialMediaScreen") {
            SocialMediaScreen(onBackClick = { navController.navigate("MembersMainScreen") })
        }

        defaultComposable("AchievementDetailScreen") {
            val achievement=achievementsNavVM.selectedAchievement.collectAsState().value
            achievement?.let {
                AchievementDetailScreen(
                    achievement = achievement,
                    onBackClick = { navController.navigate("AchievementScreen") }
                )
            }
        }

        defaultComposable("NotificationScreen") {
            NotificationScreen(
                notifications = listOf(
                    NotificationItem("Notification Title", "Notification Description", "Notification Date"),
                    NotificationItem("Notification Title", "Notification Description", "Notification Date"),
                    NotificationItem("Notification Title", "Notification Description", "Notification Date")
                ),
                onBackClick = { navController.popBackStack() }
            )
        }

        defaultComposable("MessageAdminScreen") {
            MessageAdminScreen(
                onBackClick = { navController.popBackStack() },
                onSendClick = { title, description -> /* Handle send message */ }
            )
        }

        defaultComposable("AdminMainScreen") {
            AdminMainScreen(navController, resourcesVM,resourcesNavVM)
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
                            onResult = {result->
                                if(result){
                                    Toast.makeText(navController.context,"Item Deleted", Toast.LENGTH_SHORT).show()
                                    navController.navigate("AdminMainScreen")
                                }
                                else{
                                    Toast.makeText(navController.context,"Failed", Toast.LENGTH_SHORT).show()
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

                            Toast.makeText(navController.context, "Updated successfully", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        } else {
                            Toast.makeText(navController.context, "Update failed", Toast.LENGTH_SHORT).show()
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
                    membersVM.updateMember(updatedMember,
                        onResult = { result ->
                            if (result) {
                                membersNavVM.selectMember(updatedMember)

                                Toast.makeText(navController.context, "Updated successfully", Toast.LENGTH_SHORT).show()
                                navController.popBackStack()
                            } else {
                                Toast.makeText(navController.context, "Update failed", Toast.LENGTH_SHORT).show()
                            }
                    }
                    )
                },
                memberNavVM = membersNavVM
            )
        }


        defaultComposable("AdminCompletedProjectScreen") {
            AdminCompletedProjectsScreen(
                projects = listOf(
                    Project(1, R.drawable.tarslogo, "Project 1", "Short description"),
                    Project(2, R.drawable.tarslogo, "Project 2", "Short description"),
                    Project(1, R.drawable.tarslogo, "Project 1", "Short description"),
                    Project(2, R.drawable.tarslogo, "Project 2", "Short description"),
                ),
                onViewDetail = { navController.navigate("AdminCompletedProjectDetail") },
                onBack = { navController.navigate("AdminMainScreen") },
                onAddProject = {navController.navigate("AddCompletedProject")}
            )
        }

        defaultComposable("AdminCompletedProjectDetail") {
            AdminCompletedProjectDetail(
                project = CompletedProjectDetail(
                    name = "Project Name",
                    developers = listOf("Developer 1", "Developer 2"),
                    guidedBy = listOf("Guide 1", "Guide 2"),
                    equipmentUsed = listOf("Equipment 1", "Equipment 2"),
                    techStack = listOf("Tech 1", "Tech 2"),
                    problemSolved = "Problem Solved Here",
                    youtubeUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ",
                   imageUrl = "https://res.cloudinary.com/dnewwgoua/image/upload/v1758696099/STK-20230722-WA0326_bajags.webp"
                ),
                onBackClick = { navController.navigate("AdminCompletedProjectScreen") },
                onDeleteClick = {},
                onEditClick = {navController.navigate("AddCompletedProject")}
            )
        }

        defaultComposable("AdminOngoingProjectScreen") {
            AdminOngoingProjectScreen(
                projects = listOf(
                    Project(1, R.drawable.tarslogo, "Project 1", "Short description"),
                    Project(2, R.drawable.tarslogo, "Project 2", "Short description"),
                ),
                onViewDetail = { navController.navigate("AdminOngoingProjectDetail") },
                onBack = { navController.navigate("AdminMainScreen") },
                onAddProjectClick = {navController.navigate("AddOngoingProject")}
            )
        }

        defaultComposable("AdminOngoingProjectDetail") {
            AdminOngoingProjectDetail(
                project = OngoingProjectDetail(
                    name = "Project Name",
                    imageUrl = "",
                    youtubeUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ",
                    developers = listOf("Developer 1", "Developer 2"),
                    guidedBy = listOf("Guide 1", "Guide 2"),
                    equipmentUsed = listOf("Equipment 1", "Equipment 2"),
                    techStack = listOf("Tech 1", "Tech 2"),
                    problemSolved = "Problem Solved Here"
                ),
                onDeleteClick = {},
                onEditClick = {navController.navigate("AddOngoingProject")},
                onBackClick = { navController.navigate("AdminOngoingProjectScreen") },
            )
        }

        defaultComposable("AdminAchievementsScreen") {

           AdminAchievementsScreen(
               onViewDetail = {
                   achievementsNavVM.selectAchievement(it)
                   navController.navigate("AdminAchievementDetail") },
               onBack = { navController.navigate("AdminMainScreen") },
               onAddAchievement = {navController.navigate("AddAchievementScreen")},
               achievementsVM = achievementsVM
           )
        }

        defaultComposable("AdminAchievementDetail") {
            val achievement=achievementsNavVM.selectedAchievement.collectAsState().value
            achievement?.let {
                AdminAchievementDetail(
                    achievement =achievement ,
                    onBackClick = { navController.navigate("AdminAchievementsScreen") },
                    onDeleteClick = {},
                    onEditClick = {navController.navigate("AddAchievementScreen")}
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
                    navController.navigate("AddTarsMemberScreen")},
                membersVM=membersVM
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
                            onResult = { result->
                                if(result){
                                    Toast.makeText(navController.context,"Member Deleted", Toast.LENGTH_SHORT).show()
                                }else{
                                    Toast.makeText(navController.context,"Failed", Toast.LENGTH_SHORT).show()
                                }
                            }
                        )
                    },
                    onEditClick = {navController.navigate("EditMember")}
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
                notifications = listOf(
                    NotificationItem("Notification Title", "Notification Description", "Notification Date"),
                    NotificationItem("Notification Title", "Notification Description", "Notification Date"),
                    NotificationItem("Notification Title", "Notification Description", "Notification Date")
                ),
                onBackClick = { navController.navigate("AdminMainScreen") },
                onSendNotificationClick = {navController.navigate("SendNotificationScreen")}
            )
        }

        defaultComposable("SendNotificationScreen") {
            SendNotificationScreen(
                onBackClick = { navController.navigate("AdminMainScreen") },
                onSendClick = { title, description -> /* Handle send notification */ }
            )
        }

        defaultComposable("AddEquipment") {
            AddEquipment(
                resourcesNavVM=resourcesNavVM,
                onBackClick = {
                    navController.popBackStack()
                },
                onSaveClick = {
                    resourcesVM.saveEquipment(it) { success ->
                        if (success) {
                            Log.d("AddEquipment", "Equipment Added")
                            Toast.makeText(navController.context, "Equipment Added", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        } else {
                            Log.d("AddEquipment", "Failed to Add Equipment")
                            Toast.makeText(navController.context, "Failed to Add Equipment", Toast.LENGTH_SHORT).show()
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

                }
            )
        }
        defaultComposable("AddOngoingProject") {
            AddOngoingProjectScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onSaveClick = {}
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
                            if(result){
                                Toast.makeText(navController.context,"success",Toast.LENGTH_SHORT).show()
                                navController.popBackStack()
                            } else {
                                Toast.makeText(navController.context,"failed",Toast.LENGTH_SHORT).show()
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
                          if(result){
                              Toast.makeText(navController.context,"success",Toast.LENGTH_SHORT).show()
                              navController.popBackStack()
                          } else {
                              Toast.makeText(navController.context,"failed",Toast.LENGTH_SHORT).show()
                          }
                      }
                  )
              }
          )
        }





    }
}

