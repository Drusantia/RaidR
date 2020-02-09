package net.drusantia.raidr.data.network.accessor

import net.drusantia.raidr.data.network.endpoint.RaiderIoCharacterApi

class RaiderIoCharacterAccessor(
    private val apiClient: RaiderIoCharacterApi
) {
    suspend fun getFenrohas() = apiClient.getProfileAsync()
}