package net.drusantia.raidr.application.di

import net.drusantia.raidr.utils.DarkModeHelper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val UtilKoinModule = module {
    single { DarkModeHelper(androidContext()) }
}