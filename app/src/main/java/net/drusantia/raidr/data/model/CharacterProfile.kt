package net.drusantia.raidr.data.model

//import androidx.room.*
//import com.google.gson.annotations.SerializedName
//import java.util.*
//
//@Entity(
//    tableName = "CharacterProfile",
//    primaryKeys = ["region", "realm", "name"]
//)
//data class CharacterProfile(
//    val name: String,
//    val region: String,
//    val realm: String,
//    val race: String,
//    val gender: String,
//    val faction: String,
//
//    @SerializedName("class")
//    val className: String,
//
//    @SerializedName("active_spec_name")
//    val activeSpecName: String,
//
//    @SerializedName("active_spec_role")
//    val activeSpecRole: String,
//
//    @SerializedName("achievement_points")
//    val achievementPoints: Int,
//
//    @SerializedName("honorable_kills")
//    val honorableKills: Int,
//
//    @SerializedName("thumbnail_url")
//    val thumbnailUrl: String,
//
//    @SerializedName("profile_url")
//    val profileUrl: String,
//
//    @SerializedName("profile_banner")
//    val profileBanner: String
//) {
//    val profileId: String
//        get() = "${region}:${realm}:${name}".toLowerCase(Locale.ROOT)
//}