package com.wsl.data

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

internal class UserAgentInterceptor() : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestWithUserAgent = originalRequest.newBuilder()
            .header("User-Agent", System.getProperty("http.agent"))
            .build()
        return chain.proceed(requestWithUserAgent)
    }
}