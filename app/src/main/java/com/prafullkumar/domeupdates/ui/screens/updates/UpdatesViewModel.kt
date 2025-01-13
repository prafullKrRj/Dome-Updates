package com.prafullkumar.domeupdates.ui.screens.updates

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prafullkumar.domeupdates.domain.model.Comment
import com.prafullkumar.domeupdates.domain.model.Post
import com.prafullkumar.domeupdates.domain.model.PostWithComments
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

// List of sample posts
val samplePosts = listOf(
    Post(
        id = 1001,
        username = "Elon Musk",
        title = "SpaceX's Latest Launch",
        body = "Just launched another Starship prototype. The future of space travel is here!",
        timestamp = System.currentTimeMillis(),
        numberOfComments = 3,
        numberOfShares = 15
    ),
    Post(
        id = 1002,
        username = "Mark Zuckerberg",
        title = "Meta's New VR Technology",
        body = "Introducing our latest breakthrough in virtual reality. Coming soon to Quest!",
        timestamp = System.currentTimeMillis() - 86400000, // 1 day ago
        numberOfComments = 4,
        numberOfShares = 25
    ),
    Post(
        id = 1003,
        username = "Tim Cook",
        title = "Apple Vision Pro Update",
        body = "Exciting new features coming to Vision Pro. Stay tuned for the next update!",
        timestamp = System.currentTimeMillis() - 172800000, // 2 days ago
        numberOfComments = 5,
        numberOfShares = 30
    ),
    Post(
        id = 1004,
        username = "Satya Nadella",
        title = "Microsoft AI Innovations",
        body = "Our latest AI models are transforming how we work. Check out the demo!",
        timestamp = System.currentTimeMillis() - 259200000, // 3 days ago
        numberOfComments = 2,
        numberOfShares = 18
    ),
    Post(
        id = 1005,
        username = "Sundar Pichai",
        title = "Google I/O Announcements",
        body = "Unveiling our newest developments in AI and mobile technology.",
        timestamp = System.currentTimeMillis() - 345600000, // 4 days ago
        numberOfComments = 6,
        numberOfShares = 40
    )
)

// List of sample comments
val sampleComments = listOf(
    // Comments for Post 1001
    Comment(
        commentId = 501,
        postId = 1001,
        body = "Amazing progress! When is the next launch scheduled?",
        timestamp = System.currentTimeMillis() - 3600000, // 1 hour ago
        username = "Jeff Bezos"
    ),
    Comment(
        commentId = 502,
        postId = 1001,
        body = "The landing was spectacular! Can't wait to see more.",
        timestamp = System.currentTimeMillis() - 1800000, // 30 minutes ago
        username = "Bill Gates"
    ),
    Comment(
        commentId = 503,
        postId = 1001,
        body = "This is revolutionary for space exploration!",
        timestamp = System.currentTimeMillis() - 900000, // 15 minutes ago
        username = "Richard Branson"
    ),

    // Comments for Post 1002
    Comment(
        commentId = 504,
        postId = 1002,
        body = "Will this be compatible with existing Quest hardware?",
        timestamp = System.currentTimeMillis() - 43200000, // 12 hours ago
        username = "John Developer"
    ),
    Comment(
        commentId = 505,
        postId = 1002,
        body = "The demo looks promising. Hope it lives up to the hype!",
        timestamp = System.currentTimeMillis() - 21600000, // 6 hours ago
        username = "Tech Enthusiast"
    ),
    Comment(
        commentId = 506,
        postId = 1002,
        body = "Can't wait to try this in gaming applications",
        timestamp = System.currentTimeMillis() - 10800000, // 3 hours ago
        username = "Gaming Pro"
    ),
    Comment(
        commentId = 507,
        postId = 1002,
        body = "What about the battery life implications?",
        timestamp = System.currentTimeMillis() - 7200000, // 2 hours ago
        username = "Hardware Expert"
    ),

    // Comments for Post 1003
    Comment(
        commentId = 508,
        postId = 1003,
        body = "Will this include the rumored AR features?",
        timestamp = System.currentTimeMillis() - 144000000, // 40 hours ago
        username = "Apple Fan"
    ),
    Comment(
        commentId = 509,
        postId = 1003,
        body = "Price point still seems high for mass adoption",
        timestamp = System.currentTimeMillis() - 129600000, // 36 hours ago
        username = "Market Analyst"
    ),
    Comment(
        commentId = 510,
        postId = 1003,
        body = "The display quality is already amazing!",
        timestamp = System.currentTimeMillis() - 115200000, // 32 hours ago
        username = "Display Tech"
    ),
    Comment(
        commentId = 511,
        postId = 1003,
        body = "How about enterprise applications?",
        timestamp = System.currentTimeMillis() - 100800000, // 28 hours ago
        username = "Enterprise User"
    ),
    Comment(
        commentId = 512,
        postId = 1003,
        body = "Looking forward to developer tools",
        timestamp = System.currentTimeMillis() - 86400000, // 24 hours ago
        username = "iOS Developer"
    ),

    // Comments for Post 1004
    Comment(
        commentId = 513,
        postId = 1004,
        body = "Integration with Azure looks promising",
        timestamp = System.currentTimeMillis() - 172800000, // 48 hours ago
        username = "Cloud Expert"
    ),
    Comment(
        commentId = 514,
        postId = 1004,
        body = "What about data privacy concerns?",
        timestamp = System.currentTimeMillis() - 158400000, // 44 hours ago
        username = "Privacy Advocate"
    ),

    // Comments for Post 1005
    Comment(
        commentId = 515,
        postId = 1005,
        body = "Android 15 features look great!",
        timestamp = System.currentTimeMillis() - 259200000, // 72 hours ago
        username = "Android Dev"
    ),
    Comment(
        commentId = 516,
        postId = 1005,
        body = "Will this affect Chrome OS too?",
        timestamp = System.currentTimeMillis() - 244800000, // 68 hours ago
        username = "Chrome User"
    ),
    Comment(
        commentId = 517,
        postId = 1005,
        body = "Tensor chip improvements are impressive",
        timestamp = System.currentTimeMillis() - 230400000, // 64 hours ago
        username = "Hardware Geek"
    ),
    Comment(
        commentId = 518,
        postId = 1005,
        body = "How about backwards compatibility?",
        timestamp = System.currentTimeMillis() - 216000000, // 60 hours ago
        username = "Legacy Support"
    ),
    Comment(
        commentId = 519,
        postId = 1005,
        body = "Documentation needs to be updated",
        timestamp = System.currentTimeMillis() - 201600000, // 56 hours ago
        username = "Tech Writer"
    ),
    Comment(
        commentId = 520,
        postId = 1005,
        body = "API changes look promising",
        timestamp = System.currentTimeMillis() - 187200000, // 52 hours ago
        username = "API Developer"
    )
)
val samplePostsWithComments = listOf<PostWithComments>(
    PostWithComments(
        post = samplePosts[0],
        comments = sampleComments.filter { it.postId == 1001L }
    ),
    PostWithComments(
        post = samplePosts[1],
        comments = sampleComments.filter { it.postId == 1002L }
    ),
    PostWithComments(
        post = samplePosts[2],
        comments = sampleComments.filter { it.postId == 1003L }
    ),
    PostWithComments(
        post = samplePosts[3],
        comments = sampleComments.filter { it.postId == 1004L }
    ),
    PostWithComments(
        post = samplePosts[4],
        comments = sampleComments.filter { it.postId == 1005L }
    ),

    )

