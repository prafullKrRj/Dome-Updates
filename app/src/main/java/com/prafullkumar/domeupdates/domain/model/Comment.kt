package com.prafullkumar.domeupdates.domain.model

data class Comment(
    val commentId: Long = 0,
    val postId: Long = 0,
    val body: String = "",
    val timestamp: Long = System.currentTimeMillis(),
    val username: String = "Elon Musk",
)