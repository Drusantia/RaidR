package net.drusantia.raidr.application

import android.app.Application
import com.facebook.stetho.Stetho
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import net.drusantia.raidr.application.di.NetworkKoinModule
import net.drusantia.raidr.application.di.PersistenceKoinModule
import net.drusantia.raidr.application.di.UtilKoinModule
import net.drusantia.raidr.application.di.ViewModelKoinModule
import net.drusantia.raidr.utils.build.onDebug
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber
import timber.log.Timber.DebugTree

@FlowPreview
@Suppress("unused")
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class RaidrApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initDebugTools()
        initDependencies()
    }

    private fun initDebugTools() = onDebug {
        Timber.plant(DebugTree())
        Stetho.initializeWithDefaults(this)
        registerActivityLifecycleCallbacks(ActivityLifecycleLogCallback())
    }

    private fun initDependencies() {
        startKoin {
            onDebug { androidLogger() }
            androidContext(this@RaidrApplication)
            modules(listOf(ViewModelKoinModule, PersistenceKoinModule, NetworkKoinModule, UtilKoinModule))
        }
    }
}