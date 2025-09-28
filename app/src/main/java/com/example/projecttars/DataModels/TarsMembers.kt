package com.example.projecttars.DataModels


data class TarsMember(
    val name: String,
    val designation: String,
    val gender: String,
    val id: String,
    val domain:String
)

data class MemberDetail(
    val name: String,
    val imageResId: Int,
    val domain: String,
    val id: String,
    val branch: String,
    val designation: String,
    val projects: List<String>,
    val linkedinUrl: String?
)