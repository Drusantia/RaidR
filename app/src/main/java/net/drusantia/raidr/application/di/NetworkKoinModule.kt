package net.drusantia.raidr.application.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import net.drusantia.raidr.BuildConfig
import net.drusantia.raidr.data.network.*
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val NetworkKoinModule = module {
    factory { provideOkHttpClient() }
    factory { provideRaiderIoRetrofit(get()) }
    factory { provideRaiderIoCharacterApi(get()) }
    factory { RaiderIoCharacterAccessor(get()) }
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