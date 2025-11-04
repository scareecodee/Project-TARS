package com.example.projecttars.ViewModels.Firebase

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.projecttars.DataModels.MemberDetail
import com.google.firebase.database.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MembersVM : ViewModel() {

    private val _members = MutableStateFlow<List<MemberDetail>>(emptyList())
    val members: StateFlow<List<MemberDetail>> = _members

    private val database = FirebaseDatabase.getInstance().getReference("Members")


    private var listenerAdded = false
    private var listener: ValueEventListener? = null



    fun observeMembers() {

        if (listenerAdded) return
        listenerAdded = true

        listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = snapshot.children.mapNotNull { it.getValue(MemberDetail::class.java) }
                _members.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                _members.value = emptyList()
                Log.e("MembersVM", "Failed to read members: ${error.message}")
            }
        }

        database.addValueEventListener(listener as ValueEventListener)
    }



    fun addMember(member: MemberDetail, onResult: (Boolean) -> Unit) {
        if (member.id.isBlank()) {
            onResult(false)
            return
        }



        database.child(member.id).setValue(member)
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { e ->
                Log.e("MembersVM", "Failed to add member: ${e.message}")
                onResult(false)
            }
    }



    fun updateMember(member: MemberDetail, onResult: (Boolean) -> Unit) {
        if (member.id.isBlank()) {
            onResult(false)
            return
        }

        val updates = mapOf<String, Any?>(
            "id" to member.id,
            "name" to member.name,
            "imageUrl" to member.imageUrl,
            "domain" to member.domain,
            "branch" to member.branch,
            "designation" to member.designation,
            "projects" to member.projects,
            "linkedinUrl" to member.linkedinUrl,
            "gender" to member.gender
        )

        database.child(member.id).updateChildren(updates)
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { e ->
                Log.e("MembersVM", "Failed to update member: ${e.message}")
                onResult(false)
            }
    }



    fun deleteMember(memberId: String, onResult: (Boolean) -> Unit) {
        if (memberId.isBlank()) {
            onResult(false)
            return
        }

        database.child(memberId).removeValue()
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { e ->
                Log.e("MembersVM", "Failed to delete member: ${e.message}")
                onResult(false)
            }
    }



    override fun onCleared() {
        super.onCleared()
        listener?.let { database.removeEventListener(it) }
        listenerAdded = false
        Log.d("MembersVM", "Firebase listener removed in onCleared()")
    }

    fun getMemberById(memberId: String, onResult: (MemberDetail?) -> Unit) {
        if (memberId.isBlank()) {
            onResult(null)
            return
        }

        database.child(memberId.replaceFirstChar { it.lowercase() })
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val member = snapshot.getValue(MemberDetail::class.java)
                    onResult(member)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("MembersVM", "Failed to get member: ${error.message}")
                    onResult(null)
                }
            })
    }
}
