package com.jethings.study.presentation.view.screens.academy.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jethings.study.domain.manager.AcademyManager
import com.jethings.study.presentation.view.screens.academy.events.AcademyEvent
import kotlinx.coroutines.launch

class AcademyViewModel(
    private val academyManager: AcademyManager
) : ViewModel() {


    fun onEvent( event : AcademyEvent , onSuccess : ()->Unit = {} , onFailure : ()->Unit = {}){
        when(event){
            is AcademyEvent.GetAcademyDetails -> {
                viewModelScope.launch {

                }
            }
        }
    }
}