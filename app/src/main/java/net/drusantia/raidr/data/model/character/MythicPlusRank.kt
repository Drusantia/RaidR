package net.drusantia.raidr.data.model.character

import com.google.gson.annotations.SerializedName

data class MythicPlusRank(
    val overall: MythicPlusRankingLevel,
    val tank: MythicPlusRankingLevel,
    val healer: MythicPlusRankingLevel,
    val dps: MythicPlusRankingLevel,

    @SerializedName("class")
    val `class`: MythicPlusRankingLevel,

    @SerializedName("class_tank")
    val classTanks: MythicPlusRankingLevel,

    @SerializedName("class_healer")
    val classHealer: MythicPlusRankingLevel,

    @SerializedName("class_dps")
    val classDps: MythicPlusRankingLevel
)