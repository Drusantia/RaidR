package net.drusantia.raidr.data.model.character

import com.google.gson.annotations.SerializedName
import io.objectbox.annotation.*

//@Entity(
//    tableName = "Gear",
//    foreignKeys = [
//        ForeignKey(
//            entity = PlayerCharacter::class,
//            parentColumns = ["roomId"],
//            childColumns = ["gear"],
//            onDelete = ForeignKey.CASCADE)
//    ])
@Entity
data class Gear(
//    /** This field is only for Room */
//    @PrimaryKey(autoGenerate = true)
//    val roomId: Int,

    @Id
    var id: Long = 0L,

    @SerializedName("item_level_equipped")
    var itemLevelEquipped : Double = 0.0,

    @SerializedName("item_level_total")
    var itemLevelTotal : Double = 0.0,

    @SerializedName("artifact_traits")
    var artifactTraits : Int = 0
)