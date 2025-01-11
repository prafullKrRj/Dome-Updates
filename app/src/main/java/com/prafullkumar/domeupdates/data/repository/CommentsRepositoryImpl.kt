package com.prafullkumar.domeupdates.data.repository

import androidx.room.withTransaction
import com.prafullkumar.domeupdates.data.local.DomeUpdatesDatabase
import com.prafullkumar.domeupdates.data.local.comments.CommentsDao
import com.prafullkumar.domeupdates.data.local.posts.PostsDao
import com.prafullkumar.domeupdates.data.mappers.toComment
import com.prafullkumar.domeupdates.data.mappers.toCommentEntity
import com.prafullkumar.domeupdates.data.mappers.toPost
import com.prafullkumar.domeupdates.domain.model.Comment
import com.prafullkumar.domeupdates.domain.model.PostWithComments
import com.prafullkumar.domeupdates.domain.repository.CommentsRepository
import com.prafullkumar.domeupdates.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CommentsRepositoryImpl @Inject constructor(
    private val commentsDao: CommentsDao,
    private val postsDao: PostsDao,
    private val domeUpdatesDatabase: DomeUpdatesDatabase
) : CommentsRepository {
    override fun getAllComments(postId: Long): Flow<List<Comment>> =
        commentsDao.getCommentsOfPost(postId).map { comments ->
            comments.map { it.toComment() }
        }

    override suspend fun addComment(comment: Comment): Flow<Resource<Boolean>> = channelFlow {
        domeUpdatesDatabase.withTransaction {
            val id = commentsDao.insertComment(comment.toCommentEntity())
            val post = postsDao.getPostById(comment.postId)
            val response = postsDao.insertPost(
                post!!.copy(
                    numberOfComments = post.numberOfComments + 1,
                )
            )
            send(Resource.Success(response > 0))
        }
    }

    override fun getPostWithComments(postId: Long): Flow<PostWithComments> = channelFlow {
        val post = postsDao.getPostById(postId) ?: throw IllegalStateException("Post not found")
        commentsDao.getCommentsOfPost(postId).collect { comments ->
            send(
                PostWithComments(
                    post = post.toPost(),
                    comments = comments.map { it.toComment() })
            )
        }
    }
}