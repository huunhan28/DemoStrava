package com.example.demostrava.data.api

import android.content.Context
import com.example.demostrava.data.model.input.InputDetailActivity
import com.example.demostrava.data.model.input.InputListAthleteActivity
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.UUID
import java.util.concurrent.TimeUnit

class ApiActivityHelper(accessToken: String) {

    private val BASE_URL = "https://www.strava.com/api/v3/"

    var client = OkHttpClient.Builder().addInterceptor { chain ->
        val newRequest: Request = chain.request().newBuilder()
            .addHeader("accept", "application/json")
            .addHeader("Authorization", "Bearer $accessToken")

            .build()
        chain.proceed(newRequest)
    }
        .addInterceptor(ErrorInterceptor()).
    build()

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }


    val apiService: ApiService = getRetrofit().create(ApiService::class.java)

    suspend fun getStreamsActivity(id: Long)
            = apiService.getStreamsActivity(id)

    suspend fun getDetailActivity(id: Long)
            = apiService.getDetailActivity(id)

    suspend fun getListAthleteActivity(input: InputListAthleteActivity)
    = apiService.getListAthleteActivity(
        input.page,
        input.per_page
    )

    suspend fun getProfile()
            = apiService.getProfile()



}

class ErrorInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request: Request = chain.request()
        val uuid = UUID.randomUUID().toString()
        val response = chain.proceed(request)

        try {
            when(response.code){
                200 ->{
                    //END
                }
            }
        } catch (_ : Exception){

        }
        return response
    }

}