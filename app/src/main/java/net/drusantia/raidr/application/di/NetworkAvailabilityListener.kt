package net.drusantia.raidr.application.di

import android.content.Context
import android.net.ConnectivityManager

class NetworkAvailabilityListener(
    private val context: Context
) {
    fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = connectivityManager.activeNetworkInfo
        // netInfo?.detailedState == NetworkInfo.DetailedState.CONNECTED
        return netInfo?.isConnected ?: false
    }
}