package com.prafullkumar.domeupdates.data.local.comments


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CommentsDao {
    @Query("SELECT * FROM CommentEntity WHERE postId = :postId")
    fun getCommentsOfPost(postId: Long): Flow<List<CommentEntity>>

    @Insert
    suspend fun insertComment(comment: CommentEntity): Long

}