package net.drusantia.raidr.data.model

import com.squareup.moshi.JsonClass

//@Entity(tableName = "MythicPlusRankingLevel")
@JsonClass(generateAdapter = true)
data class MythicPlusRankingLevel(
//    /** This field is only for Room */
//    @PrimaryKey(autoGenerate = true)
//    val roomId: Int,

    var world: Int= 0,
    var region: Int= 0,
    var realm: Int= 0
)