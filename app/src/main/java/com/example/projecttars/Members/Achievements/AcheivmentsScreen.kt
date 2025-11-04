package com.example.projecttars.Members.Projects.Completed

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projecttars.DataModels.Achievement
import com.example.projecttars.R
import com.example.projecttars.Members.UiElements.AchievementCard
import com.example.projecttars.ViewModels.Firebase.AchievementsVM
import com.example.projecttars.ui.theme.AccentBlue
import com.example.projecttars.ui.theme.DarkGrayBlue
import com.example.projecttars.ui.theme.DarkSlate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AchievementScreen(
    onViewDetail: (Achievement) -> Unit,
    onBack: () -> Unit,
    isAdmin: Boolean = false,
    onAddAchievement: () -> Unit = {},
    achievementsVM: AchievementsVM
) {
    BackHandler { onBack() }

    val achievements by achievementsVM.achievements.collectAsState()

    LaunchedEffect(achievements) {
        achievementsVM.observeAchievements()
    }

    TopAppBar(
        {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsTopHeight(WindowInsets.statusBars)
                    .background(DarkSlate)
            )
        }
    )

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGrayBlue)
    ) {
        val screenHeight = maxHeight.value
        val screenWidth = maxWidth.value

        val horizontalPadding = screenWidth * 0.04f
        val topPadding = screenHeight * 0.01f
        val iconSize = screenWidth * 0.08f
        val titleFontSize = (screenWidth * 0.065f).sp
        val spacerHeight = screenHeight * 0.02f
        val emptyFontSize = (screenWidth * 0.06f).sp

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
                .systemBarsPadding()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = horizontalPadding.dp,
                        end = horizontalPadding.dp,
                        top = topPadding.dp
                    )

            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint =AccentBlue,
                    modifier = Modifier
                        .size(iconSize.dp)
                        .clickable(onClick = onBack)
                )

                Spacer(modifier = Modifier.width((screenWidth * 0.025f).dp))

                Text(
                    text = "Achievements",
                    fontFamily = FontFamily(Font(R.font.poppinsbold)),
                    color = AccentBlue,
                    fontSize = titleFontSize,
                    modifier = Modifier.weight(1f)
                )

                if (isAdmin) {
                    IconButton(onClick = onAddAchievement) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add Achievement",
                            tint =Color.White,
                            modifier = Modifier.size(iconSize.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(spacerHeight.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy((screenHeight * 0.015f).dp),
                modifier = Modifier.fillMaxSize()
            ) {
                if (achievements.isEmpty()) {
                    item {
                        Box(
                            modifier = Modifier.fillParentMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "No Achievements Found",
                                fontSize = emptyFontSize,
                                color = Color.White,
                                fontFamily = FontFamily(Font(R.font.poppinsmedium))
                            )
                        }
                    }
                } else {
                    items(achievements) { achievement ->
                        AchievementCard(
                            imageUrl = achievement.imageUrl,
                            title = achievement.title,
                            shortDescription = achievement.shortDescription,
                            onViewDetail = { onViewDetail(achievement) }
                        )
                    }
                }
            }
        }
    }
}
