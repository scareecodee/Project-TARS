package com.example.projecttars.Admin.Resources

import androidx.compose.runtime.Composable
import com.example.projecttars.DataModels.TarsLabComponent
import com.example.projecttars.ViewModels.NavigationData.ResourcesNavVM

@Composable
fun EditResource(
    onBackClick: () -> Unit,
    onSaveClick: (TarsLabComponent) -> Unit,
    resourcesNavVM: ResourcesNavVM
){
    AddEquipment(
        heading = "Edit Resource",
        onBackClick = onBackClick,
        onSaveClick = onSaveClick,
        resourcesNavVM =resourcesNavVM
    )
}