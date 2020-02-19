package net.drusantia.raidr.data.repository

import com.dropbox.android.external.store4.*
import kotlinx.coroutines.*
import net.drusantia.raidr.data.model.character.PlayerCharacter
import net.drusantia.raidr.data.network.endpoint.RaiderIoCharacterApi
import net.drusantia.raidr.utils.lazyStore

@FlowPreview
@ExperimentalCoroutinesApi
class RaiderIoCharacterRepository(
    cachePolicy: MemoryPolicy,
    private val apiClient: RaiderIoCharacterApi
) {
    private val characterStore by lazyStore<RequestKeys, PlayerCharacter>(cachePolicy) {
        apiClient.getProfileAsync(name = it.name)
    }

    suspend fun getCharacter(key: RequestKeys = RequestKeys.FENROHAS) = characterStore.get(key)
    suspend fun getCharacterFresh(key: RequestKeys = RequestKeys.FENROHAS) = characterStore.fresh(key)

    enum class RequestKeys {
        FENROHAS,
        FENRO
    }
}