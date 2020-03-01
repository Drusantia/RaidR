@file:Suppress("unused")

package net.drusantia.raidr.data.repository

import com.dropbox.android.external.store4.MemoryPolicy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import net.drusantia.raidr.data.model.character.PlayerCharacter
import net.drusantia.raidr.data.network.endpoint.*
import net.drusantia.raidr.data.network.requestmodel.CharacterRequest
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
    private val characterStore by lazyStore<CharacterRequest, PlayerCharacter>(cachePolicy) {
        apiClient.getProfileAsync(it.region, it.realm, it.name)
    }

   suspend fun getCharacterStream(key: CharacterRequest, result: StoreResult<PlayerCharacter>) = characterStore.cachedStream(key, result)
}