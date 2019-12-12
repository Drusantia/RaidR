package net.drusantia.raidr.application.di

import net.drusantia.raidr.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelKoinModule = module {
    viewModel { MainViewModel() }
}