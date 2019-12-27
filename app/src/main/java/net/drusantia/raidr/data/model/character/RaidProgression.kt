package net.drusantia.raidr.data.model.character

import com.google.gson.annotations.SerializedName
import io.objectbox.annotation.*
import net.drusantia.raidr.utils.extensions.empty

//@Entity(tableName = "RaidProgression")
@Entity
data class RaidProgression (
//    /** This field is only for Room */
//    @PrimaryKey(autoGenerate = true)
//    val roomId: Int,

    @Id
    var id: Long = 0L,

    var summary : String = String.empty,

    @SerializedName("total_bosses")
    var totalBosses : Int = 0,

    @SerializedName("normal_bosses_killed")
    var normalKills : Int = 0,

    @SerializedName("heroic_bosses_killed")
    var heroicKills : Int = 0,

    @SerializedName("mythic_bosses_killed")
    var mythicKills : Int = 0
)