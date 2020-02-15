package net.drusantia.raidr.application.di

import com.dropbox.android.external.store4.MemoryPolicy
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

val PersistenceKoinModule = module {
    single { provideStoreCachePolicy() }
}

fun provideStoreCachePolicy() = MemoryPolicy.MemoryPolicyBuilder()
    .setExpireAfterWrite(30)
    .setExpireAfterTimeUnit(TimeUnit.SECONDS)
    .build()
