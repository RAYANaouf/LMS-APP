package com.jethings.study.presentation.view.screens.TrainingProgram.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jethings.study.data.api.req_res_classes.TrainingProgramModule.requestForCourse.RequestForCourseRequest
import com.jethings.study.data.api.req_res_classes.TrainingProgramModule.requestForCourse.RequestForCourseResponse
import com.jethings.study.domain.manager.LocalUserManager
import com.jethings.study.domain.manager.TrainingProgramManager
import com.jethings.study.presentation.view.screens.TrainingProgram.event.CourseEvent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class TrainingProgramViewModel(
    private val trainingProgramManager : TrainingProgramManager,
    private val localUserManager       : LocalUserManager
) : ViewModel() {


    fun onEvent( event : CourseEvent , onSuccess: ()->Unit , onFailure : ()->Unit ){

        when(event) {
            is CourseEvent.RequestCourse ->{
                viewModelScope.launch {
                    val user = localUserManager.readAccount().first()
                    if (user == null){
                        onFailure()
                        return@launch
                    }else{
                        val result = trainingProgramManager.requestForCourse(RequestForCourseRequest(userId = user.userId , courseId = event.courseId))
                        if (result is RequestForCourseResponse.Success && result.data){
                            onSuccess()
                        }else{
                            onFailure()
                        }
                    }
                }
            }
        }

    }

}