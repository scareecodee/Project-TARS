package com.example.projecttars.DataModels



data class CompletedProjectDetail(
    val id: String="",
    val name: String="",
    val imageUrl: String="",
    val developers: List<String> = emptyList(),
    val guidedBy: List<String> = emptyList(),
    val equipmentUsed: List<String> = emptyList(),
    val techStack: List<String> = emptyList(),
    val problemSolved: String = "",
    val youtubeUrl: String? = null
)

data class OngoingProjectDetail(
    val id: String="",
    val name: String="",
    val imageUrl: String="",
    val developers: List<String> = emptyList(),
    val guidedBy: List<String> = emptyList(),
    val equipmentUsed: List<String> = emptyList(),
    val techStack: List<String> = emptyList(),
    val problemSolved: String = "",
    val youtubeUrl: String? = null
)