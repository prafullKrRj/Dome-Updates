package com.prafullkumar.domeupdates.domain.model

data class PostWithComments(
    val post: Post? = null,
    val comments: List<Comment> = emptyList()
)