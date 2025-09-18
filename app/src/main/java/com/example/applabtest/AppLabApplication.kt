package com.example.applabtest

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppLabApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}