package com.example.applabtest.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    companion object {
        private const val BEARER_TOKEN = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL25vc3Eub25saW5lL2FwaS9sb2dpbiIsImlhdCI6MTc1NzE4OTI0MywiZXhwIjoxNzU3MTg5MzAzLCJuYmYiOjE3NTcxODkyNDMsImp0aSI6ImhMbHU1b2hVVWNXUXFSamsiLCJzdWIiOiI0IiwicHJ2IjoiMjNiZDVjODk0OWY2MDBhZGIzOWU3MDFjNDAwODcyZGI3YTU5NzZmNyJ9.-i_IuZ2GSBKj5TZWJ1q6EcWfV8P64EVWFIXb7ZRw19c"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val newRequest = request.newBuilder()
            .addHeader("Authorization", BEARER_TOKEN)
            .addHeader("Content-Type", "application/json")
            .build()

        return chain.proceed(newRequest)
    }
}