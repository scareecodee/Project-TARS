package com.example.projecttars.ViewModels.Firebase


import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.projecttars.DataModels.Achievement
import com.google.firebase.database.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AchievementsVM : ViewModel() {

    private val _achievements = MutableStateFlow<List<Achievement>>(emptyList())
    val achievements: StateFlow<List<Achievement>> = _achievements

    private val database = FirebaseDatabase.getInstance().getReference("Achievements")

    private var listenerAdded = false
    private var listener: ValueEventListener? = null



    fun addAchievement(achievement: Achievement, onResult: (Boolean) -> Unit) {
        val newRef = database.push() // Firebase generates unique ID
        val achievementWithId = achievement.copy(id = newRef.key ?: "")
        newRef.setValue(achievementWithId)
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { e ->
                Log.e("AchievementsVM", "Failed to add achievement: ${e.message}")
                onResult(false)
            }
    }



    fun observeAchievements() {
        if (listenerAdded) return
        listenerAdded = true

        listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = snapshot.children.mapNotNull { it.getValue(Achievement::class.java) }
                _achievements.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                _achievements.value = emptyList()
                Log.e("AchievementsVM", "Failed to read achievements: ${error.message}")
            }
        }

        database.addValueEventListener(listener as ValueEventListener)
    }



    fun updateAchievement(id: String, updated: Achievement, onResult: (Boolean) -> Unit) {
        if (id.isBlank()) {
            onResult(false)
            return
        }

        val updates = mapOf<String, Any?>(
            "imageUrl" to updated.imageUrl,
            "title" to updated.title,
            "shortDescription" to updated.shortDescription
        )

        database.child(id).updateChildren(updates)
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { e ->
                Log.e("AchievementsVM", "Failed to update: ${e.message}")
                onResult(false)
            }
    }



    fun deleteAchievement(id: String, onResult: (Boolean) -> Unit) {
        if (id.isBlank()) {
            onResult(false)
            return
        }

        database.child(id).removeValue()
            .addOnSuccessListener {
                _achievements.value = _achievements.value.filterNot { it.id == id }
                onResult(true)
            }
            .addOnFailureListener { e ->
                Log.e("AchievementsVM", "Failed to delete: ${e.message}")
                onResult(false)
            }
    }



    override fun onCleared() {
        super.onCleared()
        listener?.let { database.removeEventListener(it) }
        listenerAdded = false
        Log.d("AchievementsVM", "Firebase listener removed in onCleared()")
    }
}
