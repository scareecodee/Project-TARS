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
import com.example.projecttars.Admin.Resources.AdminResScreen
import com.example.projecttars.R
import com.example.projecttars.ViewModels.Firebase.ResourcesVM
import com.example.projecttars.ViewModels.NavigationData.ResourcesNavVM
import com.example.projecttars.ui.theme.DarkGrayBlue
import com.example.projectz.admin.mainscreen.history.FilterType
import com.example.projectz.admin.mainscreen.history.SearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResourcesScreen(  navController: NavController,
                      resourcesVM: ResourcesVM,
                      onAddClick: () -> Unit,
                      resourcesNavVM: ResourcesNavVM,
                      ) {
    AdminResScreen(
        resourcesVM = resourcesVM,
        onAddClick = onAddClick,
        isAdmin = false,
        onCardClick = {
            resourcesNavVM.selectEquipment(it)
            navController.navigate("EquipmentDetailScreen")
        }
    )
}

