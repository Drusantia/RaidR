package net.drusantia.raidr.data.network.model.character

import com.google.gson.annotations.SerializedName
import java.util.*

data class Character(
    val name: String,
    val race: String,
    val gender: String,
    val realm: String,
    val gear: Gear,

    @SerializedName("class")
    val className: String,

    @SerializedName("active_spec_name")
    val activeSpecName: String,

    @SerializedName("active_spec_role")
    val activeSpecRoleName: String,

    @SerializedName("faction")
    val factionName: String,

    @SerializedName("region")
    val regionName: String,

    @SerializedName("profile_url")
    val profileUrl: String,

    @SerializedName("raid_progression")
    val raidProgression: Map<String, RaidProgression>,

    @SerializedName("mythic_plus_ranks")
    val mythicPlusRanks: Map<String, MythicPlusRankingLevel>,

    @SerializedName("mythic_plus_scores")
    val mythicPlusScores: MythicPlusScores,

    @SerializedName("previous_mythic_plus_ranks")
    val previousMythicPlusRanks: Map<String, MythicPlusRankingLevel>,

    @SerializedName("previous_mythic_plus_scores")
    val previousMythicPlusScores: MythicPlusScores,

    @SerializedName("mythic_plus_recent_runs")
    val mythicPlusRecentRuns: List<MythicPlusRun>,

    @SerializedName("mythic_plus_best_runs")
    val mythicPlusBestRuns: List<MythicPlusRun>
) {
    val `class`: PlayerClass by lazy { PlayerClass.fromString(className) }
    val activeSpec: PlayerSpecialization by lazy { PlayerSpecialization.fromString(activeSpecName, `class`) }
    val activeSpecRole: Role by lazy { Role.from(activeSpecRoleName) }
    val faction: Faction by lazy { Faction.from(factionName) }
    val region: Region by lazy { Region.from(regionName) }
    val realmSlug: String by lazy { realm.replace(" ", "-").toLowerCase(Locale.ROOT) }
}