package com.prafullkumar.domeupdates

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.prafullkumar.domeupdates.ui.screens.comments.CommentsScreen
import com.prafullkumar.domeupdates.ui.screens.updates.UpdatesScreen
import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object Updates : Route

    @Serializable
    data class Comments(val postId: Long) : Route
}


@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController, startDestination = Route.Updates) {
        composable<Route.Updates> {
            UpdatesScreen(navController = navController)
        }
        composable<Route.Comments> { backStackEntry ->
            val postId = backStackEntry.arguments?.getInt("postId") ?: 0
            CommentsScreen(navController = navController)
        }
    }
}