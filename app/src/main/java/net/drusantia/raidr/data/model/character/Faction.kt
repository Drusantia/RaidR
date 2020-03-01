@file:Suppress("unused")

package net.drusantia.raidr.data.model.character

enum class Faction {
    Alliance,
    Horde;

    companion object {
        fun from(toParse: String) = values().first { it.name.equals(toParse, ignoreCase = true) }
    }
}