@file:Suppress("unused")

package net.drusantia.raidr.data.model.character

import androidx.databinding.BindingAdapter
import com.squareup.moshi.*
import net.drusantia.raidr.utils.extensions.empty
import java.util.*

//@Entity(
//    tableName = "PlayerCharacter",
//    primaryKeys = ["regionName", "realm", "name"],
//    ignoredColumns = ["playerClass", "activeSpec", "activeSpecRole", "faction", "region", "realmSlug"])
@JsonClass(generateAdapter = true)
data class PlayerCharacter(
    var id: Long?,

    var name: String = String.empty,
    var race: String = String.empty,
    var gender: String = String.empty,
    var realm: String = String.empty,

    var gear: Gear,

    @field:Json(name = "class")
    var className: String = String.empty,

    @field:Json(name = "active_spec_name")
    var activeSpecName: String = String.empty,

    @field:Json(name = "active_spec_role")
    var activeSpecRoleName: String = String.empty,

    @field:Json(name = "faction")
    var factionName: String = String.empty,

    @field:Json(name = "region")
    var regionName: String = String.empty,

    @field:Json(name = "profile_url")
    var profileUrl: String = String.empty,

    @field:Json(name = "thumbnail_url")
    var profileImageUrl: String = String.empty,

    @field:Json(name = "raid_progression")
    var raidProgression: Map<String, RaidProgression> = mapOf(),

    @field:Json(name = "mythic_plus_ranks")
    var mythicPlusRanks: Map<String, MythicPlusRankingLevel> = mapOf(),

    @field:Json(name = "mythic_plus_scores")
    var mythicPlusScores: MythicPlusScores = MythicPlusScores(),

    @field:Json(name = "previous_mythic_plus_ranks")
    var previousMythicPlusRanks: Map<String, MythicPlusRankingLevel> = mapOf(),

    @field:Json(name = "previous_mythic_plus_scores")
    var previousMythicPlusScores: MythicPlusScores = MythicPlusScores(),

    @field:Json(name = "mythic_plus_recent_runs")
    var mythicPlusRecentRuns: List<MythicPlusRun> = listOf(),

    @field:Json(name = "mythic_plus_best_runs")
    var mythicPlusBestRuns: List<MythicPlusRun> = listOf()
) {
//    @delegate:Ignore
    @delegate:Transient
    val playerClass: PlayerClass by lazy { PlayerClass.fromString(className) }

//    @delegate:Ignore
    @delegate:Transient
    val activeSpec: PlayerSpecialization by lazy { PlayerSpecialization.fromString(activeSpecName, playerClass) }

//    @delegate:Ignore
    @delegate:Transient
    val activeSpecRole: Role by lazy { Role.from(activeSpecRoleName) }

//    @delegate:Ignore
    @delegate:Transient
    val faction: Faction by lazy { Faction.from(factionName) }

//    @delegate:Ignore
    @delegate:Transient
    val region: Region by lazy { Region.from(regionName) }

//    @delegate:Ignore
    @delegate:Transient
    val realmSlug: String by lazy { realm.replace(" ", "-").toLowerCase(Locale.ROOT) }

//    @PrimaryKey
//    @Id
    val path = "$regionName/$realm/$name"

    @delegate:Transient
    val currentScore: String by lazy { String.format("%.2f", mythicPlusBestRuns.sumByDouble { it.score })}
}