package com.example.projecttars.DataModels

data class EquipmentDetail(
    val id: Int,
    val name: String,
    val imageResId: Int,
    val description: String,
    val isAvailable: Boolean,
    val youtubeUrl: String?,
    val documentationUrl: String?
)