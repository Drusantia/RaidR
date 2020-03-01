package net.drusantia.raidr.data.repository

import com.dropbox.android.external.store4.MemoryPolicy
import kotlinx.coroutines.*
import net.drusantia.raidr.data.model.general.SearchResultWrapper
import net.drusantia.raidr.data.network.endpoint.RaiderIoGeneralApi
import net.drusantia.raidr.utils.*

@FlowPreview
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class RaiderIoGeneralRepository(
    cachePolicy: MemoryPolicy,
    private val apiClient: RaiderIoGeneralApi
) {
    private val searchStore by lazyStore<String, SearchResultWrapper>(cachePolicy) {
        apiClient.search(it)
    }

    suspend fun searchStream(key: String, result: StoreResult<SearchResultWrapper>) = searchStore.cachedStream(key, result)
}