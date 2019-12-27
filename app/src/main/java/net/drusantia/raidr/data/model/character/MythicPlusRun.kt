package net.drusantia.raidr.data.model.character

import com.google.gson.annotations.SerializedName
import io.objectbox.annotation.*
import net.drusantia.raidr.utils.extensions.empty

//@Entity(tableName = "MythicPlusRun")
@Entity
data class MythicPlusRun (
//    /** This field is only for Room */
//    @PrimaryKey(autoGenerate = true)
//    val roomId: Int,

    @Id
    var id: Long = 0L,

    var dungeon : String = String.empty,
    var score : Double = 0.0,
    var url : String = String.empty,

    @SerializedName("short_name")
    var shortName : String = String.empty,

    @SerializedName("mythic_level")
    var mythicLevel : Int = 0,

    @SerializedName("completed_at")
    var completedAt : String = String.empty,

    @SerializedName("clear_time_ms")
    var clearTimeInMilliseconds : Int = 0,

    @SerializedName("num_keystone_upgrades")
    var keystoneLevelUpgrade : Int = 0
)