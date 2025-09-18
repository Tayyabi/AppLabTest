package com.example.applabtest.domain.usecase

import com.example.applabtest.data.local.preferences.LanguagePreferences
import javax.inject.Inject

class GetLanguagePreferenceUseCase @Inject constructor(
    private val languagePreferences: LanguagePreferences
) {
    operator fun invoke(): String {
        return languagePreferences.getLanguage()
    }
}