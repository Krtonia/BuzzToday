package com.example.buzztoday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.buzztoday.presentation.navgraph.NavGraph
import com.example.buzztoday.ui.theme.BuzzTodayTheme
import dagger.hilt.android.AndroidEntryPoint


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply { setKeepOnScreenCondition(condition = { viewModel.splashCondition }) }
        enableEdgeToEdge()
        setContent {
            BuzzTodayApp()
        }
    }
}

@Composable
fun BuzzTodayApp() {
    val viewModel: MainModel = viewModel()
    BuzzTodayTheme {
        val isdark = isSystemInDarkTheme()
        val sysControl = rememberSystemUiController()

        SideEffect {
            sysControl.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = !isdark
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            val sd = viewModel.startDestination
            NavGraph(startDestination = sd)
        }
    }
}

