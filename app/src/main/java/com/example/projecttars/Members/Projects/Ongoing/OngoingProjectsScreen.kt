package com.example.projecttars.Members.Projects.Ongoing

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.example.projecttars.Members.UiElements.OngoingProjectCard
import com.example.projecttars.ui.theme.AccentOrange
import com.example.projecttars.ui.theme.DarkGrayBlue
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.example.projecttars.DataModels.OngoingProjectDetail
import com.example.projecttars.ViewModels.Firebase.OngoingProjectVM


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OngoingProjectsScreen(
    onViewDetail: (OngoingProjectDetail) -> Unit,
    onBack: () -> Unit,
    isAdmin: Boolean = false,
    onAddProjectClick: (() -> Unit)? = null,
    ongoingProjectVM: OngoingProjectVM
) {
    val ongoingProjects=ongoingProjectVM.completedProjects.collectAsState().value
    LaunchedEffect(Unit) {
        ongoingProjectVM.observeOngoingProjects()
    }
    BackHandler { onBack() }

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
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .systemBarsPadding()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier
                        .size(30.dp)
                        .clickable(onClick = onBack)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Ongoing Projects",
                    fontFamily = FontFamily(Font(R.font.poppinsbold)),
                    color = AccentOrange,
                    fontSize = 25.sp,
                )
            }

            if (isAdmin && onAddProjectClick != null) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Project",
                    tint =Color.White,
                    modifier = Modifier
                        .size(30.dp)
                        .clickable { onAddProjectClick() }
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (ongoingProjects.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No Project Found",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppinsregular))
                )
            }
        }else{
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(ongoingProjects) { ongoingProject ->
                    OngoingProjectCard(
                        imageUrl = ongoingProject.imageUrl,
                        title = ongoingProject.name,
                        shortDescription =  ongoingProject.problemSolved,
                        onViewDetail = { onViewDetail( ongoingProject ) }
                    )
                }
            }
        }
    }
}
