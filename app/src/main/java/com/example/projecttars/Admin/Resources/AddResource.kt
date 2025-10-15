package com.example.projecttars.Admin.Resources

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import coil.compose.AsyncImage
import com.example.projecttars.R
import com.example.projecttars.ViewModels.NavigationData.ResourcesNavVM
import com.example.projecttars.ui.theme.*
import androidx.compose.runtime.collectAsState
import com.example.projecttars.DataModels.TarsLabComponent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEquipment(
    heading: String = "Add Equipment",
    onBackClick: () -> Unit,
    onSaveClick: (TarsLabComponent) -> Unit,
    resourcesNavVM: ResourcesNavVM
) {
    val selectedEquipment by resourcesNavVM.selectedEquipment.collectAsState()

    var id by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var available by remember { mutableStateOf(false) }
    var youtubeUrl by remember { mutableStateOf("") }
    var documentationUrl by remember { mutableStateOf("") }

    LaunchedEffect(selectedEquipment) {
        selectedEquipment?.let { equipment ->
            id = equipment.id
            name = equipment.name
            imageUrl = equipment.imageUrl
            description = equipment.description
            available = equipment.available
            youtubeUrl = equipment.youtubeUrl ?: ""
            documentationUrl = equipment.documentationUrl ?: ""
        }
    }

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val screenWidth = maxWidth.value
        val screenHeight = maxHeight.value

        // Responsive dimensions
        val padding = (screenWidth * 0.04f).dp
        val textFieldSpacing = (screenWidth * 0.02f).dp
        val previewHeight = (screenWidth * 0.45f).dp
        val fabSize = (screenWidth * 0.14f).dp
        val fontSizeHeading = (screenWidth * 0.05f).sp

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            heading,
                            fontSize = fontSizeHeading,
                            color = TextPrimary,
                            fontFamily = FontFamily(Font(R.font.poppinsregular))
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
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkGrayBlue)
                )
            },
            containerColor = DarkGrayBlue,
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        val equipment = TarsLabComponent(
                            id = id,
                            name = name,
                            imageUrl = imageUrl,
                            description = description,
                            available = available,
                            youtubeUrl = youtubeUrl.ifBlank { null },
                            documentationUrl = documentationUrl.ifBlank { null }
                        )
                        onSaveClick(equipment)
                    },
                    containerColor = AccentBlue,
                    modifier = Modifier.size(fabSize)
                ) {
                    Icon(Icons.Default.Check, contentDescription = "Save", tint = TextPrimary)
                }
            }
        ) { innerPadding ->

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = padding),
                verticalArrangement = Arrangement.Top
            ) {
                item {
                    // Name
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Name", fontFamily = FontFamily(Font(R.font.poppinsregular)), color = TextPrimary) },
                        leadingIcon = { Icon(Icons.Default.Description, contentDescription = "Name", tint = TextPrimary) },
                        textStyle = LocalTextStyle.current.copy(color = TextPrimary, fontFamily = FontFamily(Font(R.font.poppinsmedium))),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = DarkSlate,
                            unfocusedContainerColor = DarkSlate,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = AccentBlue,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(textFieldSpacing))
                }

                item {
                    // Image URL
                    OutlinedTextField(
                        value = imageUrl,
                        onValueChange = { imageUrl = it },
                        label = { Text("Image URL", color = TextPrimary, fontFamily = FontFamily(Font(R.font.poppinsregular))) },
                        leadingIcon = { Icon(Icons.Default.Image, contentDescription = "Image URL", tint = TextPrimary) },
                        textStyle = LocalTextStyle.current.copy(color = TextPrimary, fontFamily = FontFamily(Font(R.font.poppinsmedium))),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = DarkSlate,
                            unfocusedContainerColor = DarkSlate,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = AccentBlue,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(textFieldSpacing))
                }

                item {
                    // Preview Image
                    if (imageUrl.isNotBlank()) {
                        Card(
                            shape = RoundedCornerShape(padding),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(previewHeight)
                        ) {
                            AsyncImage(
                                model = imageUrl,
                                contentDescription = "Preview",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = androidx.compose.ui.layout.ContentScale.Crop
                            )
                        }

                        Spacer(modifier = Modifier.height(textFieldSpacing))
                    }
                }

                item {
                    // Description
                    OutlinedTextField(
                        value = description,
                        onValueChange = { description = it },
                        label = { Text("Description", color = TextPrimary, fontFamily = FontFamily(Font(R.font.poppinsregular))) },
                        leadingIcon = { Icon(Icons.Default.Info, contentDescription = "Description", tint = TextPrimary) },
                        maxLines = 4,
                        textStyle = LocalTextStyle.current.copy(color = TextPrimary, fontFamily = FontFamily(Font(R.font.poppinsmedium))),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = DarkSlate,
                            unfocusedContainerColor = DarkSlate,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = AccentBlue,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(textFieldSpacing))
                }

                item {
                    // Availability
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = available,
                            onCheckedChange = { available = it },
                            colors = CheckboxDefaults.colors(checkedColor = AccentBlue, uncheckedColor = TextSecondary)
                        )
                        Spacer(modifier = Modifier.width(textFieldSpacing))
                        Text("Available", color = TextPrimary, fontFamily = FontFamily(Font(R.font.poppinsregular)))
                    }

                    Spacer(modifier = Modifier.height(textFieldSpacing))
                }

                item {
                    // YouTube URL
                    OutlinedTextField(
                        value = youtubeUrl,
                        onValueChange = { youtubeUrl = it },
                        label = { Text("YouTube URL (optional)", color = TextPrimary, fontFamily = FontFamily(Font(R.font.poppinsregular))) },
                        leadingIcon = { Icon(Icons.Default.PlayArrow, contentDescription = "YouTube", tint = TextPrimary) },
                        textStyle = LocalTextStyle.current.copy(color = TextPrimary, fontFamily = FontFamily(Font(R.font.poppinsmedium))),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = DarkSlate,
                            unfocusedContainerColor = DarkSlate,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = AccentBlue,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(textFieldSpacing))
                }

                item {
                    // Documentation URL
                    OutlinedTextField(
                        value = documentationUrl,
                        onValueChange = { documentationUrl = it },
                        label = { Text("Documentation URL (optional)", color = TextPrimary, fontFamily = FontFamily(Font(R.font.poppinsregular))) },
                        leadingIcon = { Icon(Icons.Default.Description, contentDescription = "Documentation", tint = TextPrimary) },
                        textStyle = LocalTextStyle.current.copy(color = TextPrimary, fontFamily = FontFamily(Font(R.font.poppinsmedium))),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = DarkSlate,
                            unfocusedContainerColor = DarkSlate,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = AccentBlue,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(textFieldSpacing))
                }
            }
        }
    }
}
