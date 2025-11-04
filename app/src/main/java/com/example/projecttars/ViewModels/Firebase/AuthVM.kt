package com.example.projecttars.ViewModels.Firebase

import android.app.Application
import androidx.lifecycle.*
import com.example.projecttars.Utils.UserPreferences
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AuthVM(application: Application) : AndroidViewModel(application) {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val prefs = UserPreferences(application)

    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    private val _userRole = MutableStateFlow<String?>(null)



    fun setUserRole(role: String) {
        _userRole.value = role
        viewModelScope.launch {
            prefs.saveUserData(true, role)
        }
    }

    fun getCurrentUserMail(): String? = auth.currentUser?.email

    fun login(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email or password can't be empty")
            return
        }

        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.value = AuthState.Authenticated
                    viewModelScope.launch {
                        prefs.saveUserData(true, _userRole.value ?:"")
                    }
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Something went wrong")
                }
            }
    }


    fun signout() {
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
        viewModelScope.launch { prefs.clearUserData() }
    }


    val isLoggedIn = prefs.isLoggedIn.asLiveData()
    val savedRole = prefs.userRole.asLiveData()
}


sealed class AuthState{
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    data class Error(val message : String) : AuthState()
}