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

val samplePosts = listOf(
    Post(
        id = 1,
        username = "Sports Reporter",
        title = "India's Historic Victory at Asian Games",
        body = "India secured their best-ever medal haul at the Asian Games 2023, finishing with a total of 107 medals including 28 gold medals. The performance marks a significant improvement from their previous showing.",
        numberOfComments = 156,
        numberOfShares = 89,
        timestamp = System.currentTimeMillis() - (1 * 24 * 60 * 60 * 1000) // 1 day ago
    ),

    Post(
        id = 2,
        username = "Cricket Updates",
        title = "Virat Kohli Scores Another Century",
        body = "In a spectacular display of batting, Virat Kohli scored his 49th ODI century against South Africa. The innings helped India secure a convincing victory in the World Cup match.",
        numberOfComments = 342,
        numberOfShares = 178,
        timestamp = System.currentTimeMillis() - (2 * 24 * 60 * 60 * 1000) // 2 days ago
    ),

    Post(
        id = 3,
        username = "Tech Insider",
        title = "New AI Breakthrough in Sports Analysis",
        body = "A revolutionary AI system has been developed that can predict athlete injuries with 85% accuracy. The system analyzes movement patterns and biometric data in real-time.",
        numberOfComments = 89,
        numberOfShares = 45,
        timestamp = System.currentTimeMillis() - (3 * 24 * 60 * 60 * 1000) // 3 days ago
    ),

    Post(
        id = 4,
        username = "Olympic News",
        title = "Paris Olympics 2024 Preparations Update",
        body = "The organizing committee has confirmed that all major venues are ahead of schedule. The new sustainable athletes' village has received widespread praise from the international community.",
        numberOfComments = 234,
        numberOfShares = 112,
        timestamp = System.currentTimeMillis() - (4 * 24 * 60 * 60 * 1000) // 4 days ago
    ),

    Post(
        id = 5,
        username = "Sports Science",
        title = "Revolutionary Training Method Unveiled",
        body = "Scientists have developed a new high-altitude training protocol that shows remarkable improvements in athlete endurance. The method has already been adopted by several Olympic teams.",
        numberOfComments = 167,
        numberOfShares = 89,
        timestamp = System.currentTimeMillis() - (5 * 24 * 60 * 60 * 1000) // 5 days ago
    ),

    Post(
        id = 6,
        username = "Athletics Weekly",
        title = "New World Record in Marathon",
        body = "Kelvin Kiptum has broken the men's marathon world record with an incredible time of 2:00:35 in Chicago. This marks the first sub-2:01 marathon in history.",
        numberOfComments = 423,
        numberOfShares = 267,
        timestamp = System.currentTimeMillis() - (6 * 24 * 60 * 60 * 1000) // 6 days ago
    ),

    Post(
        id = 7,
        username = "Tennis Updates",
        title = "Alcaraz Wins US Open 2024",
        body = "Carlos Alcaraz has claimed his second US Open title in a thrilling five-set match against Novak Djokovic. The match lasted over four hours and showcased tennis at its finest.",
        numberOfComments = 567,
        numberOfShares = 289,
        timestamp = System.currentTimeMillis() - (7 * 24 * 60 * 60 * 1000) // 7 days ago
    ),

    Post(
        id = 8,
        username = "Sports Economics",
        title = "Record Broadcasting Rights Deal",
        body = "Major sports leagues have signed a historic $50 billion broadcasting rights deal for the next decade. This marks the largest sports media deal in history.",
        numberOfComments = 145,
        numberOfShares = 78,
        timestamp = System.currentTimeMillis() - (8 * 24 * 60 * 60 * 1000) // 8 days ago
    ),

    Post(
        id = 9,
        username = "Football Daily",
        title = "Champions League Final Venue Announced",
        body = "UEFA has confirmed that the 2025 Champions League final will be held at the Allianz Arena in Munich. This will be the fourth time Munich hosts the prestigious event.",
        numberOfComments = 289,
        numberOfShares = 134,
        timestamp = System.currentTimeMillis() - (9 * 24 * 60 * 60 * 1000) // 9 days ago
    ),

    Post(
        id = 10,
        username = "Sports Tech",
        title = "Smart Stadiums Revolution",
        body = "Next-generation stadiums equipped with AI and IoT technology are transforming the spectator experience. Features include real-time stats, AR replays, and automated concessions.",
        numberOfComments = 198,
        numberOfShares = 95,
        timestamp = System.currentTimeMillis() - (10 * 24 * 60 * 60 * 1000) // 10 days ago
    )
)