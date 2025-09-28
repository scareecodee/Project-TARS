package com.example.projecttars.DataModels

data class Project(
    val id: Int,
    val imageResId: Int,
    val title: String,
    val shortDescription: String
)

data class CompletedProjectDetail(
    val name: String,
    val imageResId: Int,
    val developers: List<String>,
    val guidedBy: List<String>,
    val equipmentUsed: List<String>,
    val techStack: List<String>,
    val problemSolved: String,
    val youtubeUrl: String? = null
)

data class OngoingProjectDetail(
    val name: String,
    val imageResId: Int,
    val developers: List<String>,
    val guidedBy: List<String>,
    val equipmentUsed: List<String>,
    val techStack: List<String>,
    val problemSolved: String,
    val youtubeUrl: String? = null
)