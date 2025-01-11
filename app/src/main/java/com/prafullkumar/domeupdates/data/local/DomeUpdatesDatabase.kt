package com.prafullkumar.domeupdates.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.prafullkumar.domeupdates.data.local.comments.CommentEntity
import com.prafullkumar.domeupdates.data.local.comments.CommentsDao
import com.prafullkumar.domeupdates.data.local.posts.PostEntity
import com.prafullkumar.domeupdates.data.local.posts.PostsDao

@Database(entities = [PostEntity::class, CommentEntity::class], version = 1)
abstract class DomeUpdatesDatabase : RoomDatabase() {
    abstract fun postsDao(): PostsDao
    abstract fun commentsDao(): CommentsDao
}