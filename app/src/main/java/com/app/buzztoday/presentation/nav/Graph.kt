package com.app.buzztoday.presentation.nav

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.app.buzztoday.presentation.startup.OnBoardingScreen
import com.app.buzztoday.presentation.startup.StartupViewModel
import com.app.buzztoday.presentation.state.ViewModel

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
                val viewModel: StartupViewModel = hiltViewModel()
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
                com.app.buzztoday.presentation.state.Screen(
                    state = viewModel.state.value   ,
                    event = viewModel::onEvent,
                    navigate = {}
                )
            }
        }
    }
}