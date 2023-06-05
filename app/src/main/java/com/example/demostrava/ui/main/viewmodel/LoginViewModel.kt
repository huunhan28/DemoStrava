package com.example.demostrava.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.demostrava.data.model.input.InputGetAccessToken
import com.example.demostrava.data.repository.LoginRepository
import com.example.demostrava.utils.Resource
import kotlinx.coroutines.Dispatchers


class LoginViewModel(
    private val loginRepository: LoginRepository
    ) : ViewModel() {


    fun getAccessToken(input: InputGetAccessToken) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = loginRepository.getAccessToken(input)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}