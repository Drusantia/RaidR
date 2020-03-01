package net.drusantia.raidr.data.network.endpoint

import net.drusantia.raidr.data.model.general.SearchResultWrapper
import retrofit2.http.*

interface RaiderIoGeneralApi {
    @GET("search")
    suspend fun search(
        @Query("term") term: String
    ): SearchResultWrapper
}