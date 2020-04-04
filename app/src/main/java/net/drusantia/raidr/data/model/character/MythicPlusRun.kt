package net.drusantia.raidr.data.model.character

import android.content.Context
import com.squareup.moshi.*
import net.drusantia.raidr.R
import net.drusantia.raidr.utils.extensions.empty
import timber.log.Timber
import java.util.*

//@Entity(tableName = "MythicPlusRun")
@JsonClass(generateAdapter = true)
data class MythicPlusRun(
//    /** This field is only for Room */
//    @PrimaryKey(autoGenerate = true)
//    val roomId: Int,
    var dungeon: String = String.empty,
    var url: String = String.empty,
    @field:Json(name = "short_name")
    var shortName: String = String.empty,
    @field:Json(name = "mythic_level")
    var mythicLevel: Int = 0,
    @field:Json(name = "completed_at")
    var completedAt: String = String.empty,
    @field:Json(name = "clear_time_ms")
    var clearTimeInMilliseconds: Long = 0L,
    @field:Json(name = "num_keystone_upgrades")
    var keystoneLevelUpgrade: Int = 0,
    var score: Double = 0.0,
    var affixes: List<Affix> = listOf()
)

@JsonClass(generateAdapter = true)
data class Affix(
    var id: Int,
    var name: String = String.empty,
    var description: String = String.empty,
    @field:Json(name = "wowhead_url")
    var wowHeadUrl: String = String.empty
) {
    fun getDrawableId(context: Context) = context.resources.getIdentifier("ic_affix_${name.toLowerCase(Locale.getDefault())}", "drawable", context.packageName)
}