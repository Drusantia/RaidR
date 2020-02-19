package net.drusantia.raidr.data.network

import okhttp3.*

class NetworkAvailabilityInterceptor(
    private val isNetworkAvailable: () -> Boolean,
    private val onInternetUnavailable: () -> Unit
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        chain.request().run {
            if (!isNetworkAvailable()) {
                onInternetUnavailable()
            }
            return chain.proceed(this)
        }
    }
}