package com.example.projecttars.Admin.Resources

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.projecttars.DataModels.TarsLabComponent
import com.example.projecttars.R
import com.example.projecttars.Utils.isLink
import com.example.projecttars.ui.theme.*

@Composable
fun AdminResDetailScreen(
    equipment: TarsLabComponent,
    onBackClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onEditClick: () -> Unit,
    isAdmin: Boolean
) {
    var showDeleteDialog by remember { mutableStateOf(false) }
    val uriHandler = LocalUriHandler.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp


    val horizontalPadding = (screenWidth * 0.04).dp
    val verticalPadding = (screenHeight * 0.02).dp
    val topRowSpacing = (screenWidth * 0.02).dp
    val iconSize = (screenWidth * 0.07).dp
    val headingFontSize = (screenWidth * 0.05).sp
    val titleFontSize = (screenWidth * 0.06).sp
    val subtitleFontSize = (screenWidth * 0.04).sp
    val buttonHeight = (screenHeight * 0.07).dp
    val buttonCorner = (screenWidth * 0.03).dp
    val cardHeight = (screenHeight * 0.28).dp
    val fabSize = (screenWidth * 0.14).dp
    val fabPadding = (screenWidth * 0.04).dp
    val lazyColumnPadding = (screenHeight * 0.04).dp
    val textPadding = (screenWidth * 0.03).dp
    val iconTextSpacing = (screenWidth * 0.02).dp

    Box(modifier = Modifier
        .fillMaxSize()
        .background(DarkGrayBlue)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .padding(bottom = lazyColumnPadding)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = horizontalPadding, vertical = verticalPadding),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = AccentBlue,
                            modifier = Modifier.size(iconSize)
                        )
                    }

                    Spacer(modifier = Modifier.width(topRowSpacing))

                    Text(
                        text = "Equipment Details",
                        color = AccentBlue,
                        fontSize = headingFontSize,
                        fontFamily = FontFamily(Font(R.font.poppinsbold))
                    )
                }

                if (isAdmin) {
                    IconButton(onClick = {showDeleteDialog=true}) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete Equipment",
                            tint = Color.Red,
                            modifier = Modifier
                                .clip(RoundedCornerShape(fabSize / 2))
                        )
                    }
                }
            }
            
            Card(
                shape = RoundedCornerShape(buttonCorner),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(cardHeight)
                    .padding(horizontal = horizontalPadding),
                elevation = CardDefaults.cardElevation(12.dp)
            ) {
                AsyncImage(
                    model = equipment.imageUrl,
                    contentDescription = equipment.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(verticalPadding))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = horizontalPadding),
                horizontalArrangement = Arrangement.spacedBy(iconTextSpacing)
            ) {
                equipment.youtubeUrl?.let { url ->
                    if(isLink(url)) {
                        Button(
                            onClick = { uriHandler.openUri(url) },
                            colors = ButtonDefaults.buttonColors(containerColor = AccentOrange),
                            modifier = Modifier.weight(1f).height(buttonHeight),
                            shape = RoundedCornerShape(buttonCorner)
                        ) {
                            Icon(Icons.Default.PlayArrow, contentDescription = "YouTube", tint = TextPrimary)
                            Spacer(modifier = Modifier.width(iconTextSpacing))
                            Text(
                                "Play",
                                color = TextPrimary,
                                fontFamily = FontFamily(Font(R.font.poppinsregular)),
                                fontSize = subtitleFontSize
                            )
                        }
                    }
                }

                equipment.documentationUrl?.let { url ->
                    if(isLink(url)){
                        Button(
                            onClick = { uriHandler.openUri(url) },
                            colors = ButtonDefaults.buttonColors(containerColor = AccentBlue),
                            modifier = Modifier.weight(1f).height(buttonHeight),
                            shape = RoundedCornerShape(buttonCorner)
                        ) {
                            Icon(Icons.Default.Description, contentDescription = "Documentation", tint = TextPrimary)
                            Spacer(modifier = Modifier.width(iconTextSpacing))
                            Text(
                                "Read",
                                color = TextPrimary,
                                fontFamily = FontFamily(Font(R.font.poppinsregular)),
                                fontSize = subtitleFontSize
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(verticalPadding))

            Text(
                text = equipment.name,
                color = TextPrimary,
                fontSize = titleFontSize,
                modifier = Modifier.padding(horizontal = horizontalPadding),
                fontFamily = FontFamily(Font(R.font.poppinsbold))
            )

            Spacer(modifier = Modifier.height(topRowSpacing))

            Text(
                text = if (equipment.available) "Available" else "Not Available",
                color = if (equipment.available) AccentBlue else AccentOrange,
                fontSize = subtitleFontSize,
                modifier = Modifier.padding(horizontal = horizontalPadding),
                fontFamily = FontFamily(Font(R.font.poppinsregular))
            )

            Spacer(modifier = Modifier.height(verticalPadding))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = lazyColumnPadding)
            ) {
                item {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = DarkSlate),
                        shape = RoundedCornerShape(buttonCorner),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = horizontalPadding)
                    ) {
                        Text(
                            text = equipment.description,
                            color = TextSecondary,
                            fontSize = subtitleFontSize,
                            modifier = Modifier.padding(textPadding),
                            fontFamily = FontFamily(Font(R.font.poppinsitalic))
                        )
                    }
                }
            }
        }

        if (isAdmin) {
            IconButton(
                onClick = onEditClick,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(fabPadding)
                    .size(fabSize)
                    .clip(RoundedCornerShape(fabSize / 2))
                    .background(AccentBlue.copy(alpha = 0.2f), RoundedCornerShape(fabSize / 2))
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Equipment",
                    tint = AccentBlue
                )
            }
        }
        if (showDeleteDialog) {
            AlertDialog(
                onDismissRequest = { showDeleteDialog = false },
                title = { Text("Delete ${equipment.name}", color = TextPrimary,fontSize = 17.sp,
                    fontFamily = FontFamily(androidx.compose.ui.text.font.Font(R.font.poppinsmedium))) },
                text = { Text("Are you sure you want to delete ?", color = TextSecondary,       fontSize = 12.sp,
                    fontFamily = FontFamily(androidx.compose.ui.text.font.Font(R.font.poppinsmedium))) },
                confirmButton = {
                    TextButton(
                        onClick = {
                            onDeleteClick()
                            showDeleteDialog = false
                        }
                    ) {
                        Text("Delete", color =Color.White,fontSize = 12.sp,
                            fontFamily = FontFamily(androidx.compose.ui.text.font.Font(R.font.poppinsmedium)))
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDeleteDialog = false }) {
                        Text("Cancel", color = TextPrimary, fontSize = 12.sp,
                            fontFamily = FontFamily(androidx.compose.ui.text.font.Font(R.font.poppinsmedium)))
                    }
                },
                containerColor = DarkGrayBlue
            )
        }
    }
}
