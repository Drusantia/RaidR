@file:Suppress("unused")

package net.drusantia.raidr.data.repository

import com.dropbox.android.external.store4.MemoryPolicy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import net.drusantia.raidr.data.model.PlayerCharacter
import net.drusantia.raidr.data.network.endpoint.RaiderIoCharacterApi
import net.drusantia.raidr.utils.StoreResult
import net.drusantia.raidr.utils.cachedStream
import net.drusantia.raidr.utils.lazyStore

@FlowPreview
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class RaiderIoCharacterRepository(
    cachePolicy: MemoryPolicy,
    private val apiClient: RaiderIoCharacterApi
) {
    private val characterStore by lazyStore<RequestKeys, PlayerCharacter>(cachePolicy) {
        apiClient.getProfileAsync(name = it.name)
    }

   suspend fun getCharacterStream(key: RequestKeys, result: StoreResult<PlayerCharacter>) = characterStore.cachedStream(key, result)

    enum class RequestKeys {
        FENROHAS,
        FENRO
    }
}
