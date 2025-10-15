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
import com.example.projecttars.DataModels.CompletedProjectDetail
import com.example.projecttars.Members.UiElements.CompletedProjectCard
import com.example.projecttars.ViewModels.Firebase.CompletedProjectVM
import com.example.projecttars.ui.theme.*
import com.example.projecttars.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompletedProjectsScreen(
    onViewDetail: (CompletedProjectDetail) -> Unit,
    onBack: () -> Unit,
    isAdmin: Boolean = false,
    onAddClick: () -> Unit = {},
    completedProjectsVM: CompletedProjectVM
) {
    val completedProjects = completedProjectsVM.completedProjects.collectAsState().value
    LaunchedEffect(Unit) {
        completedProjectsVM.observeCompletedProjects()
    }
    BackHandler {
        onBack()
    }

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val screenWidth = maxWidth.value
        val screenHeight = maxHeight.value

        // Dynamic sizing
        val horizontalPadding = (screenWidth * 0.04f).dp
        val verticalSpacing = (screenHeight * 0.015f).dp
        val iconSize = (screenWidth * 0.07f).dp
        val addIconSize = (screenWidth * 0.06f).dp
        val headingFontSize = (screenWidth * 0.055f).sp
        val noProjectFontSize = (screenWidth * 0.05f).sp

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(DarkGrayBlue)
                .fillMaxSize()
        ) {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = horizontalPadding, top = verticalSpacing, end = horizontalPadding)
                    .systemBarsPadding()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier
                            .size(iconSize)
                            .clickable { onBack() }
                    )
                    Spacer(modifier = Modifier.width(horizontalPadding))
                    Text(
                        text = "Completed Projects",
                        fontFamily = FontFamily(Font(R.font.poppinsbold)),
                        color = AccentBlue,
                        fontSize = headingFontSize,
                    )
                }

                if (isAdmin) {
                    IconButton(onClick = onAddClick) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add Project",
                            tint = Color.White,
                            modifier = Modifier.size(addIconSize)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(verticalSpacing))

            if (completedProjects.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No Project Found",
                        color = Color.White,
                        fontSize = noProjectFontSize,
                        fontFamily = FontFamily(Font(R.font.poppinsregular))
                    )
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(verticalSpacing),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(completedProjects) { completedProject ->
                        CompletedProjectCard(
                            imageUrl = completedProject.imageUrl,
                            title = completedProject.name,
                            shortDescription = completedProject.problemSolved,
                            onViewDetail = { onViewDetail(completedProject) },

                        )
                    }
                }
            }
        }
    }
}
