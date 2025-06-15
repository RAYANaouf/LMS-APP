package com.jethings.study.presentation.view.screens.academy.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jethings.study.data.api.req_res_classes.AcademyModule.getAcademyById.GetAcademyByIdResponse
import com.jethings.study.data.api.req_res_classes.AcademyModule.getAcademyById.GetAcademyByIdSuccessResponse
import com.jethings.study.data.api.req_res_classes.TrainingProgramModule.getAllByAcademy.GetAllByAcademyResponse
import com.jethings.study.data.db.entities.entities.Academy
import com.jethings.study.data.db.entities.entities.TrainingProgram
import com.jethings.study.domain.manager.AcademyManager
import com.jethings.study.domain.manager.TrainingProgramManager
import com.jethings.study.presentation.view.screens.academy.events.AcademyEvent
import kotlinx.coroutines.launch

class AcademyViewModel(
    private val academyManager: AcademyManager,
    private val trainingProgramManager: TrainingProgramManager
) : ViewModel() {

    var academy by mutableStateOf<Academy?>(null)
        private set




    fun onEvent( event : AcademyEvent , onSuccess : ()->Unit = {} , onFailure : ()->Unit = {}){
        when(event){
            is AcademyEvent.GetAcademyDetails -> {
                viewModelScope.launch {
                    val result = academyManager.getAcademyById(event.academy_id)
                    if(result is GetAcademyByIdResponse.Success){
                        academy = Academy(id = result.data.id, name = result.data.name , email = result.data.email , phone = result.data.phone , logo = result.data.logo , owners = result.data.owners)
                        onSuccess()
                    }else if(result is GetAcademyByIdResponse.Failure){
                        onFailure()
                    }else{
                        onFailure()
                    }
                }
            }
            is AcademyEvent.GetAllTrainingProgramByAcademy ->{
                viewModelScope.launch {
                    val result = trainingProgramManager.getAllByAcademy(event.academy_id)
                    if(result is GetAllByAcademyResponse.Success){
                        //academy  =result.data.trainingPrograms
                        academy?.let {
                            academy = it.copy(trainingPrograms = result.data.trainingPrograms)
                        }
                        Log.d("the result " , result.data.trainingPrograms.toString())
                        Log.d("new academy value : " , academy.toString())
                        onSuccess()
                    }else if(result is GetAllByAcademyResponse.Failure){
                        onFailure()
                    }else{
                        onFailure()
                    }
                }
            }
        }
    }
}