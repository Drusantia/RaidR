package net.drusantia.raidr.data.model.character

import com.squareup.moshi.*

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
    var itemLevelEquipped: Double = 0.0,

    @field:Json(name = "item_level_total")
    var itemLevelTotal: Double = 0.0,

    @field:Json(name = "artifact_traits")
    var artifactTraits: Double = 0.0
)