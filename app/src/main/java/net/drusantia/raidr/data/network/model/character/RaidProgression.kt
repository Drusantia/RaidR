package net.drusantia.raidr.data.network.model.character

import com.google.gson.annotations.SerializedName

data class RaidProgression (
    val summary : String,

    @SerializedName("total_bosses")
    val totalBosses : Int,

    @SerializedName("normal_bosses_killed")
    val normalKills : Int,

    @SerializedName("heroic_bosses_killed")
    val heroicKills : Int,

    @SerializedName("mythic_bosses_killed")
    val mythicKills : Int
)