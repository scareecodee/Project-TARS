package com.example.projecttars.Admin.SocialMedia

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projecttars.R
import com.example.projecttars.DataModels.SocialMediaLinks
import com.example.projecttars.ViewModels.Firebase.TarsSocialViewModel
import com.example.projecttars.ui.theme.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditSocialMedia(
    heading: String = "Edit Social Media",
    onBackClick: () -> Unit,
    onSaveClick: (SocialMediaLinks) -> Unit,
    socialViewModel: TarsSocialViewModel
) {
    val scrollState = rememberScrollState()
    val socialLinks by socialViewModel.socialLinks.collectAsState()

    var instagramUrl by remember { mutableStateOf("") }
    var youtubeUrl by remember { mutableStateOf("") }
    var linkedinUrl by remember { mutableStateOf("") }
    var mailUrl by remember { mutableStateOf("") }


    LaunchedEffect(socialLinks) {
        socialLinks.let { it ->
           instagramUrl= it.instagram
            youtubeUrl = it.youtube
            linkedinUrl = it.linkedin
            mailUrl = it.mail
        }
    }

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val screenWidth = maxWidth.value
        val screenHeight = maxHeight.value


        val horizontalPadding = (screenWidth * 0.04f).dp
        val verticalSpacing = (screenHeight * 0.015f).dp
        val imageHeight = (screenHeight * 0.25f).dp
        val fabSize = (screenWidth * 0.15f).dp
        val headingFontSize = (screenWidth * 0.05f).sp

        val poppins = FontFamily(Font(R.font.poppinsregular))

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            heading,
                            color = TextPrimary,
                            fontFamily = poppins,
                            fontSize = headingFontSize
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
                            SocialMediaLinks(
                                instagram = instagramUrl,
                                youtube = youtubeUrl,
                                linkedin = linkedinUrl,
                                mail = mailUrl
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
                    .padding(horizontal = horizontalPadding, vertical = verticalSpacing)
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.spacedBy(verticalSpacing)
            ) {

                SocialMediaTextField("Instagram", Icons.Default.CameraAlt, value =instagramUrl, onValueChange = { instagramUrl = it })
                SocialMediaTextField("Youtube", Icons.Default.PlayArrow, value = youtubeUrl, onValueChange = { youtubeUrl = it })
                SocialMediaTextField("Linkedin", Icons.Default.Link, value = linkedinUrl, onValueChange = {linkedinUrl= it }, )
                SocialMediaTextField("Mail", Icons.Default.Mail, value = mailUrl, onValueChange = { mailUrl = it },)
            }
        }
    }
}

@Composable
fun SocialMediaTextField(
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    value: String,
    onValueChange: (String) -> Unit,

    ) {
    val poppins = FontFamily(Font(R.font.poppinsregular))
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, fontFamily = poppins, color = TextSecondary) },
        leadingIcon = { Icon(icon, contentDescription = label, tint = TextPrimary) },
        modifier = Modifier
            .fillMaxWidth(),
        textStyle = LocalTextStyle.current.copy(
            color = TextPrimary,
            fontFamily = FontFamily(Font(R.font.poppinsmedium))
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = DarkSlate,
            unfocusedContainerColor = DarkSlate,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = AccentBlue,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White
        )
    )
}
