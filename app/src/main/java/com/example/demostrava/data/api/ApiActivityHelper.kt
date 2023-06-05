package com.example.demostrava.data.api

import com.example.demostrava.data.model.input.InputListAthleteActivity
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiActivityHelper(accessToken: String) {

    private val BASE_URL = "https://www.strava.com/api/v3/"

    var client = OkHttpClient.Builder().addInterceptor { chain ->
        val newRequest: Request = chain.request().newBuilder()
            .addHeader("accept", "application/json")
            .addHeader("Authorization", "Bearer $accessToken")
            .build()
        chain.proceed(newRequest)
    }.build()

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }



    val apiService: ApiService = getRetrofit().create(ApiService::class.java)

    suspend fun getListAthleteActivity(input: InputListAthleteActivity)
    = apiService.getListAthleteActivity(
        input.page,
        input.per_page
    )

    suspend fun getProfile()
            = apiService.getProfile()

}