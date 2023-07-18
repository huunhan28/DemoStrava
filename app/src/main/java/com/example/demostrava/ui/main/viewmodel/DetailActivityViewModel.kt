package com.example.demostrava.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.demostrava.data.model.input.InputDetailActivity
import com.example.demostrava.data.model.input.InputListAthleteActivity
import com.example.demostrava.data.repository.ActivityRepository
import com.example.demostrava.utils.Resource
import kotlinx.coroutines.Dispatchers


class DetailActivityViewModel(
    private val activityRepository: ActivityRepository
    ) : ViewModel() {

    fun getStreamsActivity(id: Long) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = activityRepository.getStreamsActivity(id)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}