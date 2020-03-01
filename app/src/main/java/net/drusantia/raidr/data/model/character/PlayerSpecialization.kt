package net.drusantia.raidr.data.model.character

enum class PlayerSpecialization {
    Blood {
        override fun getPlayerClass() = PlayerClass.DeathKnight
    },
    FrostDeathKnight {
        override fun getPlayerClass() = PlayerClass.DeathKnight
        override fun toString() = "Frost"
    },
    Unholy {
        override fun getPlayerClass() = PlayerClass.DeathKnight
    },

    Arcane {
        override fun getPlayerClass() = PlayerClass.Mage
    },
    Fire {
        override fun getPlayerClass() = PlayerClass.Mage
    },
    FrostMage {
        override fun getPlayerClass() = PlayerClass.Mage
        override fun toString() = "Frost"
    },

    Assassination {
        override fun getPlayerClass() = PlayerClass.Rogue
    },
    Outlaw {
        override fun getPlayerClass() = PlayerClass.Rogue
    },
    Subtlety {
        override fun getPlayerClass() = PlayerClass.Rogue
    },

    Havoc {
        override fun getPlayerClass() = PlayerClass.DemonHunter
    },
    Vengeance {
        override fun getPlayerClass() = PlayerClass.DemonHunter
    },

    BrewMaster {
        override fun getPlayerClass() = PlayerClass.Monk
    },
    MistWeaver {
        override fun getPlayerClass() = PlayerClass.Monk
    },
    WindWalker {
        override fun getPlayerClass() = PlayerClass.Monk
    },

    Elemental {
        override fun getPlayerClass() = PlayerClass.Shaman
    },
    Enhancement {
        override fun getPlayerClass() = PlayerClass.Shaman
    },
    RestorationShaman {
        override fun getPlayerClass() = PlayerClass.Shaman
        override fun toString() = "Restoration"
    },

    Balance {
        override fun getPlayerClass() = PlayerClass.Druid
    },
    Feral {
        override fun getPlayerClass() = PlayerClass.Druid
    },
    Guardian {
        override fun getPlayerClass() = PlayerClass.Druid
    },
    RestorationDruid {
        override fun getPlayerClass() = PlayerClass.Druid
        override fun toString() = "Restoration"
    },

    HolyPaladin {
        override fun getPlayerClass() = PlayerClass.Paladin
        override fun toString() = "Holy"
    },
    ProtectionPaladin {
        override fun getPlayerClass() = PlayerClass.Paladin
        override fun toString() = "Protection"
    },
    Retribution {
        override fun getPlayerClass() = PlayerClass.Paladin
    },

    Affliction {
        override fun getPlayerClass() = PlayerClass.Warlock
    },
    Demonology {
        override fun getPlayerClass() = PlayerClass.Warlock
    },
    Destruction {
        override fun getPlayerClass() = PlayerClass.Warlock
    },

    BeastMastery {
        override fun getPlayerClass() = PlayerClass.Hunter
    },
    Marksmanship {
        override fun getPlayerClass() = PlayerClass.Hunter
    },
    Survival {
        override fun getPlayerClass() = PlayerClass.Hunter
    },

    Discipline {
        override fun getPlayerClass() = PlayerClass.Priest
    },
    HolyPriest {
        override fun getPlayerClass() = PlayerClass.Priest
        override fun toString() = "Holy"
    },
    Shadow {
        override fun getPlayerClass() = PlayerClass.Priest
    },

    Arms {
        override fun getPlayerClass() = PlayerClass.Warrior
    },
    Fury {
        override fun getPlayerClass() = PlayerClass.Warrior
    },
    ProtectionWarrior {
        override fun getPlayerClass() = PlayerClass.Warrior
        override fun toString() = "Protection"
    };

    companion object {
        fun fromString(toParse: String, `class`: PlayerClass): PlayerSpecialization = values().first {
            it.name.equals(toParse, ignoreCase = true)
                || "$it".run {
                return@run (contains(toParse, ignoreCase = true)
                    && contains(`class`.name, ignoreCase = true))
            }
        }
    }

    abstract fun getPlayerClass(): PlayerClass
}