package com.example.buzztoday.presentation.home

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun Home(){
    val viewModel: HomeViewModel = hiltViewModel()
}