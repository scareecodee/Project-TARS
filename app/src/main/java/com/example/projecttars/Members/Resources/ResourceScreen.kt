package com.example.projecttars.Members.Resources

import androidx.compose.foundation.background
import com.example.projecttars.DataModels.TarsLabComponent
import com.example.projecttars.Members.UiElements.TarsLabComponentCard
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projecttars.R
import com.example.projecttars.ui.theme.DarkGrayBlue
import com.example.projectz.admin.mainscreen.history.FilterType
import com.example.projectz.admin.mainscreen.history.SearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResourcesScreen(components: List<TarsLabComponent>,navController: NavController) {
    var selectedFilter by remember { mutableStateOf(FilterType.NAME) }
    var query by remember { mutableStateOf("") }

    val filteredComponents = remember(query, selectedFilter, components) {
        components.filter { component ->
            when (selectedFilter) {
                FilterType.NAME -> component.name.contains(query, ignoreCase = true)
                FilterType.AVAILABLE -> component.isAvailable &&  component.name.contains(query, ignoreCase = true)
                FilterType.NOT_AVAILABLE -> (!component.isAvailable) &&  component.name.contains(query, ignoreCase = true)
            }
        }
    }

    Column(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize()
            .background(DarkGrayBlue)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Resources",
            color = Color.White,
            fontSize = 30.sp,
            fontFamily = FontFamily(Font(R.font.poppinsregular)),
            modifier = Modifier.padding(vertical = 16.dp)
        )

        SearchBar(
            query = query,
            onQueryChange = { query = it },
            selectedFilter = selectedFilter,
            onFilterSelected = { selectedFilter = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(modifier = Modifier.fillMaxSize()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(bottom = 80.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(filteredComponents) { component ->
                    TarsLabComponentCard(
                        imageResId = component.imageResId,
                        componentName = component.name,
                        isAvailable = component.isAvailable,
                        onClick = {
                            navController.navigate("EquipmentDetailScreen")
                        }
                    )
                }
            }
        }
    }
}

