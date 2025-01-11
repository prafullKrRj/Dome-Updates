package com.prafullkumar.domeupdates.ui.screens.comments

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CommentsScreen(viewModel: CommentsViewModel = hiltViewModel()) {
    Text(text = viewModel.postId.toString())
}