package net.drusantia.raidr.application.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.*
import net.drusantia.raidr.BuildConfig
import net.drusantia.raidr.data.network.*
import net.drusantia.raidr.data.network.endpoint.*
import net.drusantia.raidr.data.repository.*
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber

@FlowPreview
@ExperimentalCoroutinesApi
val NetworkKoinModule = module {
    single { NetworkAvailabilityListener(androidContext()) }
    single { provideOkHttpClient(get()) }
    single { provideMoshi() }
    single { provideRaiderIoRetrofit(get(), get()) }

    single { provideRaiderIoCharacterApi(get()) }
    single { provideRaiderIoGuildApi(get()) }
    single { provideRaiderIoMythicPlusApi(get()) }
    single { provideRaiderIoRaidingApi(get()) }

    single { RaiderIoCharacterRepository(get(), get()) }
    single { RaiderIoGuildRepository(get()) }
    single { RaiderIoMythicPlusRepository(get()) }
    single { RaiderIoRaidingRepository(get()) }
}

private fun provideOkHttpClient(nal: NetworkAvailabilityListener): OkHttpClient = OkHttpClient()
    .newBuilder()
    .addNetworkInterceptor(StethoInterceptor())
    .addInterceptor(NetworkAvailabilityInterceptor(
        isNetworkAvailable = { nal.isConnected() },
        onInternetUnavailable = { Timber.e("Internet connection not available.") }))
    .addInterceptor(AuthInterceptor())
    .build()

private fun provideMoshi() = Moshi
    .Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private fun provideRaiderIoRetrofit(client: OkHttpClient, moshi: Moshi) = Retrofit
    .Builder()
    .baseUrl(BuildConfig.API_BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .client(client)
    .build()

private fun provideRaiderIoCharacterApi(retrofit: Retrofit): RaiderIoCharacterApi = retrofit.create(RaiderIoCharacterApi::class.java)
private fun provideRaiderIoGuildApi(retrofit: Retrofit): RaiderIoGuildApi = retrofit.create(RaiderIoGuildApi::class.java)
private fun provideRaiderIoMythicPlusApi(retrofit: Retrofit): RaiderIoMythicPlusApi = retrofit.create(RaiderIoMythicPlusApi::class.java)
private fun provideRaiderIoRaidingApi(retrofit: Retrofit): RaiderIoRaidingApi = retrofit.create(RaiderIoRaidingApi::class.java)