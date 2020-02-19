package net.drusantia.raidr.utils

import com.dropbox.android.external.store4.*
import kotlinx.coroutines.*
import timber.log.Timber

@FlowPreview
@ExperimentalCoroutinesApi
fun <K : Any, T : Any> lazyStore(
    memoryPolicy: MemoryPolicy? = null,
    request: suspend (K) -> T
) = lazy {
    StoreBuilder
        .fromNonFlow<K, T> { key ->
            Timber.i("Fetching: $key")
            withContext(Dispatchers.IO) {
                request(key)
            }
        }
        .cachePolicy(memoryPolicy)
        .build()
}