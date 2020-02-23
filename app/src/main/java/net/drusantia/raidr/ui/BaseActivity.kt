package net.drusantia.raidr.ui

import android.content.res.Configuration
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.P
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import net.drusantia.raidr.utils.DarkModeHelper
import org.koin.android.ext.android.inject

@Suppress("MemberVisibilityCanBePrivate")
abstract class BaseActivity : AppCompatActivity() {
    private val darkModeHelper: DarkModeHelper by inject()
    protected var darkMode = false

    override fun onResume() {
        super.onResume()
        if (SDK_INT >= P) {
            configureAutoDarkMode()
        } else {
            darkModeHelper.onModeChanged(darkMode, delegate)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
//        R.id.mnu_set_theme -> {
//            darkMode = !darkMode
//            sharedPref.edit {
//                putBoolean("darkMode", darkMode)
//                putInt("currentDataType", currentDataType)
//            }
//            darkModeHelper.onModeChanged(darkMode, delegate)
//            true
//        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun configureAutoDarkMode() {
        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> darkMode = true
            Configuration.UI_MODE_NIGHT_NO -> darkMode = false
        }
    }
}