package com.example.projecttars

import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.projecttars.Admin.Achievements.AdminAchievementDetail
import com.example.projecttars.Admin.Achievements.AdminAchievementsScreen
import com.example.projecttars.Admin.AdminMainScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.example.projecttars.Admin.Login.AdminLogin
import com.example.projecttars.Admin.Profile.AdminNotificationScreen
import com.example.projecttars.Admin.Profile.SendNotificationScreen
import com.example.projecttars.Admin.Projects.Completed.AdminCompletedProjectDetail
import com.example.projecttars.Admin.Projects.Completed.AdminCompletedProjectsScreen
import com.example.projecttars.Admin.Projects.Ongoing.AdminOngoingProjectDetail
import com.example.projecttars.Admin.Projects.Ongoing.AdminOngoingProjectScreen
import com.example.projecttars.Admin.Resources.AdminResDetailScreen
import com.example.projecttars.Admin.SocialMedia.AdminSocialMedia
import com.example.projecttars.Admin.TarsMembers.AdminTarsMemberDetail
import com.example.projecttars.Admin.TarsMembers.AdminTarsMemberScreen
import com.example.projecttars.Common.RoleSelectionScreen
import com.example.projecttars.DataModels.Achievement
import com.example.projecttars.DataModels.CompletedProjectDetail
import com.example.projecttars.DataModels.EquipmentDetail
import com.example.projecttars.DataModels.MemberDetail
import com.example.projecttars.DataModels.NotificationItem
import com.example.projecttars.DataModels.OngoingProjectDetail
import com.example.projecttars.DataModels.Project
import com.example.projecttars.DataModels.TarsMember
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
import com.example.projecttars.Members.Resources.EquipmentDetailScreen
import com.example.projecttars.Members.SocialMedia.SocialMediaScreen
import com.example.projecttars.Members.TarsMembers.MemberDetailScreen
import com.example.projecttars.Members.TarsMembers.TarsMembersScreen
import defaultComposable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavGraph(navController: NavHostController) {
    AnimatedNavHost(navController, startDestination = "role_selection") {

        defaultComposable("role_selection") {
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

        defaultComposable("MembersMainScreen") { MainScreen(navController) }

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
            val members = listOf(
                TarsMember("Sundram Kumar", "Member 1", "m", "B524066", "AppDev"),
                TarsMember("Rahul Kumar", "Member 2", "f", "B524066", "WebDev"),
                TarsMember("Sundram Kumar", "Member 1", "m", "B524066", "AI/ML"),
                TarsMember("Rahul Kumar", "Member 2", "f", "B524066", "Management")
            )
            TarsMembersScreen(
                tarsMembers = members,
                onViewDetail = { navController.navigate("MemberDetailScreen") },
                onBack = { navController.navigate("MembersMainScreen") },
            )
        }

        defaultComposable("AchievementScreen") {
            val achievements = listOf(
                Achievement(title = "Best Project Award", shortDescription = "Awarded for outstanding project contributions", imageResId =  R.drawable.tarslogo),
                Achievement(title = "Community Service", shortDescription = "Recognized for helping the community", imageResId =  R.drawable.tarslogo)
            )
            AchievementScreen(
                achievements = achievements,
                onViewDetail = { navController.navigate("AchievementDetailScreen") },
                onBack = { navController.navigate("MembersMainScreen") },
            )
        }

        defaultComposable("EquipmentDetailScreen") {
            EquipmentDetailScreen(
                equipment = EquipmentDetail(
                    id = 1,
                    name = "Equipment Name",
                    imageResId = R.drawable.tarslogo,
                    description = "Equipment Description",
                    isAvailable = true,
                    youtubeUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ",
                    documentationUrl = "https://example.com/documentation"
                ),
                onBackClick = { navController.navigate("MembersMainScreen")}
            )
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
                    imageResId = R.drawable.tarslogo
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
                    imageResId = R.drawable.tarslogo,
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
            MemberDetailScreen(
                member = MemberDetail(
                    name = "Member Name",
                    imageResId = R.drawable.tarslogo,
                    domain = "Domain",
                    id = "ID",
                    branch = "Branch",
                    designation = "Designation",
                    projects = listOf("Project 1", "Project 2"),
                    linkedinUrl = "https://www.linkedin.com/in/sundramkumar/"
                ),
                onBackClick = { navController.navigate("TarsMemberScreen") }
            )
        }

        defaultComposable("SocialMediaScreen") {
            SocialMediaScreen(onBackClick = { navController.navigate("MembersMainScreen") })
        }

        defaultComposable("AchievementDetailScreen") {
            AchievementDetailScreen(
                achievement = Achievement(
                    title = "Achievement Title",
                    shortDescription = "Achievement Description",
                    imageResId = R.drawable.tarslogo
                ),
                onBackClick = { navController.navigate("AchievementScreen") }
            )
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
            AdminMainScreen(navController)
        }

        defaultComposable("AdminResDetailScreen") {
            AdminResDetailScreen(
                equipment = EquipmentDetail(
                    id = 1,
                    name = "Equipment Name",
                    imageResId = R.drawable.tarslogo,
                    description = "Equipment Description",
                    isAvailable = true,
                    youtubeUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ",
                    documentationUrl = "https://example.com/documentation"
                ),
                onBackClick = { navController.navigate("AdminMainScreen") },
                onDeleteClick = {},
                onEditClick = {}
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
                onAddProject = {}
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
                    imageResId = R.drawable.tarslogo
                ),
                onBackClick = { navController.navigate("AdminCompletedProjectScreen") },
                onDeleteClick = {},
                onEditClick = {}
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
                onAddProjectClick = {}
            )
        }

        defaultComposable("AdminOngoingProjectDetail") {
            AdminOngoingProjectDetail(
                project = OngoingProjectDetail(
                    name = "Project Name",
                    imageResId = R.drawable.tarslogo,
                    youtubeUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ",
                    developers = listOf("Developer 1", "Developer 2"),
                    guidedBy = listOf("Guide 1", "Guide 2"),
                    equipmentUsed = listOf("Equipment 1", "Equipment 2"),
                    techStack = listOf("Tech 1", "Tech 2"),
                    problemSolved = "Problem Solved Here"
                ),
                onDeleteClick = {},
                onEditClick = {},
                onBackClick = { navController.navigate("AdminOngoingProjectScreen") },
            )
        }

        defaultComposable("AdminAchievementsScreen") {
           AdminAchievementsScreen(
               achievements = listOf(
                   Achievement(title = "Achievement Title", shortDescription = "Achievement Description", imageResId = R.drawable.tarslogo),
                   Achievement(title = "Achievement Title", shortDescription = "Achievement Description", imageResId = R.drawable.tarslogo)
               ),
               onViewDetail = { navController.navigate("AdminAchievementDetail") },
               onBack = { navController.navigate("AdminMainScreen") },
               onAddAchievement = {}
           )
        }

        defaultComposable("AdminAchievementDetail") {
            AdminAchievementDetail(
                achievement = Achievement(title = "Achievement Title", shortDescription = "Achievement Description", imageResId = R.drawable.tarslogo),
                onBackClick = { navController.navigate("AdminAchievementsScreen") },
                onDeleteClick = {},
                onEditClick = {}
            )
        }

        defaultComposable("AdminTarsMemberScreen") {
            AdminTarsMemberScreen(
                tarsMembers= listOf(
                    TarsMember("Sundram Kumar", "Member 1", "m", "B524066", "AppDev"),
                    TarsMember("Rahul Kumar", "Member 2", "f", "B524066", "WebDev"),
                ),
                onViewDetail = { navController.navigate("AdminTarsMemberDetailScreen") },
                onBack = { navController.navigate("AdminMainScreen") },
                onAddMember = {}
            )
        }

        defaultComposable("AdminTarsMemberDetailScreen") {
            AdminTarsMemberDetail(
                member = MemberDetail(
                    name = "Member Name",
                    imageResId = R.drawable.tarslogo,
                    domain = "Domain",
                    id = "ID",
                    branch = "Branch",
                    designation = "Designation",
                    projects = listOf("Project 1", "Project 2"),
                    linkedinUrl = "https://www.linkedin.com/in/sundramkumar/"
                ),
                onBackClick = { navController.navigate("AdminTarsMemberScreen") },
                onDeleteClick = {},
                onEditClick = {}
            )
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



    }
}

