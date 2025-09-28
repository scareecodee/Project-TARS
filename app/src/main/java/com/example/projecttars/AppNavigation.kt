package com.example.projecttars

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.example.projecttars.Admin.Login.AdminLogin
import com.example.projecttars.Common.RoleSelectionScreen
import com.example.projecttars.DataModels.Achievement
import com.example.projecttars.DataModels.CompletedProjectDetail
import com.example.projecttars.DataModels.EquipmentDetail
import com.example.projecttars.DataModels.MemberDetail
import com.example.projecttars.DataModels.NotificationItem
import com.example.projecttars.DataModels.OngoingProjectDetail
import com.example.projecttars.DataModels.Project
import com.example.projecttars.DataModels.TarsMember
import com.example.projecttars.Members.Acheivments.AchievementDetailScreen
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

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavGraph(navController: NavHostController) {
    AnimatedNavHost(navController, startDestination = "role_selection") {


        composable(
            "role_selection",
            enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(500)
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -300 },
                    animationSpec = tween(500)
                )
            },
            popExitTransition = { fadeOut(tween(500)) }
        ) {
            RoleSelectionScreen { selectedRole ->
                when (selectedRole) {
                    "Members" -> navController.navigate("MembersLogin")
                    "Admin" -> navController.navigate("AdminLogin")
                }
            }
        }

        // Members login
        composable(
            "MembersLogin",
            enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(500)
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -300 },
                    animationSpec = tween(500)
                )
            },
            popExitTransition = { fadeOut(tween(500)) }
        ) {
            MembersLogin { username, password ->
                if (username.isNotBlank() && password.isNotBlank()) {
                    navController.navigate("MembersMainScreen") {
                        popUpTo("MembersLogin") { inclusive = true }
                        launchSingleTop = true
                    }
                }
            }
        }


        // Admin login
        composable(
            "AdminLogin",
            enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(500)
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -300 },
                    animationSpec = tween(500)
                )
            },
            popExitTransition = { fadeOut(tween(500)) }
        ) {
            AdminLogin { username, password ->

                if (username.isNotBlank() && password.isNotBlank()) {
                    navController.navigate("AdminHome") {
                        // Remove AdminLogin from backstack to prevent back press returning to login
                        popUpTo("AdminLogin") { inclusive = true }
                        launchSingleTop = true
                    }
                }
            }
        }


        // Members main screen
        composable(
            "MembersMainScreen",
            enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(500)
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -300 },
                    animationSpec = tween(500)
                )
            },
            popExitTransition = { fadeOut(tween(500)) }
        ) {
            MainScreen(navController)
        }

        // Completed projects
        composable(
            "CompletedProjectsScreen",
            enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(500)
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -300 },
                    animationSpec = tween(500)
                )
            },
            popExitTransition = { fadeOut(tween(500)) }) {
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

        // Ongoing projects
        composable(
            "OngoingProjectsScreen",
            enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(500)
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -300 },
                    animationSpec = tween(500)
                )
            },
            popExitTransition = { fadeOut(tween(500)) }) {
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


        // TarsMemberScreen
        composable(
            "TarsMemberScreen",
            enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(500)
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -300 },
                    animationSpec = tween(500)
                )
            },
            popExitTransition = { fadeOut(tween(500)) }) {
            val members = listOf(
                TarsMember("Sundram Kumar", "Member 1", "m", "B524066", "AppDev"),
                TarsMember("Rahul Kumar", "Member 2", "f", "B524066", "WebDev"),
                TarsMember("Sundram Kumar", "Member 1", "m", "B524066", "AI/ML"),
                TarsMember("Rahul Kumar", "Member 2", "f", "B524066", "Management"),
            )
            TarsMembersScreen(
                tarsMembers = members,
                onViewDetail = { navController.navigate("MemberDetailScreen") },
                onBack = { navController.navigate("MembersMainScreen") },
            )
        }

        //AchievementsScreen
        composable(
            "AchievementScreen",
            enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(500)
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -300 },
                    animationSpec = tween(500)
                )
            },
            popExitTransition = { fadeOut(tween(500)) }
        ) {
            val achievements = listOf(
                Achievement(
                    title = "Best Project Award",
                    shortDescription = "Awarded for outstanding project contributions",
                    imageResId = R.drawable.tarslogo
                ),
                Achievement(
                    title = "Community Service",
                    shortDescription = "Recognized for helping the community",
                    imageResId = R.drawable.tarslogo
                ),
                Achievement(
                    title = "Community Service",
                    shortDescription = "Recognized for helping the community",
                    imageResId = R.drawable.tarslogo
                ),
                Achievement(
                    title = "Community Service",
                    shortDescription = "Recognized for helping the community",
                    imageResId = R.drawable.tarslogo
                )
            )
            AchievementScreen(
                achievements = achievements,
                onViewDetail = {navController.navigate("AchievementDetailScreen") },
                onBack = { navController.navigate("MembersMainScreen") },
            )
        }


        // EquipmentDetailScreen
            composable(
                "EquipmentDetailScreen",
                enterTransition = { fadeIn(animationSpec = tween(500)) },
                exitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { -300 },
                        animationSpec = tween(500)
                    )
                },
                popEnterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { -300 },
                        animationSpec = tween(500)
                    )
                },
                popExitTransition = { fadeOut(tween(500)) }
            ) {
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
                    onBackClick = { navController.navigate("MembersMainScreen")},
                )
            }



        //CompletedProjectDetailScreen
        composable(
            "CompletedProjectDetailScreen",
            enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(500)
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -300 },
                    animationSpec = tween(500)
                )
            },
            popExitTransition = { fadeOut(tween(500)) }
        ) {
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
                onBackClick = { navController.navigate("CompletedProjectsScreen") }
            )
        }

        //OngoingProjectDetailScreen
        composable(
            "OngoingProjectDetailScreen",
            enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(500)
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -300 },
                    animationSpec = tween(500)
                )
            },
            popExitTransition = { fadeOut(tween(500)) }
        ) {
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

        //OngoingProjectDetailScreen
        composable(
            "MemberDetailScreen",
            enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(500)
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -300 },
                    animationSpec = tween(500)
                )
            },
            popExitTransition = { fadeOut(tween(500)) }
        ) {
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

        //OngoingProjectDetailScreen
        composable(
            "SocialMediaScreen",
            enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(500)
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -300 },
                    animationSpec = tween(500)
                )
            },
            popExitTransition = { fadeOut(tween(500)) }
        ) {
            SocialMediaScreen(
                onBackClick = { navController.navigate("MembersMainScreen") }
            )

        }

        composable(
            "AchievementDetailScreen",
            enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(500)
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -300 },
                    animationSpec = tween(500)
                )
            },
            popExitTransition = { fadeOut(tween(500)) }
        ) {
            AchievementDetailScreen(
                achievement = Achievement(
                    title = "Achievement Title",
                    shortDescription = "Achievement Description",
                    imageResId = R.drawable.tarslogo
                ),
                onBackClick = { navController.navigate("AchievementScreen") }
            )
        }

        composable(
            "NotificationScreen",
            enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(500)
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -300 },
                    animationSpec = tween(500)
                )
            },
            popExitTransition = { fadeOut(tween(500)) }
        ) {
            NotificationScreen(
                notifications =listOf(
                    NotificationItem(
                        title = "Notification Title",
                        description = "Notification Description",
                        date = "Notification Date"
                    ),
                    NotificationItem(
                        title = "Notification Title",
                        description = "Notification Description",
                        date = "Notification Date"
                    ),
                    NotificationItem(
                        title = "Notification Title",
                        description = "Notification Description",
                        date = "Notification Date"
                    )
                ),
                onBackClick = { navController.popBackStack() }
            )

        }

        composable(
            "MessageAdminScreen",
            enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(500)
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -300 },
                    animationSpec = tween(500)
                )
            },
            popExitTransition = { fadeOut(tween(500)) }
        ) {
            MessageAdminScreen(
                onBackClick = { navController.popBackStack() },
                onSendClick = { title, description ->
                    // Handle send message logic here
                }
            )

        }
        }
    }

