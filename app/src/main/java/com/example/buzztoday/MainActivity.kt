package com.example.buzztoday

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.buzztoday.ui.Home
import com.example.buzztoday.ui.NewsViewModel
import com.example.buzztoday.ui.theme.BuzzTodayTheme
import com.example.buzztoday.ui.theme.parkinsan

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val newsViewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        setContent {
            BuzzTodayTheme {
                Scaffold(modifier = Modifier.height(110.dp).fillMaxSize(), topBar = {
                    TopAppBar(modifier = Modifier
                        .height(110.dp)
                        .padding(), title = {
                        Text(
                            modifier = Modifier.padding(start = 95.dp),
                            text = "BuzzToday",
                            color = Color(0xFF3C215E),
                            fontSize = 40.sp,
                            fontFamily = parkinsan,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                        )
                    })
                }) {
                    Home(newsViewModel)
                }
            }
        }
    }
}
