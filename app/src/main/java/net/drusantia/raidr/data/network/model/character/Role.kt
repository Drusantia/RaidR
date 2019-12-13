package net.drusantia.raidr.data.network.model.character

enum class Role {
    TANK, HEALER, DPS;

    companion object {
        fun from(toParse: String) = values().first { it.name.equals(toParse, ignoreCase = true) }
    }
}