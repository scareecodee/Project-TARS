package com.example.projecttars.Members.Projects.Ongoing
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.example.projecttars.DataModels.OngoingProjectDetail
import com.example.projecttars.R
import com.example.projecttars.ui.theme.*

@Composable
fun OngoingProjectDetailScreen(
    project: OngoingProjectDetail,
    onBackClick: () -> Unit,
    isAdmin: Boolean = false,
    onEditClick: (() -> Unit)? = {},
    onDeleteClick: (() -> Unit)? = null
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
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = TextPrimary)
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Project Details",
                        color = TextPrimary,
                        fontSize = 28.sp,
                        fontFamily = FontFamily(Font(R.font.poppinsregular))
                    )
                }


                if (isAdmin && onDeleteClick != null) {
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
                    .height(220.dp)
                    .padding(horizontal = 16.dp),
                elevation = CardDefaults.cardElevation(12.dp)
            ) {
               AsyncImage(
                   model = project.imageUrl,
                   contentDescription = "Project Image",
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


            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {
                item {
                    Text(
                        text = project.name,
                        color = TextPrimary,
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(R.font.poppinsbold)),
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }

                item { CardSection(Icons.Default.Person, "Developers", project.developers.joinToString(", ")) }
                item { CardSection(Icons.Default.School, "Guided By", project.guidedBy.joinToString(", ")) }
                item { CardSection(Icons.Default.Build, "Equipment Used", project.equipmentUsed.joinToString(", ")) }
                item { CardSection(Icons.Default.Code, "Tech Stack", project.techStack.joinToString(", ")) }
                item { CardSection(Icons.Default.HelpOutline, "Problem Solved", project.problemSolved) }

                item { Spacer(modifier = Modifier.height(16.dp)) }
            }
        }


        if (isAdmin) {
            if (onEditClick != null) {
                FloatingActionButton(
                    onClick = onEditClick,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp),
                    containerColor = AccentBlue,
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit Project", tint = Color.White)
                }
            }
        }
    }
}


@Composable
fun CardSection(
    icon: ImageVector,
    title: String,
    content: String,
    textColor: Color = TextPrimary,
    cardColor: Color = DarkSlate
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = cardColor),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = title, tint = AccentPurple, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = title, fontFamily = FontFamily(Font(R.font.poppinsmedium)), color = textColor, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = content, fontFamily = FontFamily(Font(R.font.poppinsregular)), color = TextSecondary, fontSize = 14.sp)
            }
        }
    }
}
