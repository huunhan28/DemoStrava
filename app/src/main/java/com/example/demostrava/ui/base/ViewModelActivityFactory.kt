package com.example.demostrava.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.demostrava.data.api.ApiActivityHelper
import com.example.demostrava.data.repository.ActivityRepository
import com.example.demostrava.ui.main.viewmodel.ActivityViewModel

class ViewModelActivityFactory(private val apiActivityHelper: ApiActivityHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ActivityViewModel::class.java)) {
            return ActivityViewModel(
                ActivityRepository(apiActivityHelper)
            ) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}

