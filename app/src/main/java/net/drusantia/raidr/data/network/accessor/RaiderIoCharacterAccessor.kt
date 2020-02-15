package net.drusantia.raidr.data.network.accessor

import com.dropbox.android.external.store4.*
import kotlinx.coroutines.*
import net.drusantia.raidr.data.model.character.PlayerCharacter
import net.drusantia.raidr.data.network.endpoint.RaiderIoCharacterApi

@ExperimentalCoroutinesApi
@UseExperimental(FlowPreview::class)
class RaiderIoCharacterAccessor(
    private val apiClient: RaiderIoCharacterApi,
    private val cachePolicy: MemoryPolicy
) {
//    suspend fun getFenrohas() = apiClient.getProfileAsync()

    suspend fun fenrohas() = StoreBuilder
        .fromNonFlow<Unit, PlayerCharacter> { apiClient.getProfileAsync() }
        .cachePolicy(cachePolicy)
        .build()
}