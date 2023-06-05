package com.example.demostrava.data.repository

import com.example.demostrava.data.api.ApiLoginHelper
import com.example.demostrava.data.model.input.InputGetAccessToken

class LoginRepository(private val apiLoginHelper: ApiLoginHelper) {
    suspend fun getAccessToken(input: InputGetAccessToken) = apiLoginHelper.getAccessToken(input)
}