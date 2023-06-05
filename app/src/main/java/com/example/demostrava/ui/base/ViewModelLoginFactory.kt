package com.example.demostrava.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.demostrava.data.api.ApiLoginHelper
import com.example.demostrava.data.repository.LoginRepository
import com.example.demostrava.ui.main.viewmodel.LoginViewModel

class ViewModelLoginFactory(private val apiLoginHelper: ApiLoginHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                LoginRepository(apiLoginHelper)
            ) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}

