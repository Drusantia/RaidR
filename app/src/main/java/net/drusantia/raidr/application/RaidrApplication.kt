package net.drusantia.raidr.application

import android.app.Application
import net.drusantia.raidr.application.di.ViewModelKoinModule
import net.drusantia.raidr.utils.build.onDebug
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber
import timber.log.Timber.DebugTree

class RaidrApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initLogging()
        initDependencies()
    }

    private fun initLogging() = onDebug { Timber.plant(DebugTree()) }
    private fun initDependencies() {
        startKoin {
            onDebug { printLogger(level = Level.INFO) }
            androidContext(this@RaidrApplication)
            modules(listOf(ViewModelKoinModule))
            //    single<HelloRepository> { HelloRepositoryImpl() }
            //    factory { MySimplePresenter(get()) }
            //    viewModel { MyViewModel(get()) }
        }
    }
}