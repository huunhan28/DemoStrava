package com.example.demostrava.data.api

import com.example.demostrava.data.model.input.InputGetAccessToken
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiLoginHelper() {

    private val BASE_URL = "https://www.strava.com/api/v3/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }



    val apiService: ApiService = getRetrofit().create(ApiService::class.java)

    suspend fun getAccessToken(input: InputGetAccessToken) = apiService.getAccessToken(input)

}