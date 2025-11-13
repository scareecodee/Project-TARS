package com.example.projecttars.Admin.Resources

import AdminResCard
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import com.example.projecttars.DataModels.TarsLabComponent
import com.example.projecttars.R
import com.example.projecttars.ViewModels.Firebase.ResourcesVM
import com.example.projecttars.ui.theme.DarkGrayBlue
import com.example.projectz.admin.mainscreen.history.FilterType
import com.example.projectz.admin.mainscreen.history.SearchBar

@Composable
fun AdminResScreen(
    resourcesVM: ResourcesVM,
    onAddClick: () -> Unit,
    isAdmin: Boolean,
    onCardClick: (TarsLabComponent) -> Unit = {}
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp


    val horizontalPadding = (screenWidth * 0.04).dp
    val verticalPadding = (screenHeight * 0.025).dp
    val headingFontSize = (screenWidth * 0.07).sp
    val spacerSmall = (screenHeight * 0.015).dp
    val gridSpacing = (screenWidth * 0.04).dp
    val bottomPadding = (screenHeight * 0.11).dp

    val equipments by resourcesVM.equipments.collectAsState()

    LaunchedEffect(Unit) {
        resourcesVM.observeEquipments()
    }

    var selectedFilter by remember { mutableStateOf(FilterType.NAME) }
    var query by remember { mutableStateOf("") }

    val filteredComponents = remember(query, selectedFilter, equipments) {
        equipments.filter { equipment ->
            when (selectedFilter) {
                FilterType.NAME -> equipment.name.contains(query, ignoreCase = true)
                FilterType.AVAILABLE -> equipment.available && equipment.name.contains(query, ignoreCase = true)
                FilterType.NOT_AVAILABLE -> (!equipment.available) && equipment.name.contains(query, ignoreCase = true)
            }
        }
    }

    Box(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize()
            .background(DarkGrayBlue)
            .padding(horizontal = horizontalPadding)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Resources",
                    color = Color.White,
                    fontSize = headingFontSize,
                    fontFamily = FontFamily(Font(R.font.poppinsregular)),
                    modifier = Modifier.padding(vertical = verticalPadding)
                )

                if (isAdmin) {
                    IconButton(
                        onClick = onAddClick,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    ) {
                        Icon(
                            imageVector = androidx.compose.material.icons.Icons.Default.Add,
                            contentDescription = "Add Equipment",
                            tint = Color.White
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(spacerSmall))

            SearchBar(
                query = query,
                onQueryChange = { query = it },
                selectedFilter = selectedFilter,
                onFilterSelected = { selectedFilter = it }
            )

            if (filteredComponents.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No Components Found",
                        color = Color.White,
                        fontSize = (screenWidth * 0.05).sp,
                        fontFamily = FontFamily(Font(R.font.poppinsregular))
                    )
                }
            } else {
                Spacer(modifier = Modifier.height(spacerSmall))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(bottom = bottomPadding),
                    verticalArrangement = Arrangement.spacedBy(gridSpacing),
                    horizontalArrangement = Arrangement.spacedBy(gridSpacing),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(filteredComponents) { equipment ->
                        AdminResCard(
                            imageUrl = equipment.imageUrl,
                            componentName = equipment.name,
                            isAvailable = equipment.available,
                            onClick = { onCardClick(equipment) }
                        )
                    }
                }
            }
        }
    }
}
