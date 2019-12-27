package net.drusantia.raidr.utils.extensions

import android.content.Intent

@Suppress("UNCHECKED_CAST")
fun <T> Intent.getExtra(key: String, fallbackValue: T? = null) = extras?.run {
    if (hasExtra(key)) get(key) as T
    else fallbackValue
} ?: fallbackValue

//fun Intent.getExtraString(key: String, fallbackValue: String = String.empty) = extras?.run {
//    if (hasExtra(key)) getString(key, fallbackValue)
//    else fallbackValue
//} ?: fallbackValue
//
//fun Intent.getExtraInt(key: String, fallbackValue: Int = -1) = extras?.run {
//    if (hasExtra(key)) getInt(key, fallbackValue)
//    else fallbackValue
//} ?: fallbackValue
//
//fun Intent.getExtraObject(key: String, fallbackValue: Any? = null) = extras?.run {
//    if (hasExtra(key)) get(key)
//    else fallbackValue
//} ?: fallbackValue
