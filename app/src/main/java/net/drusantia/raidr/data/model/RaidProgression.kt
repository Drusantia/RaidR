package net.drusantia.raidr.data.model

import com.squareup.moshi.*
import net.drusantia.raidr.utils.extensions.empty

//@Entity(tableName = "RaidProgression")
@JsonClass(generateAdapter = true)
data class RaidProgression (
//    /** This field is only for Room */
//    @PrimaryKey(autoGenerate = true)
//    val roomId: Int,

    var summary : String = String.empty,

    @field:Json(name = "total_bosses")
    var totalBosses : Int = 0,

    @field:Json(name = "normal_bosses_killed")
    var normalKills : Int = 0,

    @field:Json(name = "heroic_bosses_killed")
    var heroicKills : Int = 0,

    @field:Json(name = "mythic_bosses_killed")
    var mythicKills : Int = 0
)