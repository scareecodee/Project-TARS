package com.example.projecttars.ViewModels.Firebase


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projecttars.DataModels.NotificationItem
import com.google.firebase.database.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class NotificationVM : ViewModel() {

    private val db = FirebaseDatabase.getInstance().getReference("notifications")


    private val _notifications = MutableStateFlow<List<NotificationItem>>(emptyList())
    val notifications: StateFlow<List<NotificationItem>> = _notifications

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {

        observeNotifications()
    }


    fun createNotification(
        title: String,
        description: String,
        onResult: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            val id = db.push().key ?: return@launch


            val notificationData = mapOf(
                "id" to id,
                "title" to title,
                "description" to description,
                "timestamp" to ServerValue.TIMESTAMP
            )

            db.child(id).setValue(notificationData)
                .addOnSuccessListener { onResult(true) }
                .addOnFailureListener { onResult(false) }
        }
    }



    fun deleteNotification(
        notificationId: String,
        onResult: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            db.child(notificationId).removeValue()
                .addOnSuccessListener { onResult(true) }
                .addOnFailureListener { onResult(false) }
        }
    }


    fun observeNotifications() {
        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val notificationsList = mutableListOf<NotificationItem>()
                for (child in snapshot.children) {
                    val notification = child.getValue(NotificationItem::class.java)
                    if (notification != null) {
                        notificationsList.add(notification)
                    }
                }
                _notifications.value = notificationsList
            }

            override fun onCancelled(error: DatabaseError) {
                _error.value = error.message
            }
        })
    }
}





