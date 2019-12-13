package net.drusantia.raidr.data.network.model.character

enum class Region {
    US, EU, KR, TW;

    companion object {
        fun from(toParse: String) = values().first { it.name.equals(toParse, ignoreCase = true) }
    }
}