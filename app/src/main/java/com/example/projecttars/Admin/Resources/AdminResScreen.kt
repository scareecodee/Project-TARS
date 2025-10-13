package com.example.projecttars.Admin.Resources
import androidx.compose.foundation.background
import com.example.projecttars.DataModels.TarsLabComponent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projecttars.Admin.Components.AdminResCard
import com.example.projecttars.R
import com.example.projecttars.ViewModels.Firebase.ResourcesVM
import com.example.projecttars.ViewModels.NavigationData.ResourcesNavVM
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
            .padding(horizontal = 16.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Resources",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontFamily = FontFamily(Font(R.font.poppinsregular)),
                    modifier = Modifier.padding(vertical = 16.dp)
                )

                if(isAdmin){
                    androidx.compose.material3.IconButton(
                        onClick = onAddClick,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    ) {
                        androidx.compose.material3.Icon(
                            imageVector = androidx.compose.material.icons.Icons.Default.Add,
                            contentDescription = "Add Equipment",
                            tint = Color.White
                        )
                    }
                }

            }

            Spacer(modifier = Modifier.height(8.dp))

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
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.poppinsregular))
                    )
                }
            } else {
                Spacer(modifier = Modifier.height(16.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(bottom = 80.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(filteredComponents) { equipment ->
                        AdminResCard(
                            imageUrl = equipment.imageUrl,
                            componentName = equipment.name,
                            isAvailable = equipment.available,
                            onClick = {
                               onCardClick(equipment)
                            }
                        )
                    }
                }
            }

        }
    }
}
