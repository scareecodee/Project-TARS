package com.example.projecttars.ViewModels.Firebase

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.projecttars.DataModels.TarsLabComponent
import com.google.firebase.database.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ResourcesVM : ViewModel() {

    private val _equipments = MutableStateFlow<List<TarsLabComponent>>(emptyList())
    val equipments: StateFlow<List<TarsLabComponent>> = _equipments

    private val database = FirebaseDatabase.getInstance().getReference("Resources")


    fun saveEquipment(equipment: TarsLabComponent, onResult: (Boolean) -> Unit) {
        val newRef = database.push()
        val uniqueId = newRef.key ?: return onResult(false)

        val equipmentWithId = equipment.copy(id = uniqueId)

        newRef.setValue(equipmentWithId)
            .addOnSuccessListener {
                onResult(true)
            }
            .addOnFailureListener { e ->
                onResult(false)
            }
    }



    fun observeEquipments() {
        Log.d("ResourcesVM", "Observing equipments")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = snapshot.children.mapNotNull { it.getValue(TarsLabComponent::class.java) }
                _equipments.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                _equipments.value = emptyList()
            }
        })
    }

    fun updateEquipment(equipment: TarsLabComponent, onResult: (Boolean) -> Unit) {
        if (equipment.id.isBlank()) {
            onResult(false)
            return
        }

        val updates = mapOf<String, Any?>(
            "name" to equipment.name,
            "imageUrl" to equipment.imageUrl,
            "description" to equipment.description,
            "available" to equipment.available,
            "youtubeUrl" to equipment.youtubeUrl,
            "documentationUrl" to equipment.documentationUrl
        )

        database.child(equipment.id).updateChildren(updates)
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }

    fun deleteEquipment(equipmentId: String, onResult: (Boolean) -> Unit) {
        if (equipmentId.isBlank()) {
            onResult(false)
            return
        }

        database.child(equipmentId).removeValue()
            .addOnSuccessListener {
                _equipments.value = _equipments.value.filter { it.id != equipmentId }
                onResult(true)
            }
            .addOnFailureListener {
                onResult(false)
            }
    }




}
