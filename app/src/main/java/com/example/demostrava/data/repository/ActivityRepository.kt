package com.example.demostrava.data.repository

import com.example.demostrava.data.api.ApiActivityHelper
import com.example.demostrava.data.model.input.InputDetailActivity
import com.example.demostrava.data.model.input.InputListAthleteActivity

class ActivityRepository(private val apiActivityHelper: ApiActivityHelper) {

    suspend fun getStreamsActivity(id: Long) = apiActivityHelper.getStreamsActivity(id)
    suspend fun getDetailActivity(id: Long) = apiActivityHelper.getDetailActivity(id)

    suspend fun getListAthleteActivity(input: InputListAthleteActivity) = apiActivityHelper.getListAthleteActivity(input)

    suspend fun getProfile() = apiActivityHelper.getProfile()
}