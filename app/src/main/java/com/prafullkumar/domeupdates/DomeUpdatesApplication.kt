package com.prafullkumar.domeupdates

import android.app.Application
import androidx.room.Room
import com.prafullkumar.domeupdates.data.local.DomeUpdatesDatabase
import com.prafullkumar.domeupdates.data.local.comments.CommentsDao
import com.prafullkumar.domeupdates.data.local.posts.PostsDao
import com.prafullkumar.domeupdates.data.repository.CommentsRepositoryImpl
import com.prafullkumar.domeupdates.data.repository.PostsRepositoryImpl
import com.prafullkumar.domeupdates.domain.repository.CommentsRepository
import com.prafullkumar.domeupdates.domain.repository.PostsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@HiltAndroidApp
class DomeUpdatesApplication : Application()

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesUpdatesDatabase(application: Application): DomeUpdatesDatabase {
        return Room.databaseBuilder(
            application, DomeUpdatesDatabase::class.java, "dome_updates_database"
        ).build()
    }

    @Singleton
    @Provides
    fun providesPostsDao(database: DomeUpdatesDatabase) = database.postsDao()

    @Singleton
    @Provides
    fun providesCommentsDao(database: DomeUpdatesDatabase) = database.commentsDao()


    @Singleton
    @Provides
    fun providesPostsRepository(
        postsDao: PostsDao,
    ): PostsRepository {
        return PostsRepositoryImpl(postsDao)
    }

    @Singleton
    @Provides
    fun providesCommentsRepository(
        commentsDao: CommentsDao,
        domeUpdatesDatabase: DomeUpdatesDatabase,
        postsDao: PostsDao
    ): CommentsRepository {
        return CommentsRepositoryImpl(commentsDao, postsDao, domeUpdatesDatabase)
    }
}

