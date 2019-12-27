package net.drusantia.raidr.data.model.character

import io.objectbox.annotation.*

//@Entity(tableName = "MythicPlusRankingLevel")
@Entity
data class MythicPlusRankingLevel(
//    /** This field is only for Room */
//    @PrimaryKey(autoGenerate = true)
//    val roomId: Int,

    @Id
    var id: Long = 0L,

    var world: Int= 0,
    var region: Int= 0,
    var realm: Int= 0
)