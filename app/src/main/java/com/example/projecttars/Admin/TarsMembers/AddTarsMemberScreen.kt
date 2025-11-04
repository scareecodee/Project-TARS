package com.example.projecttars.Admin.Members

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import coil.compose.AsyncImage
import com.example.projecttars.R
import com.example.projecttars.DataModels.MemberDetail
import com.example.projecttars.ViewModels.NavigationData.MemberNavVM
import com.example.projecttars.ui.theme.*
import kotlin.text.replaceFirstChar
import kotlin.text.uppercase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTarsMemberScreen(
    heading: String = "Add TARS Member",
    onBackClick: () -> Unit,
    onSaveClick: (MemberDetail) -> Unit,
    memberNavVM: MemberNavVM
) {
    val scrollState = rememberScrollState()
    val selectedMember by memberNavVM.selectedMember.collectAsState()

    var name by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }
    var domain by remember { mutableStateOf("") }
    var memberId by remember { mutableStateOf("") }
    var branch by remember { mutableStateOf("") }
    var designation by remember { mutableStateOf("") }
    var projects by remember { mutableStateOf("") }
    var linkedinUrl by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }

    LaunchedEffect(selectedMember) {
        selectedMember?.let { it ->
            name = it.name
            imageUrl = it.imageUrl
            domain = it.domain
            memberId = it.id.replaceFirstChar { it.lowercase() }
            branch = it.branch
            designation = it.designation
            projects = it.projects.joinToString(", ")
            gender = it.gender
            linkedinUrl = it.linkedinUrl ?: ""
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
                            MemberDetail(
                                name = name,
                                imageUrl = imageUrl,
                                domain = domain,
                                id = memberId.replaceFirstChar { it.lowercase() },
                                branch = branch,
                                designation = designation,
                                projects = projects.split(",").map { it.trim() },
                                linkedinUrl = linkedinUrl.ifBlank { null },
                                gender = gender
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
                // Pass value and onValueChange explicitly
                MemberTextField("Name", Icons.Default.Person, value = name, onValueChange = { name = it })
                MemberTextField("Image URL", Icons.Default.Image, value = imageUrl, onValueChange = { imageUrl = it })

                if (imageUrl.isNotBlank()) {
                    Card(
                        shape = RoundedCornerShape(horizontalPadding),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(imageHeight)
                    ) {
                        AsyncImage(
                            model = imageUrl,
                            contentDescription = "Preview",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = androidx.compose.ui.layout.ContentScale.Crop
                        )
                    }
                }

                MemberTextField("Domain", Icons.Default.Work, value = domain, onValueChange = { domain = it }, )
                MemberTextField("Member ID", Icons.Default.Badge, value = memberId, onValueChange = { memberId = it },)
                MemberTextField("Branch", Icons.Default.School, value = branch, onValueChange = { branch = it })
                MemberTextField("Designation", Icons.Default.Star, value = designation, onValueChange = { designation = it })
                MemberTextField("Projects (comma separated)", Icons.Default.Build, value = projects, onValueChange = { projects = it })
                MemberTextField("LinkedIn URL", Icons.Default.Link, value = linkedinUrl, onValueChange = { linkedinUrl = it })
                MemberTextField("Gender", Icons.Default.Boy, value = gender, onValueChange = { gender = it })
            }
        }
    }
}

@Composable
fun MemberTextField(
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
