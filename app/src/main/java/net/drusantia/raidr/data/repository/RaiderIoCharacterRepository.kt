package net.drusantia.raidr.data.repository

import com.dropbox.android.external.store4.*
import kotlinx.coroutines.*
import net.drusantia.raidr.data.model.character.PlayerCharacter
import net.drusantia.raidr.data.network.endpoint.RaiderIoCharacterApi
import timber.log.Timber

@FlowPreview
@ExperimentalCoroutinesApi
class RaiderIoCharacterRepository(
    private val apiClient: RaiderIoCharacterApi,
    private val cachePolicy: MemoryPolicy
) {
    private val characterStore by lazy {
        StoreBuilder
            .fromNonFlow<RequestKeys, PlayerCharacter> {
                Timber.i("Fetching: $it")
                apiClient.getProfileAsync(name = it.name)
            }
            .cachePolicy(cachePolicy)
            .build()
    }

    suspend fun getCharacter(key: RequestKeys = RequestKeys.FENROHAS) = characterStore.get(key)
    suspend fun getCharacterFresh(key: RequestKeys = RequestKeys.FENROHAS) = characterStore.fresh(key)

    enum class RequestKeys {
        FENROHAS,
        FENRO
    }
}