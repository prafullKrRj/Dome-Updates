package com.prafullkumar.domeupdates.domain.model

data class Post(
    val id: Long = 0,
    val username: String = "Elon Musk",
    val title: String = "",
    val body: String = "",
    val timestamp: Long = System.currentTimeMillis(),
    val numberOfComments: Int = 0,
    val numberOfShares: Int = 0,
)
