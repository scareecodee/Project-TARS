package com.example.projecttars.Admin.Achievements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.projecttars.DataModels.Achievement
import com.example.projecttars.R
import com.example.projecttars.ViewModels.NavigationData.AchievementsNavVM
import com.example.projecttars.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAchievementScreen(
    heading: String,
    onBackClick: () -> Unit,
    onSaveClick: (Achievement) -> Unit,
    achievementsNavVM: AchievementsNavVM
) {
    val selectedAchievement = achievementsNavVM.selectedAchievement.collectAsState().value
    val poppins = FontFamily(Font(R.font.poppinsregular))
    val scrollState = rememberScrollState()

    var imageUrl by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }
    var shortDescription by remember { mutableStateOf("") }

    LaunchedEffect(selectedAchievement) {
        selectedAchievement?.let {
            imageUrl = it.imageUrl
            title = it.title
            shortDescription = it.shortDescription
        }
    }

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val screenWidth = maxWidth.value
        val screenHeight = maxHeight.value

        // Responsive sizes
        val horizontalPadding = (screenWidth * 0.04f).dp
        val verticalPadding = (screenHeight * 0.02f).dp
        val cardHeight = (screenHeight * 0.25f).dp
        val spacerHeight = (screenHeight * 0.015f).dp
        val fontSizeHeading = (screenWidth * 0.05f).sp
        val fabSize = (screenWidth * 0.14f).dp

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            heading,
                            color = TextPrimary,
                            fontFamily = poppins,
                            fontSize = fontSizeHeading
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(
                                Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = TextPrimary
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkSlate)
                )
            },
            containerColor = DarkGrayBlue,
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        onSaveClick(
                            Achievement(
                                imageUrl = imageUrl,
                                title = title,
                                shortDescription = shortDescription
                            )
                        )
                    },
                    containerColor = AccentBlue,
                    modifier = Modifier.size(fabSize)
                ) {
                    Icon(Icons.Default.Check, contentDescription = "Save", tint = TextPrimary)
                }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = horizontalPadding, vertical = verticalPadding)
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.spacedBy(spacerHeight)
            ) {
                AddAchievementTextField("Image URL", Icons.Default.Image, imageUrl) { imageUrl = it }

                if (imageUrl.isNotBlank()) {
                    Card(
                        shape = RoundedCornerShape((screenWidth * 0.04f).dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(cardHeight)
                    ) {
                        AsyncImage(
                            model = imageUrl,
                            contentDescription = "Preview",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                AddAchievementTextField("Title", Icons.Default.Star, title) { title = it }
                AddAchievementTextField("Short Description", Icons.Default.Description, shortDescription) { shortDescription = it }
            }
        }
    }
}

@Composable
fun AddAchievementTextField(
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    value: String,
    onValueChange: (String) -> Unit
) {
    BoxWithConstraints {
        val screenWidth = maxWidth.value
        val fontSizeText = (screenWidth * 0.045f).sp
        val poppins = FontFamily(Font(R.font.poppinsregular))

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label, fontFamily = poppins, color = TextSecondary, fontSize = fontSizeText) },
            leadingIcon = { Icon(icon, contentDescription = label, tint = TextPrimary) },
            modifier = Modifier.fillMaxWidth(),
            textStyle = LocalTextStyle.current.copy(
                color = TextPrimary,
                fontFamily = FontFamily(Font(R.font.poppinsmedium)),
                fontSize = fontSizeText
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = DarkSlate,
                unfocusedContainerColor = DarkSlate,
                focusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
                unfocusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
                cursorColor = AccentBlue,
                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary
            )
        )
    }
}
