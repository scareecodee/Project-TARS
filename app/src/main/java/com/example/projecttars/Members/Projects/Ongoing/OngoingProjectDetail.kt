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
import com.example.projecttars.DataModels.OngoingProjectDetail
import com.example.projecttars.R
import com.example.projecttars.ui.theme.*

@Composable
fun OngoingProjectDetailScreen(
    project: OngoingProjectDetail, // Use same data model
    onBackClick: () -> Unit
) {
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGrayBlue)
            .systemBarsPadding()
    ) {
        // ----- Fixed Top Section -----
        Column(modifier = Modifier.fillMaxWidth()) {
            // Top Row: Back button + Heading
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            ) {
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

            // Project Image
            Card(
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .padding(horizontal = 16.dp),
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

            // YouTube Button
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
        }

        // ----- Scrollable Details Section -----
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 16.dp)
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

            item { CardSection(Icons.Default.Person, "Developers", project.developers.joinToString(", "), TextPrimary, DarkSlate) }
            item { CardSection(Icons.Default.School, "Guided By", project.guidedBy.joinToString(", "), TextPrimary, DarkSlate) }
            item { CardSection(Icons.Default.Build, "Equipment Used", project.equipmentUsed.joinToString(", "), TextPrimary, DarkSlate) }
            item { CardSection(Icons.Default.Code, "Tech Stack", project.techStack.joinToString(", "), TextPrimary, DarkSlate) }
            item { CardSection(Icons.Default.HelpOutline, "Problem Solved", project.problemSolved, TextPrimary, DarkSlate) }

            item { Spacer(modifier = Modifier.height(16.dp)) }
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
