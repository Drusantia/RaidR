package net.drusantia.raidr.data.network

import net.drusantia.raidr.data.network.model.character.*
import retrofit2.http.*

interface RaiderIoCharacterApi {
    @GET("characters/profile")
    suspend fun getProfile(
        @Query("region") region: String? = Region.EU.name,
        @Query("realm") realm: String? = "Ragnaros",
        @Query("name") name: String? = "Fenrohas",
        @Query("fields") fields: String? = Fields.ALL.getUrlParams())
}

interface RaiderIoGuildApi {}
interface RaiderIoMythicPlusApi {}
interface RaiderIoRaidingApi {}