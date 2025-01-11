package com.prafullkumar.domeupdates.data.local.posts

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val username: String,
    val title: String,
    val body: String,
    val timestamp: Long,
    val numberOfComments: Int = 0,
    val numberOfShares: Int = 0,
)