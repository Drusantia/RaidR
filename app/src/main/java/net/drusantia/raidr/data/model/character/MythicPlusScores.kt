package net.drusantia.raidr.data.model.character

import io.objectbox.annotation.*

//@Entity(tableName = "MythicPlusScores")
@Entity
data class MythicPlusScores(
//    /** This field is only for Room */
//    @PrimaryKey(autoGenerate = true)
//    val roomId: Int,

    @Id
    var id: Long = 0L,

    var all: Int = 0,
    var dps: Int = 0,
    var healer: Int = 0,
    var tank: Int = 0
)