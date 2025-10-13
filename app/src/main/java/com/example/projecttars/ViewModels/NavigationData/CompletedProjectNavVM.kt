package com.example.projecttars.ViewModels.NavigationData



import androidx.lifecycle.ViewModel
import com.example.projecttars.DataModels.CompletedProjectDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CompletedProjectNavVM : ViewModel() {
    private val _selectedCompletedProject = MutableStateFlow<CompletedProjectDetail?>(null)
    val selectedCompletedProject: StateFlow<CompletedProjectDetail?> =_selectedCompletedProject


    fun selectCompletedProject(completedProject: CompletedProjectDetail){
        _selectedCompletedProject.value = completedProject
    }


    fun clearSelection() {
        _selectedCompletedProject.value = null
    }
}
