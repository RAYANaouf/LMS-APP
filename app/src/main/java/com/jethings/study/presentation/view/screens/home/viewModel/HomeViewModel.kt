package com.jethings.study.presentation.view.screens.home.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jethings.study.data.api.req_res_classes.getAllAcademies.GetAllAcademiesResponse
import com.jethings.study.data.db.entities.entities.Academy
import com.jethings.study.domain.manager.AcademyManager
import com.jethings.study.presentation.view.screens.home.events.HomeEvents
import kotlinx.coroutines.launch

class HomeViewModel(
    private val academyManager: AcademyManager,
) : ViewModel() {


    val academyList = mutableStateListOf<Academy>()


    fun onEvent( event : HomeEvents , onSuccess : () -> Unit , onFailure : () -> Unit){
        when(event){
            is HomeEvents.refreshAcademyList -> {
                viewModelScope.launch {
                    val response = academyManager.getAllAcademies()
                    academyList.clear()
                    if (response is GetAllAcademiesResponse.Success) {
                        academyList.addAll(response.data.academies)
                        onSuccess()
                    } else {
                        onFailure()
                    }
                }
            }
        }
    }
}