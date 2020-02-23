@file:Suppress("unused")

package net.drusantia.raidr.data.model

import com.squareup.moshi.*
import net.drusantia.raidr.data.network.adapter.IValueEnum

//@Entity(
//    tableName = "Gear",
//    foreignKeys = [
//        ForeignKey(
//            entity = PlayerCharacter::class,
//            parentColumns = ["roomId"],
//            childColumns = ["gear"],
//            onDelete = ForeignKey.CASCADE)
//    ])
@JsonClass(generateAdapter = true)
data class Gear(
//    /** This field is only for Room */
//    @PrimaryKey(autoGenerate = true)
//    val roomId: Int,
    @field:Json(name = "item_level_equipped")
    val itemLevelEquipped: Double,
    @field:Json(name = "item_level_total")
    val itemLevelTotal: Double,
    @field:Json(name = "artifact_traits")
    val artifactTraits: Double,
    val corruption: Corruption?,
    val items: Equipment
)

@JsonClass(generateAdapter = true)
data class Corruption(
    val added: Int,
    val cloakRank: Int?, // only on Gear object
    val resisted: Int,
    val total: Int,
    val spells: List<Spell>? // only on Gear object
)

@JsonClass(generateAdapter = true)
data class Spell(
    val icon: String,
    val id: Int,
    val name: String,
    val rank: String?, // TODO always null, may be Int?
    val school: Int // TODO probably spell school enum
)

@JsonClass(generateAdapter = true)
data class Equipment(
    val back: GearPiece,
    val chest: GearPiece,
    val feet: GearPiece,
    val finger1: GearPiece,
    val finger2: GearPiece,
    val hands: GearPiece,
    val head: GearPiece,
    val legs: GearPiece,
    @field:Json(name = "mainhand")
    val mainHand: GearPiece,
    val neck: GearPiece,
    @field:Json(name = "offhand")
    val offHand: GearPiece,
    val shoulder: GearPiece,
    val trinket1: GearPiece,
    val trinket2: GearPiece,
    val waist: GearPiece,
    val wrist: GearPiece
)

@JsonClass(generateAdapter = true)
data class GearPiece(
    @field:Json(name = "azerite_powers")
    val azeritePowers: List<AzeritePower>,
    val bonuses: List<Int>,
    val corruption: Corruption,
    val enchant: Int?,
    val gems: List<Int>,
    @field:Json(name = "heart_of_azeroth")
    val heartOfAzeroth: HeartOfAzeroth?,
    @field:Json(name = "is_azerite_armor")
    val isAzeriteArmor: Boolean,
    @field:Json(name = "is_legion_legendary")
    val isLegionLegendary: Boolean,
    @field:Json(name = "item_id")
    val itemId: Int,
    @field:Json(name = "item_level")
    val itemLevel: Int,
    @field:Json(name = "item_quality")
    val itemQuality: ItemQuality
)

@JsonClass(generateAdapter = true)
data class AzeritePower(
    val id: Int,
    val spell: Spell,
    val tier: Int
)

@JsonClass(generateAdapter = true)
data class HeartOfAzeroth(
    val essences: List<HeartOfAzerothEssence>,
    val level: Int,
    val progress: Double // <=100, % op current necklace XP
)

@JsonClass(generateAdapter = true)
data class HeartOfAzerothEssence(
    val id: Int,
    val power: HeartOfAzerothPower,
    val rank: Int,
    val slot: Int
)

@JsonClass(generateAdapter = true)
data class HeartOfAzerothPower(
    val essence: Essence,
    val id: Int,
    val majorPowerSpell: Spell,
    val minorPowerSpell: Spell,
    val rank: Int?,
    val slot: Int?
)

@JsonClass(generateAdapter = true)
data class Essence(
    val description: String,
    val id: Int,
    val name: String,
    val shortName: String
)

enum class ItemQuality : IValueEnum {
    Poor { override val value: Int get() = 0 },
    Common { override val value: Int get() = 1},
    Uncommon { override val value: Int get() = 2 },
    Rare { override val value: Int get() = 3 },
    Epic { override val value: Int get() = 4 },
    Legendary { override val value: Int get() = 5 },
    Artifact { override val value: Int get() = 6 },
    Heirloom { override val value: Int get() = 7 },
    WoWToken { override val value: Int get() = 8 };

    companion object {
        fun from(toParse: String) = values().first { it.name.equals(toParse, ignoreCase = true) }
    }
}