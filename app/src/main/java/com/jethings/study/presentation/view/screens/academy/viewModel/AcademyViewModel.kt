package com.jethings.study.presentation.view.screens.academy.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jethings.study.data.api.req_res_classes.AcademyModule.getAcademyById.GetAcademyByIdResponse
import com.jethings.study.data.api.req_res_classes.AcademyModule.getAcademyById.GetAcademyByIdSuccessResponse
import com.jethings.study.data.api.req_res_classes.PostModule.getAllByAcademy.GetAllByAcademySuccessResponse
import com.jethings.study.data.api.req_res_classes.PostModule.getAllPost.GetAllPostsResponse
import com.jethings.study.data.api.req_res_classes.TrainingProgramModule.getAllByAcademy.GetAllByAcademyResponse
import com.jethings.study.data.db.entities.entities.Academy
import com.jethings.study.data.db.entities.entities.TrainingProgram
import com.jethings.study.domain.manager.AcademyManager
import com.jethings.study.domain.manager.PostManager
import com.jethings.study.domain.manager.TrainingProgramManager
import com.jethings.study.presentation.view.screens.academy.events.AcademyEvent
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AcademyViewModel(
    private val academyId : Int ,
    private val academyManager: AcademyManager,
    private val trainingProgramManager: TrainingProgramManager,
    private val postManager: PostManager
) : ViewModel() {

    var academy by mutableStateOf<Academy?>(null)
        private set

    private var academyLoadJob: Job? = null


    init {
        getAcademyDetails()
    }

    private fun getAcademyDetails() {
        academyLoadJob = viewModelScope.launch {
            val result = academyManager.getAcademyById(academyId)
            if (result is GetAcademyByIdResponse.Success) {
                academy = Academy(id = result.data.id, name = result.data.name , email = result.data.email , phone = result.data.phone , logo = result.data.logo , owners = result.data.owners)
            }
        }
        onEvent(
            AcademyEvent.GetAllPostByAcademy(academy_id = academyId),{

            },{

            }
        )
    }

    fun onEvent( event : AcademyEvent , onSuccess : ()->Unit = {} , onFailure : ()->Unit = {}){
        when(event){
            is AcademyEvent.GetAllTrainingProgramByAcademy ->{
                viewModelScope.launch {
                    //wait for academy to be loaded
                    academyLoadJob?.join()

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
            is AcademyEvent.GetAllPostByAcademy ->{
                viewModelScope.launch {
                    //wait for academy to be loaded
                    academyLoadJob?.join()

                    val result = postManager.getAllByAcademy(event.academy_id)
                    if(result is com.jethings.study.data.api.req_res_classes.PostModule.getAllByAcademy.GetAllByAcademyResponse.Success){
                        academy?.let {
                            academy = it.copy(posts = result.data.posts)
                        }
                        onSuccess()
                    }else if(result is com.jethings.study.data.api.req_res_classes.PostModule.getAllByAcademy.GetAllByAcademyResponse.Failure){
                        onFailure()
                    }else{
                        onFailure()
                    }
                }
            }
        }
    }
}