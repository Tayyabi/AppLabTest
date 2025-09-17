package com.example.applabtest.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ErrorInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        return try {
            val response = chain.proceed(request)

            when {
                response.isSuccessful -> response
                response.code == 401 -> {
                    throw IOException("Unauthorized: Invalid authentication token")
                }
                response.code == 403 -> {
                    throw IOException("Forbidden: Access denied")
                }
                response.code == 404 -> {
                    throw IOException("Not Found: Resource not found")
                }
                response.code == 429 -> {
                    throw IOException("Too Many Requests: Rate limit exceeded")
                }
                response.code >= 500 -> {
                    throw IOException("Server Error: Internal server error occurred")
                }
                else -> {
                    throw IOException("HTTP ${response.code}: ${response.message}")
                }
            }
        } catch (e: IOException) {
            throw e
        } catch (e: Exception) {
            throw IOException("Network error: ${e.message}", e)
        }
    }
}