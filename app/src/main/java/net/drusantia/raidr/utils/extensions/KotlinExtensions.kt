package net.drusantia.raidr.utils.extensions

import android.content.Context
import android.util.DisplayMetrics
import kotlinx.coroutines.*

fun Int.px2dp(context: Context): Int = run {
    val resources = context.resources
    val metrics = resources.displayMetrics
    (this / (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
}

fun <T> debounce(
    coroutineScope: CoroutineScope,
    waitMs: Long = 300L,
    action: (T) -> Unit
): (T) -> Unit {
    var debounceJob: Job? = null
    return { param: T ->
        debounceJob?.cancel()
        debounceJob = coroutineScope.launch {
            delay(waitMs)
            action(param)
        }
    }
}

fun <T> throttleFirst(
    coroutineScope: CoroutineScope,
    skipMs: Long = 300L,
    action: (T) -> Unit
): (T) -> Unit {
    var throttleJob: Job? = null
    return { param: T ->
        if (throttleJob?.isCompleted != false) {
            throttleJob = coroutineScope.launch {
                action(param)
                delay(skipMs)
            }
        }
    }
}

fun <T> throttleLatest(
    coroutineScope: CoroutineScope,
    intervalMs: Long = 300L,
    action: (T) -> Unit
): (T) -> Unit {
    var throttleJob: Job? = null
    var latestParam: T
    return { param: T ->
        latestParam = param
        if (throttleJob?.isCompleted != false) {
            throttleJob = coroutineScope.launch {
                delay(intervalMs)
                latestParam.let(action)
            }
        }
    }
}
