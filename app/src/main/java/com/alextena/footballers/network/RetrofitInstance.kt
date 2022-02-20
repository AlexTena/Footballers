package com.alextena.footballers.network

import com.alextena.footballers.utils.Constants.Companion.API_KEY
import com.alextena.footballers.utils.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor{ chain ->
                    val newRequest = chain.request()
                        .newBuilder()
                        .addHeader("X-AUTH-TOKEN", API_KEY)
                        .build()
                    chain.proceed(newRequest)
                }
                .build()

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        val api by lazy {
            retrofit.create(FootballApi::class.java)
        }
    }
}