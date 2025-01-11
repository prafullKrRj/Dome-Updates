package com.prafullkumar.domeupdates.data.repository

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
        val response = postsDao.insertPost(post.toPostEntity())
        emit(response > 0)
    }
}