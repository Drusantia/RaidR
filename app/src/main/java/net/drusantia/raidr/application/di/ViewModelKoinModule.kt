package net.drusantia.raidr.application.di

import kotlinx.coroutines.*
import net.drusantia.raidr.ui.main.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@FlowPreview
@ExperimentalCoroutinesApi
@InternalCoroutinesApi
val ViewModelKoinModule = module {
    viewModel { SearchViewModel() }
}