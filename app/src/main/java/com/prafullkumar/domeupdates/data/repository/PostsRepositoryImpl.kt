package com.prafullkumar.domeupdates.data.repository

import android.util.Log
import com.prafullkumar.domeupdates.data.local.posts.PostsDao
import com.prafullkumar.domeupdates.data.mappers.toPost
import com.prafullkumar.domeupdates.data.mappers.toPostEntity
import com.prafullkumar.domeupdates.domain.model.Post
import com.prafullkumar.domeupdates.domain.repository.PostsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class PostsRepositoryImpl @Inject constructor(
    private val postsDao: PostsDao,
) : PostsRepository {
    override fun getPosts(): Flow<List<Post>> {
        return postsDao.getAllPosts().map { posts ->
            posts.map { it.toPost() }
        }
    }

    override suspend fun getPostById(id: Long): Post? {
        return postsDao.getPostById(id)?.toPost()
    }

    override suspend fun savePost(post: Post): Flow<Boolean> = flow {
        Log.d("PostsRepositoryImpl", "savePost: $post")
        val response = postsDao.insertPost(post.copy(id = 0).toPostEntity())
        Log.d("PostsRepositoryImpl", "savePost: $response")
        emit(response > 0)
    }

    override suspend fun savePosts(posts: List<Post>) {
        Log.d("PostsRepositoryImpl", "savePosts: $posts")
        postsDao.insertPosts(posts.map { it.toPostEntity() })
    }
}