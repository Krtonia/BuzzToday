package com.example.buzztoday

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.example.buzztoday.domain.usecases.AppEntryUseCases
import com.example.buzztoday.presentation.onboarding.OnBoardingScreen
import com.example.buzztoday.presentation.onboarding.OnBoardingViewModel
import com.example.buzztoday.ui.theme.BuzzTodayTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var appEntryUseCases: AppEntryUseCases

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        lifecycleScope.launch {
            appEntryUseCases.readAppEntry().collect { Log.d("Test", "$it") }
        }
        enableEdgeToEdge()
        setContent {
            BuzzTodayApp()
        }
    }
}

@Composable
fun BuzzTodayApp() {
    BuzzTodayTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            val viewModel: OnBoardingViewModel = hiltViewModel()
            OnBoardingScreen(
                event = { viewModel.onEvent(it) }
            )
        }
    }
}

/*@Preview(showBackground = true)
@Composable
fun BuzzTodayAppPreview() {
    BuzzTodayApp()
}*/
