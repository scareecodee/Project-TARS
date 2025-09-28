package com.example.projecttars.Members.Projects.Completed

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import com.example.projecttars.DataModels.CompletedProjectDetail
import com.example.projecttars.R
import com.example.projecttars.ui.theme.*



@Composable
fun CompletedProjectDetailScreen(
    project: CompletedProjectDetail,
    onBackClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onEditClick: () -> Unit,
    isAdmin: Boolean
) {
    val uriHandler = LocalUriHandler.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGrayBlue)
            .systemBarsPadding()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {


            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = TextPrimary
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Project Details",
                        color = TextPrimary,
                        fontSize = 28.sp,
                        fontFamily = FontFamily(Font(R.font.poppinsregular))
                    )
                }


                if (isAdmin) {
                    IconButton(onClick = onDeleteClick) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete Project",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }


            Card(
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .padding(horizontal = 18.dp),
                elevation = CardDefaults.cardElevation(12.dp)
            ) {
                Image(
                    painter = painterResource(id = project.imageResId),
                    contentDescription = project.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))


            project.youtubeUrl?.let { url ->
                Button(
                    onClick = { uriHandler.openUri(url) },
                    colors = ButtonDefaults.buttonColors(containerColor = AccentOrange),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Icon(Icons.Default.PlayArrow, contentDescription = "YouTube", tint = TextPrimary)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Watch Demo",
                        color = TextPrimary,
                        fontFamily = FontFamily(Font(R.font.poppinsregular))
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            Text(
                text = project.name,
                color = TextPrimary,
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.poppinsbold)),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))


            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 80.dp) // leave space for FAB
            ) {
                item { CardSection(Icons.Default.Person, "Developers", project.developers.joinToString(", ")) }
                item { CardSection(Icons.Default.School, "Guided By", project.guidedBy.joinToString(", ")) }
                item { CardSection(Icons.Default.Build, "Equipment Used", project.equipmentUsed.joinToString(", ")) }
                item { CardSection(Icons.Default.Code, "Tech Stack", project.techStack.joinToString(", ")) }
                item { CardSection(Icons.Default.HelpOutline, "Problem Solved", project.problemSolved) }
                item { Spacer(modifier = Modifier.height(16.dp)) }
            }
        }


        if (isAdmin) {
            FloatingActionButton(
                onClick = onEditClick,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                containerColor = AccentBlue,
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Project",
                    tint = Color.White
                )
            }
        }
    }
}


@Composable
fun CardSection(icon: androidx.compose.ui.graphics.vector.ImageVector, title: String, content: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = DarkSlate),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = AccentBlue,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = title,
                    color = TextPrimary,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppinsmedium))
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = content,
                    color = TextSecondary,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppinsregular))
                )
            }
        }
    }
}