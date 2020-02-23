package net.drusantia.raidr.data.model

import com.squareup.moshi.*
import net.drusantia.raidr.utils.extensions.empty

//@Entity(tableName = "MythicPlusRun")
@JsonClass(generateAdapter = true)
data class MythicPlusRun (
//    /** This field is only for Room */
//    @PrimaryKey(autoGenerate = true)
//    val roomId: Int,

    var dungeon : String = String.empty,
    var score : Double = 0.0,
    var url : String = String.empty,

    @field:Json(name = "short_name")
    var shortName : String = String.empty,

    @field:Json(name = "mythic_level")
    var mythicLevel : Int = 0,

    @field:Json(name = "completed_at")
    var completedAt : String = String.empty,

    @field:Json(name = "clear_time_ms")
    var clearTimeInMilliseconds : Int = 0,

    @field:Json(name = "num_keystone_upgrades")
    var keystoneLevelUpgrade : Int = 0
)