package com.example.buzztoday.presentation.navgraph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.buzztoday.presentation.home.Home
import com.example.buzztoday.presentation.home.Screen
import com.example.buzztoday.presentation.onboarding.OnBoardingScreen
import com.example.buzztoday.presentation.onboarding.OnBoardingViewModel
import com.example.buzztoday.presentation.state.ViewModel

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route,
        )
        {
            composable(
                route = Route.OnBoardingScreen.route,
            ) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(
                    event = { viewModel.onEvent(it) }
                )
            }
        }
        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        )
        {
            composable(route = Route.NewsNavigatorScreen.route)
            {
                val viewModel: ViewModel = hiltViewModel()
                com.example.buzztoday.presentation.state.Screen(
                    state = viewModel.state.value   ,
                    event = viewModel::onEvent,
                    navigate = {}
                )
            }
        }
    }
}