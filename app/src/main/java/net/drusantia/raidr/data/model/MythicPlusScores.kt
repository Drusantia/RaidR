package net.drusantia.raidr.data.model

import com.squareup.moshi.JsonClass

//@Entity(tableName = "MythicPlusScores")
@JsonClass(generateAdapter = true)
data class MythicPlusScores(
//    /** This field is only for Room */
//    @PrimaryKey(autoGenerate = true)
//    val roomId: Int,

    var all: Int = 0,
    var dps: Int = 0,
    var healer: Int = 0,
    var tank: Int = 0
)