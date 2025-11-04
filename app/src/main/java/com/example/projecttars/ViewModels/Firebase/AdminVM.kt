package com.example.projecttars.ViewModels.Firebase

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AdminVM : ViewModel() {

    private val dbRef = FirebaseDatabase.getInstance().getReference("Admins")


    private val _adminEmails = MutableStateFlow<List<String>>(emptyList())
    val adminEmails: StateFlow<List<String>> = _adminEmails

    init {
        observeAdmins()
    }


    private fun observeAdmins() {
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val emails = snapshot.children.mapNotNull { it.getValue(String::class.java) }
                _adminEmails.value = emails
            }

            override fun onCancelled(error: DatabaseError) {
                error.toException().printStackTrace()
            }
        })
    }


    fun isAdmin(email: String, callback: (Boolean) -> Unit) {
        Log.d("AdminVM", "Checking if $email is an admin")
        dbRef.get().addOnSuccessListener { snapshot ->
            val emails = snapshot.children.mapNotNull { it.getValue(String::class.java) }
            callback(emails.contains(email))
        }.addOnFailureListener {
            it.printStackTrace()
            callback(false)
        }
    }


    fun addAdmin(email: String, onResult: (Boolean) -> Unit) {
        if (email.isBlank()) {
            onResult(false)
            return
        }

        val key = dbRef.push().key
        if (key == null) {
            onResult(false)
            return
        }

        dbRef.child(key).setValue(email)
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { e ->
                onResult(false)
            }
    }


    fun deleteAdmin(email: String, onResult: (Boolean) -> Unit) {
        if (email.isBlank()) {
            onResult(false)
            return
        }

        dbRef.get()
            .addOnSuccessListener { snapshot ->
                val child = snapshot.children.find { it.getValue(String::class.java) == email }
                if (child != null) {
                    child.ref.removeValue()
                        .addOnSuccessListener { onResult(true) }
                        .addOnFailureListener { onResult(false) }
                } else {
                    onResult(false)
                }
            }
            .addOnFailureListener {
                onResult(false)
            }
    }

}
