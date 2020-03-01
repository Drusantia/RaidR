package net.drusantia.raidr.data.model.general

import com.squareup.moshi.Json

data class SearchResultWrapper(
    val matches: List<SearchResult>
)

data class SearchResult(
    val type: String,
    val name: String,
    val data: SearchDetails?
)

data class SearchDetails(
    val id: Int?,
    val name: String?,
    val faction: String?, // Character only
    val realm: SearchRealm,
    val region: SearchRegion,
    val `class`: SearchClass?, // Character only
    val path: String? // Guild only
)

data class SearchRealm(
    val id: Int,
    val connectedRealmId: Int,
    val name: String,
    val altName: String?,
    val slug: String,
    val altSlug: String?,
    val locale: String,
    val isConnected: Boolean
)

data class SearchRegion(
    val name: String,
    val slug: String,
    @Json(name = "short_name")
    val shortName: String
)

data class SearchClass(
    val id: Int,
    val name: String,
    val slug: String
)