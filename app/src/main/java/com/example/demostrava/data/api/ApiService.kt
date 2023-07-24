package com.example.demostrava.data.api

import com.example.demostrava.data.model.input.InputGetAccessToken
import com.example.demostrava.data.model.output.ActivityModel
import com.example.demostrava.data.model.output.OutputGetAccessToken
import com.example.demostrava.data.model.output.ProfileModel
import com.example.demostrava.data.model.output.StreamModel
import retrofit2.http.*

interface ApiService {

//    @GET("activities/9143250963/streams?keys=latlng&key_by_type=true")
    @GET("activities/{id}/streams?keys=latlng&key_by_type=true")
    suspend fun getStreamsActivity(
        @Path("id") id: Long,
    ): StreamModel

    @GET("activities/{id}?include_all_efforts=true")
    suspend fun getDetailActivity(
        @Path("id") id: Long
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