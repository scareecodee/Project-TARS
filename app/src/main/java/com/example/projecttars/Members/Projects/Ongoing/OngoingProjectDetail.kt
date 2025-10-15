package com.example.projecttars.Members.Projects.Ongoing

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.projecttars.DataModels.OngoingProjectDetail
import com.example.projecttars.Members.Projects.Completed.CardSection
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

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGrayBlue)
            .systemBarsPadding()
    ) {
        val screenWidth = maxWidth.value
        val screenHeight = maxHeight.value

        // Dynamic sizes
        val cardCornerRadius = (screenWidth * 0.057f).dp
        val spacerHeight = (screenHeight * 0.015f).dp
        val fabSize = (screenWidth * 0.14f).dp
        val horizontalPadding = (screenWidth * 0.04f).dp
        val verticalPadding = (screenHeight * 0.02f).dp
        val cardHeight = (screenHeight * 0.25f).dp
        val iconSize = (screenWidth * 0.07f).dp
        val headingFontSize = (screenWidth * 0.07f).sp
        val spacerHeight1 = (screenHeight * 0.02f).dp
        val cardSectionCorner = (screenWidth * 0.04f).dp
        val cardSectionElevation = (screenWidth * 0.01f).dp
        val cardSectionIconSize = (screenWidth * 0.07f).dp
        val cardSectionTitleFont = (screenWidth * 0.045f).sp
        val cardSectionContentFont = (screenWidth * 0.035f).sp

        Column(modifier = Modifier.fillMaxSize()) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = horizontalPadding, vertical = verticalPadding)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = TextPrimary,
                            modifier = Modifier.size(iconSize)
                        )
                    }

                    Spacer(modifier = Modifier.width(horizontalPadding / 2))

                    Text(
                        text = "Project Details",
                        color = TextPrimary,
                        fontSize = headingFontSize,
                        fontFamily = FontFamily(Font(R.font.poppinsregular))
                    )
                }

                if (isAdmin && onDeleteClick != null) {
                    IconButton(onClick = onDeleteClick) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete Project",
                            tint = MaterialTheme.colorScheme.error,
                            modifier = Modifier.size(iconSize)
                        )
                    }
                }
            }

            Card(
                shape = RoundedCornerShape(cardCornerRadius),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(cardHeight)
                    .padding(horizontal = horizontalPadding),
                elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
            ) {
                AsyncImage(
                    model = project.imageUrl,
                    contentDescription = "Project Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(spacerHeight))

            project.youtubeUrl?.let { url ->
                Button(
                    onClick = { uriHandler.openUri(url) },
                    colors = ButtonDefaults.buttonColors(containerColor = AccentOrange),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = horizontalPadding),
                    shape = RoundedCornerShape(cardCornerRadius)
                ) {
                    Icon(
                        Icons.Default.PlayArrow,
                        contentDescription = "YouTube",
                        tint = TextPrimary
                    )
                    Spacer(modifier = Modifier.width(horizontalPadding / 2))
                    Text(
                        text = "Watch Demo",
                        color = TextPrimary,
                        fontFamily = FontFamily(Font(R.font.poppinsregular))
                    )
                }
            }

            Spacer(modifier = Modifier.height(spacerHeight))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = fabSize)
            ) {
                item {
                    Text(
                        text = project.name,
                        color = TextPrimary,
                        fontSize = headingFontSize,
                        fontFamily = FontFamily(Font(R.font.poppinsbold)),
                        modifier = Modifier.padding(
                            horizontal = horizontalPadding,
                            vertical = verticalPadding
                        )
                    )
                }

                item {
                    CardSection(
                        icon = Icons.Default.Person,
                        title = "Developers",
                        content = project.developers.joinToString(", "),
                        cornerRadius = cardSectionCorner,
                        elevation = cardSectionElevation,
                        iconSize = cardSectionIconSize,
                        titleFontSize = cardSectionTitleFont,
                        contentFontSize = cardSectionContentFont
                    )
                }
                item {
                    CardSection(
                        icon = Icons.Default.School,
                        title = "Guided By",
                        content = project.guidedBy.joinToString(", "),
                        cornerRadius = cardSectionCorner,
                        elevation = cardSectionElevation,
                        iconSize = cardSectionIconSize,
                        titleFontSize = cardSectionTitleFont,
                        contentFontSize = cardSectionContentFont
                    )
                }
                item {
                    CardSection(
                        icon = Icons.Default.Build,
                        title = "Equipment Used",
                        content = project.equipmentUsed.joinToString(", "),
                        cornerRadius = cardSectionCorner,
                        elevation = cardSectionElevation,
                        iconSize = cardSectionIconSize,
                        titleFontSize = cardSectionTitleFont,
                        contentFontSize = cardSectionContentFont
                    )
                }
                item {
                    CardSection(
                        icon = Icons.Default.Code,
                        title = "Tech Stack",
                        content = project.techStack.joinToString(", "),
                        cornerRadius = cardSectionCorner,
                        elevation = cardSectionElevation,
                        iconSize = cardSectionIconSize,
                        titleFontSize = cardSectionTitleFont,
                        contentFontSize = cardSectionContentFont
                    )
                }
                item {
                    CardSection(
                        icon = Icons.Default.HelpOutline,
                        title = "Problem Solved",
                        content = project.problemSolved,
                        cornerRadius = cardSectionCorner,
                        elevation = cardSectionElevation,
                        iconSize = cardSectionIconSize,
                        titleFontSize = cardSectionTitleFont,
                        contentFontSize = cardSectionContentFont
                    )
                }
                item { Spacer(modifier = Modifier.height(spacerHeight1)) }
            }

        }

        if (isAdmin) {
            if (onEditClick != null) {
                FloatingActionButton(
                    onClick = onEditClick,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(horizontalPadding, verticalPadding),
                    containerColor = AccentBlue,
                    shape = RoundedCornerShape(cardCornerRadius/1.2f),
                    elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 8.dp)
                ) {
                    Icon(
                        Icons.Default.Edit,
                        contentDescription = "Edit Project",
                        tint = Color.White,
                        modifier = Modifier.size(iconSize)
                    )
                }
            }
        }
    }
}