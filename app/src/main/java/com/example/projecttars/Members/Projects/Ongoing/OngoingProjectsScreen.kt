// OngoingProjectsScreen.kt
package com.example.projecttars.Members.Projects.Ongoing

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projecttars.DataModels.OngoingProjectDetail
import com.example.projecttars.Members.UiElements.OngoingProjectCard
import com.example.projecttars.R
import com.example.projecttars.ViewModels.Firebase.OngoingProjectVM
import com.example.projecttars.ui.theme.AccentBlue
import com.example.projecttars.ui.theme.AccentOrange
import com.example.projecttars.ui.theme.DarkGrayBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OngoingProjectsScreen(
    onViewDetail: (OngoingProjectDetail) -> Unit,
    onBack: () -> Unit,
    isAdmin: Boolean = false,
    onAddProjectClick: (() -> Unit)? = null,
    ongoingProjectVM: OngoingProjectVM
) {
    val ongoingProjects = ongoingProjectVM.completedProjects.collectAsState().value
    LaunchedEffect(Unit) { ongoingProjectVM.observeOngoingProjects() }
    BackHandler { onBack() }

    BoxWithConstraints(
        modifier = Modifier
            .background(DarkGrayBlue)
            .fillMaxSize()
    ) {
        val screenWidth = maxWidth.value
        val screenHeight = maxHeight.value

        // Dynamic sizes
        val horizontalPadding = (screenWidth * 0.04f).dp
        val verticalPadding = (screenHeight * 0.02f).dp
        val iconSize = (screenWidth * 0.07f).dp
        val headingFontSize = (screenWidth * 0.06f).sp
        val spacerHeight = (screenHeight * 0.015f).dp
        val cardSpacing = (screenHeight * 0.02f).dp

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(DarkGrayBlue)
                .fillMaxSize()
                .systemBarsPadding()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start= horizontalPadding,end=horizontalPadding, top = verticalPadding)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = AccentBlue,
                        modifier = Modifier
                            .size(iconSize)
                            .clickable(onClick = onBack)
                    )
                    Spacer(modifier = Modifier.width(horizontalPadding / 2))
                    Text(
                        text = "Ongoing Projects",
                        fontFamily = FontFamily(Font(R.font.poppinsbold)),
                        color = AccentBlue,
                        fontSize = headingFontSize,
                    )
                }

                if (isAdmin && onAddProjectClick != null) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Project",
                        tint = Color.White,
                        modifier = Modifier
                            .size(iconSize)
                            .clickable { onAddProjectClick() }
                    )
                }
            }

            Spacer(modifier = Modifier.height(spacerHeight))

            if (ongoingProjects.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No Project Found",
                        color = Color.White,
                        fontSize = headingFontSize,
                        fontFamily = FontFamily(Font(R.font.poppinsregular))
                    )
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(cardSpacing),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(ongoingProjects) { ongoingProject ->
                        OngoingProjectCard(
                            imageUrl = ongoingProject.imageUrl,
                            title = ongoingProject.name,
                            shortDescription = ongoingProject.problemSolved,
                            onViewDetail = { onViewDetail(ongoingProject) },
                            screenWidth = screenWidth,
                            screenHeight = screenHeight
                        )
                    }
                }
            }
        }
    }
}
