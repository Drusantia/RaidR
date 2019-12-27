package net.drusantia.raidr.application.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import net.drusantia.raidr.BuildConfig
import net.drusantia.raidr.data.network.AuthInterceptor
import net.drusantia.raidr.data.network.accessor.*
import net.drusantia.raidr.data.network.endpoint.*
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val NetworkKoinModule = module {
    single { provideOkHttpClient() }
    single { provideRaiderIoRetrofit(get()) }

    single { provideRaiderIoCharacterApi(get()) }
    single { provideRaiderIoGuildApi(get()) }
    single { provideRaiderIoMythicPlusApi(get()) }
    single { provideRaiderIoRaidingApi(get()) }

    single { RaiderIoCharacterAccessor(get()) }
    single { RaiderIoGuildAccessor(get()) }
    single { RaiderIoMythicPlusAccessor(get()) }
    single { RaiderIoRaidingAccessor(get()) }
}

private fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient()
        .newBuilder()
        .addNetworkInterceptor(StethoInterceptor())
        .addInterceptor(AuthInterceptor())
        .build()
}

private fun provideRaiderIoRetrofit(client: OkHttpClient) = Retrofit
    .Builder()
    .baseUrl(BuildConfig.API_BASE_URL)
    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
    .client(client)
    .build()

private fun provideRaiderIoCharacterApi(retrofit: Retrofit): RaiderIoCharacterApi = retrofit.create(RaiderIoCharacterApi::class.java)
private fun provideRaiderIoGuildApi(retrofit: Retrofit): RaiderIoGuildApi = retrofit.create(RaiderIoGuildApi::class.java)
private fun provideRaiderIoMythicPlusApi(retrofit: Retrofit): RaiderIoMythicPlusApi = retrofit.create(RaiderIoMythicPlusApi::class.java)
private fun provideRaiderIoRaidingApi(retrofit: Retrofit): RaiderIoRaidingApi = retrofit.create(RaiderIoRaidingApi::class.java)