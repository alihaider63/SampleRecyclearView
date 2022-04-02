package com.example.samplerecyclearview.models

data class User(
    val first_name: String,
    val last_name: String,
    val user_id: Int,
    val scholarship_points: Int,
    val state: String,
    val joined: String,
    val rank: Int,
    val hours: Int,
    val school_id: Int,
    val profile_image_url: String,
    val is_online: Boolean,
    val is_tutor: Boolean,
    val role: String,
    val role_id: Int,
    val school: String,
    val registered: Boolean,
    val email: String
)
