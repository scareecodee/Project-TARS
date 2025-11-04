package com.example.projecttars.DataModels

import androidx.compose.ui.graphics.Color

data class SocialHandle(
    val name: String,
    val url: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val color: Color
)


data class SocialMediaLinks(
    val instagram: String = "",
    val youtube: String = "",
    val linkedin: String = "",
    val mail: String = ""
)