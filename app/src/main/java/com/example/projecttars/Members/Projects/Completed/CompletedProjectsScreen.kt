package com.example.projecttars.Members.Projects.Completed


import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projecttars.R
import com.example.projecttars.DataModels.Project
import com.example.projecttars.Members.UiElements.CompletedProjectCard
import com.example.projecttars.ui.theme.AccentBlue
import com.example.projecttars.ui.theme.DarkGrayBlue
import com.example.projecttars.ui.theme.DarkSlate


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompletedProjectsScreen(
    projects: List<Project>,
    onViewDetail: (Project) -> Unit,
    onBack: () -> Unit,

) {
    BackHandler {
      onBack()
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
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(DarkGrayBlue)
            .fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
                .padding(start = 16.dp,top=10.dp)
                .systemBarsPadding()
        ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                        . clickable(
                        onClick = onBack
                    )
                )
            Text(
                text = "Completed Projects",
                fontFamily = FontFamily(Font(R.font.poppinsbold)),
                color = AccentBlue,
                fontSize = 25.sp,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(projects) { project ->
                CompletedProjectCard(
                    imageResId = project.imageResId,
                    title = project.title,
                    shortDescription = project.shortDescription,
                    onViewDetail = { onViewDetail(project) }
                )
            }
        }
    }
}

