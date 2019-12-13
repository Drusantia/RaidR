package net.drusantia.raidr.utils.build

import net.drusantia.raidr.BuildConfig

inline fun onDebug(body: () -> Unit) {
    if (BuildConfig.DEBUG) {
        body()
    }
}