package com.example.projecttars.DataModels

import androidx.compose.ui.graphics.Color

data class SocialMediaHandle(
    val name: String,
    val url: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val color: Color
)

data class SocialMediaLinks(
    val name: String,
    val url: String,
)