package com.example.samplerecyclearview.models

data class PostInfo(
    val post_id: Int,
    val user_id: Int,
    val view_count: Int,
    val date: String,
    val questions_count: Int,
    val thanks_count: Int,
    val feed_id: Int,
)
