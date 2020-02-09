package net.drusantia.raidr.utils.extensions

import android.content.pm.PackageManager
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.P

fun PackageManager.isApplicationWithPackageInstalled(packageName: String): InstalledPackageInfo = try {
    val applicationInfo = getApplicationInfo(packageName, PackageManager.GET_META_DATA)
    val packageInfo = getPackageInfo(packageName, 0)
    if (SDK_INT >= P)
        InstalledPackageInfo(true, applicationInfo.enabled, packageInfo.longVersionCode)
    else InstalledPackageInfo(true, applicationInfo.enabled, packageInfo.versionCode.toLong())
} catch (notInstalled: PackageManager.NameNotFoundException) {
    InstalledPackageInfo(isInstalled = false, isEnabled = false, versionCode = -1L)
}

data class InstalledPackageInfo(
        val isInstalled: Boolean,
        val isEnabled: Boolean,
        val versionCode: Long
)