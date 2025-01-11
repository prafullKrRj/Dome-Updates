package com.prafullkumar.domeupdates.domain.repository

import com.prafullkumar.domeupdates.domain.model.Comment
import com.prafullkumar.domeupdates.domain.model.PostWithComments
import com.prafullkumar.domeupdates.util.Resource
import kotlinx.coroutines.flow.Flow

interface CommentsRepository {

    fun getAllComments(postId: Long): Flow<List<Comment>>
    suspend fun addComment(comment: Comment): Flow<Resource<Boolean>>
    fun getPostWithComments(postId: Long): Flow<PostWithComments>
}