package net.drusantia.raidr.data.network.endpoint

import net.drusantia.raidr.data.model.character.Fields
import net.drusantia.raidr.data.model.character.PlayerCharacter
import net.drusantia.raidr.data.model.character.Region
import retrofit2.http.*

interface RaiderIoCharacterApi {
    @GET("v1/characters/profile")
    suspend fun getProfileAsync(
        @Query("region") region: String? = Region.EU.name,
        @Query("realm") realm: String? = "Ragnaros",
        @Query("name") name: String? = "Fenrohas",
        @Query("fields") fields: String? = Fields.ALL.getUrlParams()
    ): PlayerCharacter
}