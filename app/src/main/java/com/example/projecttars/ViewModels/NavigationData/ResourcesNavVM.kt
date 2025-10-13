package com.example.projecttars.ViewModels.NavigationData

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import com.example.projecttars.DataModels.TarsLabComponent

import kotlinx.coroutines.flow.StateFlow

class ResourcesNavVM : ViewModel() {
    private val _selectedEquipment = MutableStateFlow<TarsLabComponent?>(null)
    val selectedEquipment: StateFlow<TarsLabComponent?> = _selectedEquipment


    fun selectEquipment(equipment: TarsLabComponent) {
        _selectedEquipment.value = equipment
    }


    fun clearSelection() {
        _selectedEquipment.value = null
    }
}
