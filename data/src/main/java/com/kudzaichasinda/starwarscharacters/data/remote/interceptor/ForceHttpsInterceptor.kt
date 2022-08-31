package com.kudzaichasinda.starwarscharacters.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response

object ForceHttpsInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()

        if (!originalRequest.url.isHttps) {
            val newUrl = originalRequest.url.newBuilder()
                .scheme("https")
                .host(originalRequest.url.host)
                .build()
            requestBuilder.url(newUrl)
        }

        val newRequest = requestBuilder.build()

        return try {
            chain.proceed(newRequest)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
}
