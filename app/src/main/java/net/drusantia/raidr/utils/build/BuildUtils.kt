package net.drusantia.raidr.utils.build

import android.os.Build

inline fun onDebug(body: () -> Unit) {
    if (Build.TYPE.equals("debug", ignoreCase = true)) {
        body()
    }
}