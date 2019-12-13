package net.drusantia.raidr.data.network

import okhttp3.*

class AuthInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        chain.request().run {
//            val url = url()
//                .newBuilder()
//                .addQueryParameter("APPID", "your_key_here")
//                .build()
//            return chain.proceed(newBuilder().url(url).build())
            return chain.proceed(this)
        }
    }
}