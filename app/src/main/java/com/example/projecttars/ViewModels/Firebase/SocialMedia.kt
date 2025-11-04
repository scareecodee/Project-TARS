package com.example.projecttars.ViewModels.Firebase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projecttars.DataModels.SocialMediaLinks
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class TarsSocialViewModel : ViewModel() {

    private val dbRef = FirebaseDatabase.getInstance().getReference("SocialMedia")

    private val _socialLinks = MutableStateFlow(SocialMediaLinks())
    val socialLinks: StateFlow<SocialMediaLinks> = _socialLinks

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading


    fun readSocialLinks(onResult: (Boolean) -> Unit ) {
        _isLoading.value = true
        dbRef.get()
            .addOnSuccessListener { snapshot ->
                _isLoading.value = false
                if (snapshot.exists()) {
                    val links = snapshot.getValue(SocialMediaLinks::class.java)
                    if (links != null) {
                        _socialLinks.value = links
                    }
                    onResult(true)
                } else {
                    onResult(false)
                }
            }
            .addOnFailureListener { e ->
                _isLoading.value = false
                onResult(false)
            }
    }


    fun updateAllSocialLinks(
        instagram: String,
        youtube: String,
        linkedin: String,
        mail: String,
        onResult: (Boolean) -> Unit
    ) {
        _isLoading.value = true
        viewModelScope.launch {
            val updatedLinks = SocialMediaLinks(instagram, youtube, linkedin, mail)
            dbRef.setValue(updatedLinks)
                .addOnSuccessListener {
                    _isLoading.value = false
                    _socialLinks.value = updatedLinks
                    onResult(true)
                }
                .addOnFailureListener { e ->
                    _isLoading.value = false
                    onResult(false)
                }
        }
    }
}
