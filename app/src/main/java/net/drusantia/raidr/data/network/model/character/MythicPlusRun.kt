package net.drusantia.raidr.data.network.model.character

import com.google.gson.annotations.SerializedName

data class MythicPlusRun (
    val dungeon : String,
    val score : Double,
    val url : String,

    @SerializedName("short_name")
    val shortName : String,

    @SerializedName("mythic_level")
    val mythicLevel : Int,

    @SerializedName("completed_at")
    val completedAt : String,

    @SerializedName("clear_time_ms")
    val clearTimeInMilliseconds : Int,

    @SerializedName("num_keystone_upgrades")
    val keystoneLevelUpgrade : Int
)