package net.drusantia.raidr.data.network.model.character

import com.google.gson.annotations.SerializedName

data class Profile(
    val name: String,
    val region: String,
    val realm: String,
    val race: String,
    val `class`: String,
    val gender: String,
    val faction: String,

    @SerializedName("active_spec_name")
    val activeSpecName: String,

    @SerializedName("active_spec_role")
    val activeSpecRole: String,

    @SerializedName("achievement_points")
    val achievementPoints: Int,

    @SerializedName("honorable_kills")
    val honorableKills: Int,

    @SerializedName("thumbnail_url")
    val thumbnailUrl: String,

    @SerializedName("profile_url")
    val profileUrl: String,

    @SerializedName("profile_banner")
    val profileBanner: String
)