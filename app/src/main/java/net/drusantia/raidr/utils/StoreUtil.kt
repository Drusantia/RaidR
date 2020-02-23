@file:JvmName("StoreUtil")

package net.drusantia.raidr.utils

import com.dropbox.android.external.store4.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import retrofit2.HttpException
import timber.log.Timber

data class StoreResult<Output : Any>(
    val onFinished: (Output) -> Unit,
    val onLoading: (() -> Unit)? = null,
    val onError: ((Throwable) -> Unit)? = null
)

@FlowPreview
@ExperimentalCoroutinesApi
fun <Key : Any, Output : Any> lazyStore(
    memoryPolicy: MemoryPolicy? = null,
    request: suspend (Key) -> Output
) = lazy {
    StoreBuilder
        .fromNonFlow<Key, Output> { key ->
            Timber.i("Fetching from Network: $key")
            withContext(Dispatchers.IO) {
                request(key)
            }
        }
        .cachePolicy(memoryPolicy)
        .build()
}

@InternalCoroutinesApi
suspend fun <Key : Any, Output : Any> Store<Key, Output>.cachedStream(
    key: Key,
    result: StoreResult<Output>,
    refresh: Boolean = false
) = result.run {
    return@run cachedStream(key, refresh, onLoading, onError, onFinished)
}

@InternalCoroutinesApi
suspend fun <Key : Any, Output : Any> Store<Key, Output>.cachedStream(
    key: Key,
    refresh: Boolean = false,
    onLoading: (() -> Unit)? = null,
    onError: ((Throwable) -> Unit)? = null,
    onFinished: (Output) -> Unit
) = stream(StoreRequest.cached(key, refresh)).collect {
    when (it) {
        is StoreResponse.Loading -> {
            Timber.i("Requesting from Store: $key")
            onLoading?.invoke()
        }
        is StoreResponse.Error -> {
            Timber.e(it.error)
            onError?.invoke(it.error)
        }
        is StoreResponse.Data -> {
            Timber.i("Loaded from Store: $key")
            onFinished.invoke(it.value)
        }
    }
}
