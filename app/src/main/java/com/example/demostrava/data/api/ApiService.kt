package com.example.demostrava.data.api

import com.example.demostrava.data.model.input.InputGetAccessToken
import com.example.demostrava.data.model.output.ActivityModel
import com.example.demostrava.data.model.output.OutputGetAccessToken
import com.example.demostrava.data.model.output.ProfileModel
import retrofit2.http.*

interface ApiService {

    @GET("activities/{id}")
    suspend fun getDetailActivity(
        @Path("id") id: Long,
        @Query("include_all_efforts") include_all_efforts: Boolean = true
    ): ActivityModel

    @GET("athlete/activities")
    suspend fun getListAthleteActivity(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): List<ActivityModel>

    @GET("athlete")
    suspend fun getProfile(
    ): ProfileModel

    @POST("oauth/token")
    suspend fun getAccessToken(@Body input: InputGetAccessToken): OutputGetAccessToken
}