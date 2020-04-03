package net.drusantia.raidr.utils.extensions

import kotlinx.coroutines.*

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
