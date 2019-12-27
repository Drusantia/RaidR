package net.drusantia.raidr.data.model.character

import com.google.gson.annotations.SerializedName
import io.objectbox.annotation.*
import net.drusantia.raidr.utils.extensions.empty
import java.util.*

//@Entity(
//    tableName = "PlayerCharacter",
//    primaryKeys = ["regionName", "realm", "name"],
//    ignoredColumns = ["playerClass", "activeSpec", "activeSpecRole", "faction", "region", "realmSlug"])
@Entity
data class PlayerCharacter(
    @Id
    var id: Long,

    var name: String = String.empty,
    var race: String = String.empty,
    var gender: String = String.empty,
    var realm: String = String.empty,

    @Backlink
    var gear: Gear = Gear(),

    @SerializedName("class")
    var className: String = String.empty,

    @SerializedName("active_spec_name")
    var activeSpecName: String = String.empty,

    @SerializedName("active_spec_role")
    var activeSpecRoleName: String = String.empty,

    @SerializedName("faction")
    var factionName: String = String.empty,

    @SerializedName("region")
    var regionName: String = String.empty,

    @SerializedName("profile_url")
    var profileUrl: String = String.empty,

    @SerializedName("raid_progression")
    var raidProgression: Map<String, RaidProgression> = mapOf(),

    @SerializedName("mythic_plus_ranks")
    var mythicPlusRanks: Map<String, MythicPlusRankingLevel> = mapOf(),

    @SerializedName("mythic_plus_scores")
    var mythicPlusScores: MythicPlusScores = MythicPlusScores(),

    @SerializedName("previous_mythic_plus_ranks")
    var previousMythicPlusRanks: Map<String, MythicPlusRankingLevel> = mapOf(),

    @SerializedName("previous_mythic_plus_scores")
    var previousMythicPlusScores: MythicPlusScores = MythicPlusScores(),

    @SerializedName("mythic_plus_recent_runs")
    var mythicPlusRecentRuns: List<MythicPlusRun> = listOf(),

    @SerializedName("mythic_plus_best_runs")
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
}