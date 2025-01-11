package com.prafullkumar.domeupdates.data.mappers

import com.prafullkumar.domeupdates.data.local.comments.CommentEntity
import com.prafullkumar.domeupdates.domain.model.Comment

fun CommentEntity.toComment(): Comment {
    return Comment(
        commentId = commentId,
        username = username,
        body = body,
        timestamp = timestamp,
        postId = postId
    )
}

fun Comment.toCommentEntity(): CommentEntity {
    return CommentEntity(
        username = username,
        body = body,
        timestamp = timestamp,
        postId = postId,
        commentId = commentId
    )
}