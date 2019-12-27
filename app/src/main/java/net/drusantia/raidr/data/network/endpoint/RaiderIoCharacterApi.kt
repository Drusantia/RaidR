package net.drusantia.raidr.data.network.endpoint

import net.drusantia.raidr.data.model.character.*
import retrofit2.http.*

interface RaiderIoCharacterApi {
    @GET("characters/profile")
    suspend fun getProfileAsync(
        @Query("region") region: String? = Region.EU.name,
        @Query("realm") realm: String? = "Ragnaros",
        @Query("name") name: String? = "Fenrohas",
        @Query("fields") fields: String? = Fields.ALL.getUrlParams()): PlayerCharacter
}