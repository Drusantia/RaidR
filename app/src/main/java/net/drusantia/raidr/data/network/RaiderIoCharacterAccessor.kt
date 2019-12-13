package net.drusantia.raidr.data.network

class RaiderIoCharacterAccessor(
    private val apiClient: RaiderIoCharacterApi
) {

    suspend fun getFenrohas() = apiClient.getProfileAsync()
}