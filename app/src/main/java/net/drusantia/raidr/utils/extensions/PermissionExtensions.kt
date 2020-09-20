package net.drusantia.raidr.utils.extensions

import net.drusantia.raidr.ui.base.BaseActivity

fun BaseActivity.onWriteStoragePermission(onGranted: () -> Unit) = onPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) {
    onGranted()
}