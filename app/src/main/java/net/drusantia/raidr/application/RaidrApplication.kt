package net.drusantia.raidr.application

import android.app.Application
import com.facebook.stetho.Stetho
import net.drusantia.raidr.application.di.*
import net.drusantia.raidr.utils.build.onDebug
import org.koin.android.ext.koin.*
import org.koin.core.context.startKoin
import timber.log.Timber
import timber.log.Timber.DebugTree

class RaidrApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initDebugTools()
        initDependencies()
    }

    private fun initDebugTools() = onDebug {
        Timber.plant(DebugTree())
        Stetho.initializeWithDefaults(this)
    }

    private fun initDependencies() {
        startKoin {
            onDebug {
                androidLogger()
            }
            androidContext(this@RaidrApplication)
            modules(listOf(ViewModelKoinModule, PersistenceKoinModule, NetworkKoinModule, UtilKoinModule))
        }
    }
}