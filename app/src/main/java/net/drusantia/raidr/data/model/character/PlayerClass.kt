package net.drusantia.raidr.data.model.character

import net.drusantia.raidr.data.model.character.PlayerSpecialization.*

enum class PlayerClass {
    DeathKnight {
        override fun getSpecializations(): List<PlayerSpecialization> = listOf(Blood, FrostDeathKnight, Unholy)
        override fun toString() = "Death Knight"
    },
    DemonHunter {
        override fun getSpecializations(): List<PlayerSpecialization> = listOf(Havoc, Vengeance)
        override fun toString() = "Demon Hunter"
    },
    Druid {
        override fun getSpecializations(): List<PlayerSpecialization> = listOf(Balance, Feral, Guardian, RestorationDruid)
    },
    Hunter {
        override fun getSpecializations(): List<PlayerSpecialization> = listOf(BeastMastery, Marksmanship, Survival)
    },
    Mage {
        override fun getSpecializations(): List<PlayerSpecialization> = listOf(Arcane, Fire, FrostMage)
    },
    Monk {
        override fun getSpecializations(): List<PlayerSpecialization> = listOf(BrewMaster, MistWeaver, WindWalker)
    },
    Paladin {
        override fun getSpecializations(): List<PlayerSpecialization> = listOf(HolyPaladin, ProtectionPaladin, Retribution)
    },
    Priest {
        override fun getSpecializations(): List<PlayerSpecialization> = listOf(Discipline, HolyPriest, Shadow)
    },
    Rogue {
        override fun getSpecializations(): List<PlayerSpecialization> = listOf(Assassination, Outlaw, Subtlety)
    },
    Shaman {
        override fun getSpecializations(): List<PlayerSpecialization> = listOf(Elemental, Enhancement, RestorationShaman)
    },
    Warlock {
        override fun getSpecializations(): List<PlayerSpecialization> = listOf(Affliction, Demonology, Destruction)
    },
    Warrior {
        override fun getSpecializations(): List<PlayerSpecialization> = listOf(Arms, Fury, ProtectionWarrior)
    };

    companion object {
        fun fromString(toParse: String): PlayerClass = values().first {
            it.name.equals(toParse, ignoreCase = true) || "$it".equals(toParse, ignoreCase = true)
        }
    }

    abstract fun getSpecializations(): List<PlayerSpecialization>
}