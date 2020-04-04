@file:SuppressLint("SimpleDateFormat")

package net.drusantia.raidr.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

val isoDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'")
val readableDateTimeFormat = SimpleDateFormat("yyyy.MM.dd. HH:mm")

fun Date.toReadableDateTime(): String = readableDateTimeFormat.format(this)