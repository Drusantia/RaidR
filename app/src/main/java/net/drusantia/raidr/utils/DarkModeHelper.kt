package net.drusantia.raidr.utils

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.*

class DarkModeHelper(
    private val context: Context
) {
    fun onModeChanged(newMode: Boolean, delegate: AppCompatDelegate) {
        setDefaultNightMode(if (newMode) MODE_NIGHT_YES else MODE_NIGHT_NO)
        delegate.applyDayNight()
    }
}