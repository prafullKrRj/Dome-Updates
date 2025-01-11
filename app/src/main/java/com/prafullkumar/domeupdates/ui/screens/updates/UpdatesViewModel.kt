package com.prafullkumar.domeupdates.ui.screens.updates

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prafullkumar.domeupdates.domain.model.Post
import com.prafullkumar.domeupdates.domain.repository.PostsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UpdatesViewModel @Inject constructor(
    private val repository: PostsRepository,
    private val context: Application
) : ViewModel() {

    val posts = repository.getPosts()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    var newPost by mutableStateOf(Post())

    fun addPost() {
        viewModelScope.launch {
            repository.savePost(newPost).collectLatest {
                if (it) {
                    newPost = Post()
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "Post added successfully", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
            newPost = Post()
        }
    }
}