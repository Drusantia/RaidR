package net.drusantia.raidr.data.model.character

import com.squareup.moshi.*

@JsonClass(generateAdapter = true)
data class MythicPlusRank(
    val overall: MythicPlusRankingLevel,
    val tank: MythicPlusRankingLevel,
    val healer: MythicPlusRankingLevel,
    val dps: MythicPlusRankingLevel,

    @field:Json(name = "class")
    val `class`: MythicPlusRankingLevel,

    @field:Json(name = "class_tank")
    val classTanks: MythicPlusRankingLevel,

    @field:Json(name = "class_healer")
    val classHealer: MythicPlusRankingLevel,

    @field:Json(name = "class_dps")
    val classDps: MythicPlusRankingLevel
)