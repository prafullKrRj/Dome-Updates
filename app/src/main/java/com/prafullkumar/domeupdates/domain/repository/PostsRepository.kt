package com.prafullkumar.domeupdates.domain.repository

import com.prafullkumar.domeupdates.domain.model.Post
import com.prafullkumar.domeupdates.domain.model.PostWithComments
import kotlinx.coroutines.flow.Flow

interface PostsRepository {
    fun getPosts(): Flow<List<Post>>
    suspend fun getPostById(id: Long): Post?
    suspend fun savePost(post: Post): Flow<Boolean>
    suspend fun savePosts(posts: List<Post>)
    suspend fun savePostsWithComments(postWithComments: List<PostWithComments>)
}