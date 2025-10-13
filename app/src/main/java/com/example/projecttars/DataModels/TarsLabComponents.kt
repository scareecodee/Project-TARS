package com.example.projecttars.DataModels

data class TarsLabComponent(
    var id: String = "",
    var name: String = "",
    var imageUrl: String = "",
    var description: String = "",
    var available: Boolean = false,
    var youtubeUrl: String? = null,
    var documentationUrl: String? = null
)