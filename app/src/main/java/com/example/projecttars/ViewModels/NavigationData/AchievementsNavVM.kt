package com.example.projecttars.ViewModels.NavigationData



import androidx.lifecycle.ViewModel
import com.example.projecttars.DataModels.Achievement
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AchievementsNavVM : ViewModel() {
    private val _selectedAchievement = MutableStateFlow<Achievement?>(null)
    val selectedAchievement: StateFlow<Achievement?> =_selectedAchievement


    fun selectAchievement(achievement: Achievement){
        _selectedAchievement.value = achievement
    }


    fun clearSelection() {
        _selectedAchievement.value = null
    }
}
