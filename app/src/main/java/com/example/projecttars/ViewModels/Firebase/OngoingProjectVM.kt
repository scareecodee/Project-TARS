package com.example.projecttars.ViewModels.Firebase




import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.projecttars.DataModels.OngoingProjectDetail
import com.google.firebase.database.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class OngoingProjectVM : ViewModel() {

    private val _ongoingProjects = MutableStateFlow<List<OngoingProjectDetail>>(emptyList())
    val completedProjects: StateFlow<List<OngoingProjectDetail>> =_ongoingProjects

    private val database = FirebaseDatabase.getInstance().getReference("OngoingProjects")

    private var listenerAdded = false
    private var listener: ValueEventListener? = null



    fun addOngoingProject(ongoingProject:OngoingProjectDetail, onResult: (Boolean) -> Unit) {
        val newRef = database.push()
        val completedProjectWithId = ongoingProject.copy(id = newRef.key ?: "")
        newRef.setValue(completedProjectWithId)
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { e ->
                Log.e("OngoingProjectVM", "Failed to add ongoing project: ${e.message}")
                onResult(false)
            }
    }



    fun observeOngoingProjects() {
        if (listenerAdded) return
        listenerAdded = true

        listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = snapshot.children.mapNotNull { dataSnapshot ->
                    val project = dataSnapshot.getValue(OngoingProjectDetail::class.java)
                    project?.copy(id = dataSnapshot.key ?: "")
                }
                _ongoingProjects.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                _ongoingProjects.value = emptyList()
                Log.e("OngoingProjectVM", "Failed to read ongoing projects: ${error.message}")
            }
        }

        database.addValueEventListener(listener as ValueEventListener)
    }




    fun updateOngoingProject(id: String, updated: OngoingProjectDetail, onResult: (Boolean) -> Unit) {
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
                Log.e("OngoingProjectVM", "Failed to update: ${e.message}")
                onResult(false)
            }
    }



    fun deleteOngoingProject(id: String, onResult: (Boolean) -> Unit) {
        if (id.isBlank()) {
            onResult(false)
            return
        }

        database.child(id).removeValue()
            .addOnSuccessListener {
                _ongoingProjects.value = _ongoingProjects.value.filterNot { it.id == id }
                onResult(true)
            }
            .addOnFailureListener { e ->
                Log.e("OngoingProjectVM", "Failed to delete: ${e.message}")
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
