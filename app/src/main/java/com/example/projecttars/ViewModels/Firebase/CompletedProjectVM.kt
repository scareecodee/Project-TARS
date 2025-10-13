package com.example.projecttars.ViewModels.Firebase



import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.projecttars.DataModels.CompletedProjectDetail
import com.google.firebase.database.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CompletedProjectVM : ViewModel() {

    private val _completedProjects = MutableStateFlow<List<CompletedProjectDetail>>(emptyList())
    val completedProjects: StateFlow<List<CompletedProjectDetail>> =_completedProjects

    private val database = FirebaseDatabase.getInstance().getReference("CompletedProjects")

    private var listenerAdded = false
    private var listener: ValueEventListener? = null



    fun addCompletedProject(completedProject:CompletedProjectDetail, onResult: (Boolean) -> Unit) {
        val newRef = database.push() // Firebase generates unique ID
        val completedProjectWithId = completedProject.copy(id = newRef.key ?: "")
        newRef.setValue(completedProjectWithId)
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { e ->
                Log.e("CompletedProjectVM", "Failed to add completed project: ${e.message}")
                onResult(false)
            }
    }



    fun observeCompletedProjects() {
        if (listenerAdded) return
        listenerAdded = true

        listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = snapshot.children.mapNotNull { dataSnapshot ->
                    val project = dataSnapshot.getValue(CompletedProjectDetail::class.java)
                    project?.copy(id = dataSnapshot.key ?: "")
                }
                _completedProjects.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                _completedProjects.value = emptyList()
                Log.e("CompletedProjectVM", "Failed to read completed projects: ${error.message}")
            }
        }

        database.addValueEventListener(listener as ValueEventListener)
    }




    fun updateCompletedProject(id: String, updated: CompletedProjectDetail, onResult: (Boolean) -> Unit) {
        Log.d("CompletedProjectVM", "Updating completed project with ID: $id")
        if (id.isBlank()) {
            onResult(false)
            return
        }

        val updates = mapOf<String, Any?>(
            "imageUrl" to updated.imageUrl,
            "name" to updated.name,
            "problemSolved" to updated.problemSolved,
            "youtubeUrl" to updated.youtubeUrl,
            "developers" to updated.developers,
            "guidedBy" to updated.guidedBy,
            "equipmentUsed" to updated.equipmentUsed,
            "techStack" to updated.techStack
        )

        database.child(id).updateChildren(updates)
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { e ->
                Log.e("AchievementsVM", "Failed to update: ${e.message}")
                onResult(false)
            }
    }



    fun deleteCompletedProject(id: String, onResult: (Boolean) -> Unit) {
        if (id.isBlank()) {
            onResult(false)
            return
        }

        database.child(id).removeValue()
            .addOnSuccessListener {
                _completedProjects.value = _completedProjects.value.filterNot { it.id == id }
                onResult(true)
            }
            .addOnFailureListener { e ->
                Log.e("CompletedProjectVM", "Failed to delete: ${e.message}")
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
