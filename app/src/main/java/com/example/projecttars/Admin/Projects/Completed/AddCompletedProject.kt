package com.example.projecttars.Admin.Resources


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import coil.compose.AsyncImage
import com.example.projecttars.DataModels.CompletedProjectDetail
import com.example.projecttars.ui.theme.*
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.layout.ContentScale
import com.example.projecttars.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCompletedProjectScreen(
    onBackClick: () -> Unit,
    onSaveClick: (CompletedProjectDetail) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }
    var developers by remember { mutableStateOf("") } // comma separated
    var guidedBy by remember { mutableStateOf("") } // comma separated
    var equipmentUsed by remember { mutableStateOf("") } // comma separated
    var techStack by remember { mutableStateOf("") } // comma separated
    var problemSolved by remember { mutableStateOf("") }
    var youtubeUrl by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()
    val poppinsFont = FontFamily(Font(R.font.poppinsregular))

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Add Completed Project",
                        fontSize = 20.sp,
                        color = TextPrimary,
                        fontFamily = poppinsFont
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
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
                    val project = CompletedProjectDetail(
                        name = name,
                        imageUrl = imageUrl,
                        developers = developers.split(",").map { it.trim() }.filter { it.isNotEmpty() },
                        guidedBy = guidedBy.split(",").map { it.trim() }.filter { it.isNotEmpty() },
                        equipmentUsed = equipmentUsed.split(",").map { it.trim() }.filter { it.isNotEmpty() },
                        techStack = techStack.split(",").map { it.trim() }.filter { it.isNotEmpty() },
                        problemSolved = problemSolved,
                        youtubeUrl = youtubeUrl.ifBlank { null }
                    )
                    onSaveClick(project)
                },
                containerColor = AccentBlue
            ) {
                Icon(Icons.Default.Check, contentDescription = "Save", tint = TextPrimary)
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            // Project Name
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Project Name", color = TextPrimary, fontFamily = poppinsFont) },
                leadingIcon = { Icon(Icons.Default.Description, contentDescription = "Name", tint = TextPrimary) },
                textStyle = LocalTextStyle.current.copy(
                    color = TextPrimary,
                    fontFamily = FontFamily(Font(R.font.poppinsmedium))
                ),
                colors = textFieldColors(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Image URL
            OutlinedTextField(
                value = imageUrl,
                onValueChange = { imageUrl = it },
                label = { Text("Image URL", color = TextPrimary, fontFamily = poppinsFont) },
                leadingIcon = { Icon(Icons.Default.Image, contentDescription = "Image URL", tint = TextPrimary) },
                textStyle = LocalTextStyle.current.copy(
                    color = TextPrimary,
                    fontFamily = FontFamily(Font(R.font.poppinsmedium))
                ),
                colors = textFieldColors(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Preview Image
            if (imageUrl.isNotBlank()) {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                ) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = "Preview",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))
            }

            // Developers
            OutlinedTextField(
                value = developers,
                onValueChange = { developers = it },
                label = { Text("Developers (comma separated)", color = TextPrimary, fontFamily = poppinsFont) },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Developers", tint = TextPrimary) },
                textStyle = LocalTextStyle.current.copy(
                    color = TextPrimary,
                    fontFamily = FontFamily(Font(R.font.poppinsmedium))
                ),
                colors = textFieldColors(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Guided By
            OutlinedTextField(
                value = guidedBy,
                onValueChange = { guidedBy = it },
                label = { Text("Guided By (comma separated)", color = TextPrimary, fontFamily = poppinsFont) },
                leadingIcon = { Icon(Icons.Default.PersonSearch, contentDescription = "Guided By", tint = TextPrimary) },
                textStyle = LocalTextStyle.current.copy(
                    color = TextPrimary,
                    fontFamily = FontFamily(Font(R.font.poppinsmedium))
                ),
                colors = textFieldColors(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Equipment Used
            OutlinedTextField(
                value = equipmentUsed,
                onValueChange = { equipmentUsed = it },
                label = { Text("Equipment Used (comma separated)", color = TextPrimary, fontFamily = poppinsFont) },
                leadingIcon = { Icon(Icons.Default.Settings, contentDescription = "Equipment", tint = TextPrimary) },
                textStyle = LocalTextStyle.current.copy(
                    color = TextPrimary,
                    fontFamily = FontFamily(Font(R.font.poppinsmedium))
                ),
                colors = textFieldColors(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Tech Stack
            OutlinedTextField(
                value = techStack,
                onValueChange = { techStack = it },
                label = { Text("Tech Stack (comma separated)", color = TextPrimary, fontFamily = poppinsFont) },
                leadingIcon = { Icon(Icons.Default.Code, contentDescription = "Tech Stack", tint = TextPrimary) },
                textStyle = LocalTextStyle.current.copy(
                    color = TextPrimary,
                    fontFamily = FontFamily(Font(R.font.poppinsmedium))
                ),
                colors = textFieldColors(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Problem Solved
            OutlinedTextField(
                value = problemSolved,
                onValueChange = { problemSolved = it },
                label = { Text("Problem Solved", color = TextPrimary, fontFamily = poppinsFont) },
                leadingIcon = { Icon(Icons.Default.Lightbulb, contentDescription = "Problem Solved", tint = TextPrimary) },
                maxLines = 4,
                textStyle = LocalTextStyle.current.copy(
                    color = TextPrimary,
                    fontFamily = FontFamily(Font(R.font.poppinsmedium))
                ),
                colors = textFieldColors(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // YouTube URL
            OutlinedTextField(
                value = youtubeUrl,
                onValueChange = { youtubeUrl = it },
                label = { Text("YouTube URL (optional)", color = TextPrimary, fontFamily = poppinsFont) },
                leadingIcon = { Icon(Icons.Default.PlayArrow, contentDescription = "YouTube", tint = TextPrimary) },
                textStyle = LocalTextStyle.current.copy(
                    color = TextPrimary,
                    fontFamily = FontFamily(Font(R.font.poppinsmedium))
                ),
                colors = textFieldColors(),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun textFieldColors() = TextFieldDefaults.colors(
    focusedContainerColor = DarkSlate,
    unfocusedContainerColor = DarkSlate,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    cursorColor = AccentBlue,
    focusedTextColor = Color.White,
    unfocusedTextColor = Color.White
)
