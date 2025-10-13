package com.example.projecttars.ViewModels.NavigationData




import androidx.lifecycle.ViewModel
import com.example.projecttars.DataModels.OngoingProjectDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class OngoingProjectNavVM: ViewModel() {
    private val _selectedOngoingProject = MutableStateFlow<OngoingProjectDetail?>(null)
    val selectedOngoingProject: StateFlow<OngoingProjectDetail?> =_selectedOngoingProject


    fun selectOngoingProject(ongoingProject: OngoingProjectDetail){
        _selectedOngoingProject.value = ongoingProject
    }


    fun clearSelection() {
        _selectedOngoingProject.value = null
    }
}
