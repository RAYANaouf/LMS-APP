package com.jethings.study.presentation.view.screens.createAcademy.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jethings.study.data.api.req_res_classes.CreateAcademyRequest
import com.jethings.study.data.api.req_res_classes.CreateAcademyResponse
import com.jethings.study.domain.manager.AcademyManager
import com.jethings.study.presentation.view.screens.createAcademy.events.CreateAcademyEvents
import kotlinx.coroutines.launch

class CreateAcademyViewModel(
    private val academyManager: AcademyManager
) : ViewModel() {


    fun onEvent(event: CreateAcademyEvents, onSuccess: () -> Unit, onFailure: () -> Unit) {
        when (event) {
            is CreateAcademyEvents.CreateAcademy -> {
                viewModelScope.launch {
                    val response = academyManager.createAcademy(
                        CreateAcademyRequest(event.name),
                        event.logo
                    )
                    if (response is CreateAcademyResponse.Success) {
                        onSuccess()
                    } else if (response is CreateAcademyResponse.Failure) {
                        Log.d("create academy : " , "Failed to create academy")
                        onFailure()
                    } else if (response is CreateAcademyResponse.Exception) {
                        onFailure()
                        Log.d("create academy : " , "Exception on create academy ${response.data}")
                    }
                }
            }
        }

    }
}