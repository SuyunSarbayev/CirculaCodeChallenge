package com.example.impl.domain

import android.content.SharedPreferences
import javax.inject.Inject

class PokemonUpdateUseCase @Inject constructor(
    private val preferences: SharedPreferences,
) {
    fun initiateUpdateTime() {
        preferences.edit().putLong(KEY_PREFERENCES_MANAGER_TIMESTAMP, System.currentTimeMillis())
            .apply()
    }

    fun initiateCheckLastUpdateTime(): Boolean {
        return !preferences.contains(KEY_PREFERENCES_MANAGER_TIMESTAMP) ||
            System.currentTimeMillis() - preferences.getLong(
                KEY_PREFERENCES_MANAGER_TIMESTAMP,
                0L,
            ) >= KEY_PREFERENCES_FIVE_MINUTES
    }

    companion object {
        const val KEY_PREFERENCES_MANAGER_TIMESTAMP = "KEY_PREFERENCES_MANAGER_TIMESTAMP"
        const val KEY_PREFERENCES_FIVE_MINUTES = 300000
    }
}
