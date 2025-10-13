package com.example.projecttars.DataModels


data class MemberDetail(
    val id: String = "",
    val name: String = "",
    val imageUrl: String = "",
    val domain: String = "",
    val branch: String = "",
    val designation: String = "",
    val projects: List<String> = emptyList(),
    val linkedinUrl: String ?= "",
    val gender: String = ""
)
