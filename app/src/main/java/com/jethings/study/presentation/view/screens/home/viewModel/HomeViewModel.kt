package com.jethings.study.presentation.view.screens.home.viewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jethings.study.data.api.req_res_classes.SuperAdminModule.GetAllSuperAdminResponse
import com.jethings.study.data.api.req_res_classes.AcademyModule.getAllAcademies.GetAllAcademiesResponse
import com.jethings.study.data.api.req_res_classes.TrainingProgramModule.getAllTrainingProgram.GetAllTrainingProgramResponse
import com.jethings.study.data.api.req_res_classes.TrainingProgramModule.getAllTrainingProgram.GetAllTrainingProgramSuccessResponse
import com.jethings.study.data.db.entities.entities.Academy
import com.jethings.study.data.db.entities.entities.SuperAdmin
import com.jethings.study.data.db.entities.entities.TrainingProgram
import com.jethings.study.domain.manager.AcademyManager
import com.jethings.study.domain.manager.SuperAdminManager
import com.jethings.study.domain.manager.TrainingProgramManager
import com.jethings.study.presentation.view.screens.home.events.HomeEvents
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel(
    private val trainingProgramManager : TrainingProgramManager,
    private val academyManager: AcademyManager,
    private val superAdminManager: SuperAdminManager,
    private val context : Context
) : ViewModel() {


    val trainingProgramList    = mutableStateListOf<TrainingProgram>()
    val academyList            = mutableStateListOf<Academy>()
    val superAdminList         = mutableStateListOf<SuperAdmin>()


    init {
        viewModelScope.launch {
            val response = trainingProgramManager.getAll()
            if(response is GetAllTrainingProgramResponse.Success){
                trainingProgramList.clear()
                trainingProgramList.addAll(response.data.trainingPrograms)
            }else{
                Toast.makeText(context , "Failed to get Training programs" , Toast.LENGTH_SHORT).show()
                Log.d("get all training program : " , response.toString())
            }
        }
    }

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
            is HomeEvents.refreshSuperAdminList -> {
                viewModelScope.launch {
                    val response = superAdminManager.getAllSuperAdmins()
                    superAdminList.clear()
                    if (response is GetAllSuperAdminResponse.Success) {
                        superAdminList.addAll(response.data.superAdminList)
                        onSuccess()
                    } else {
                        onFailure()
                    }
                }
            }
            is HomeEvents.getAllTrainingProgram ->{
                viewModelScope.launch {
                    val response = trainingProgramManager.getAll()
                    Toast.makeText(context , "response ${response}" , Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}