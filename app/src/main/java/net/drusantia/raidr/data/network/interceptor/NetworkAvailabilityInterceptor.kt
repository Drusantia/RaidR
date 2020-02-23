package net.drusantia.raidr.data.network.interceptor

import okhttp3.*

private const val ERROR_MESSAGE = "Internet connection not available"
private val ERROR_RESPONSE by lazy { ResponseBody.create(MediaType.get("application/json"), "{}") }

class NetworkAvailabilityInterceptor(
    private val isNetworkAvailable: () -> Boolean
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        chain.request().run {
            return if (!isNetworkAvailable()) {
                createNoNetworkErrorResponse(chain)
            } else chain.proceed(this)
        }
    }

    private fun createNoNetworkErrorResponse(chain: Interceptor.Chain): Response {
        return Response.Builder()
            .code(503)
            .protocol(Protocol.HTTP_2)
            .message(ERROR_MESSAGE)
            .body(ERROR_RESPONSE)
            .request(chain.request())
            .build()
    }
}