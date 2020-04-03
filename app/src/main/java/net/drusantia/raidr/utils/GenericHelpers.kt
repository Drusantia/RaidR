package net.drusantia.raidr.utils

import timber.log.Timber

fun noop(logMessage: String? = null) {
    if (logMessage != null) {
        Timber.d(logMessage)
    }
}