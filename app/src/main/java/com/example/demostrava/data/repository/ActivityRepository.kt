package com.example.demostrava.data.repository

import com.example.demostrava.data.api.ApiActivityHelper
import com.example.demostrava.data.model.input.InputListAthleteActivity

class ActivityRepository(private val apiActivityHelper: ApiActivityHelper) {

    suspend fun getListAthleteActivity(input: InputListAthleteActivity) = apiActivityHelper.getListAthleteActivity(input)

    suspend fun getProfile() = apiActivityHelper.getProfile()
}