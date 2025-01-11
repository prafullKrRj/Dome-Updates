package com.prafullkumar.domeupdates.ui.screens.comments

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prafullkumar.domeupdates.domain.model.Comment
import com.prafullkumar.domeupdates.domain.model.PostWithComments
import com.prafullkumar.domeupdates.domain.repository.CommentsRepository
import com.prafullkumar.domeupdates.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val commentsRepository: CommentsRepository,
    val context: Application
) : ViewModel() {

    var postId by mutableLongStateOf(0L)

    init {
        postId = savedStateHandle["postId"] ?: 0L
        Log.d("CommentsViewModel", "postId: $postId")
    }

    val comments: StateFlow<PostWithComments> =
        commentsRepository.getPostWithComments(postId = savedStateHandle.get<Long>("postId") ?: 0L)
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000), PostWithComments()
            )

    var newComment by mutableStateOf(Comment(postId = postId, body = ""))

    fun addComment() {
        viewModelScope.launch(Dispatchers.IO) {
            commentsRepository.addComment(newComment).collectLatest {
                newComment = Comment(postId = postId, body = "")
                if (it is Resource.Success) {
                    viewModelScope.launch(Dispatchers.Main) {
                        Toast.makeText(context, "Comment added successfully", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                if (it is Resource.Error) {
                    viewModelScope.launch(Dispatchers.Main) {
                        Toast.makeText(context, "Failed to add comment", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}