package com.prafullkumar.domeupdates.data.local.comments

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CommentEntity(
    @PrimaryKey(autoGenerate = true)
    val commentId: Long,
    val postId: Long,
    val body: String,
    val timestamp: Long,
    val username: String = "Elon Musk",
)
