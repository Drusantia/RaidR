package net.drusantia.raidr.data.network.model.character

import com.google.gson.annotations.SerializedName

data class Gear(
    @SerializedName("item_level_equipped")
    val itemLevelEquipped : Int,

    @SerializedName("item_level_total")
    val itemLevelTotal : Int,

    @SerializedName("artifact_traits")
    val artifactTraits : Int
)