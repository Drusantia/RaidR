package net.drusantia.raidr.application.di

import net.drusantia.raidr.data.persistence.PlayerCharacterBox
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val PersistenceKoinModule = module {
    single(createdAtStart = true) { PlayerCharacterBox(androidContext()) }
}