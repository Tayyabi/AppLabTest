package com.example.applabtest.presentation.ui

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import com.example.applabtest.core.utils.LocaleHelper
import com.example.applabtest.data.preferences.LanguagePreferences
import com.example.applabtest.presentation.theme.AppLabTestTheme
import com.example.applabtest.presentation.ui.home.HomeScreenRoot
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var languagePreferences: LanguagePreferences

    override fun attachBaseContext(newBase: Context?) {
        val context = newBase ?: return
        val languageCode = getLanguageFromPreferences(context)
        val updatedContext = LocaleHelper.setLocale(context, languageCode)
        super.attachBaseContext(updatedContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppLabTestTheme {
                HomeScreenRoot(
                    onLanguageChanged = { languageCode ->
                        // Save the new language and recreate activity
                        languagePreferences.setLanguage(languageCode)
                        recreate()
                    }
                )
            }
        }
    }

    private fun getLanguageFromPreferences(context: Context): String {
        val prefs = context.getSharedPreferences("language_preferences", Context.MODE_PRIVATE)
        return prefs.getString("selected_language", "EN") ?: "EN"
    }
}