package com.example.applabtest.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import com.example.applabtest.presentation.theme.AppLabTestTheme
import com.example.applabtest.presentation.ui.home.HomeScreenRoot

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppLabTestTheme {
                HomeScreenRoot()
            }
        }
    }
}