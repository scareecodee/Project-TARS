package com.example.projecttars.ViewModels.NavigationData



import androidx.lifecycle.ViewModel
import com.example.projecttars.DataModels.MemberDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MemberNavVM : ViewModel() {
    private val _selectedMember = MutableStateFlow<MemberDetail?>(null)
    val selectedMember: StateFlow<MemberDetail?> = _selectedMember


    fun selectMember(member: MemberDetail){
        _selectedMember.value = member
    }


    fun clearSelection() {
        _selectedMember.value = null
    }
}
