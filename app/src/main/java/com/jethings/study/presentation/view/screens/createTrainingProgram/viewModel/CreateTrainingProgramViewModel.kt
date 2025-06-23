package com.jethings.study.presentation.view.screens.createTrainingProgram.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jethings.study.data.api.req_res_classes.TrainingProgramModule.createTrainingProgram.CreateTrainingProgramResponse
import com.jethings.study.domain.manager.TrainingProgramManager
import com.jethings.study.presentation.view.screens.createTrainingProgram.event.CreateTrainingProgramEvent
import kotlinx.coroutines.launch

class CreateTrainingProgramViewModel(
    private val trainingProgramManager: TrainingProgramManager,
) : ViewModel() {


    fun onEvent(event : CreateTrainingProgramEvent, onSuccess : ()->Unit, onFailure : ()->Unit){

        when(event){
            is CreateTrainingProgramEvent.CreateTrainingProgram ->{
                viewModelScope.launch {
                    val result = trainingProgramManager.createTrainingProgram(
                        event.createTrainingProgramRequest,
                        cover = event.file
                    )
                    if (result is CreateTrainingProgramResponse.Success){
                        onSuccess()
                    }else {
                        onFailure()
                    }
                }
            }
        }

    }

}