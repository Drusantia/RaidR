package net.drusantia.raidr.ui.base

import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.P
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import net.drusantia.raidr.utils.*
import org.koin.android.ext.android.inject

@Suppress("MemberVisibilityCanBePrivate")
abstract class BaseActivity : AppCompatActivity() {
    private val darkModeHelper: DarkModeHelper by inject()

    protected var darkMode = false
    private val permissionCallbacks = HashMap<Int, PermissionRequest>()

    fun onPermission(
        permission: String,
        onDeniedAction: (() -> Unit)? = null,
        onGrantedAction: () -> Unit
    ) {
        val requestCode = View.generateViewId()
        permissionCallbacks[requestCode] = PermissionRequest(permission, onDeniedAction, onGrantedAction)
        ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
    }

    override fun onResume() {
        super.onResume()
        if (SDK_INT >= P) {
            configureAutoDarkMode()
        } else {
            darkModeHelper.onModeChanged(darkMode, delegate)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionCallbacks[requestCode]?.let {
            if (checkSelfPermission(it.permission) == PackageManager.PERMISSION_GRANTED) {
                it.onGrantedAction()
            } else {
                it.onDeniedAction?.invoke()
            }
            permissionCallbacks.remove(requestCode)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        /*R.id.mnu_set_theme -> {
            darkMode = !darkMode
            sharedPref.edit {
                putBoolean("darkMode", darkMode)
                putInt("currentDataType", currentDataType)
            }
            darkModeHelper.onModeChanged(darkMode, delegate)
            true
        }*/
        else -> super.onOptionsItemSelected(item)
    }

    private fun configureAutoDarkMode() {
        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> darkMode = true
            Configuration.UI_MODE_NIGHT_NO -> darkMode = false
        }
    }

    private data class PermissionRequest(
        val permission: String,
        val onDeniedAction: (() -> Unit)?,
        val onGrantedAction: () -> Unit)
}