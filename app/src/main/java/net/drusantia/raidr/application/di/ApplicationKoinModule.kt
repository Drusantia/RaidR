package net.drusantia.raidr.application.di

import com.google.gson.GsonBuilder
import net.drusantia.raidr.BuildConfig
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val ApplicationKoinModule = module {
    single { provideRaiderIoRetrofit() }
}

private fun provideRaiderIoRetrofit() = Retrofit
    .Builder()
    .baseUrl(BuildConfig.API_BASE_URL)
    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
    .build()
