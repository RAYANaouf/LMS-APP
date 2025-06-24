package com.jethings.study.presentation.view.screens.createPost.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jethings.study.data.api.req_res_classes.PostModule.createPost.CreatePostRequest
import com.jethings.study.data.api.req_res_classes.PostModule.createPost.CreatePostResponse
import com.jethings.study.data.api.req_res_classes.TrainingProgramModule.createTrainingProgram.CreateTrainingProgramResponse
import com.jethings.study.domain.manager.PostManager
import com.jethings.study.domain.manager.TrainingProgramManager
import com.jethings.study.presentation.view.screens.createPost.event.CreatePostEvent
import com.jethings.study.presentation.view.screens.createTrainingProgram.event.CreateTrainingProgramEvent
import kotlinx.coroutines.launch

class CreatePostViewModel(
    private val postManager: PostManager,
) : ViewModel() {


    fun onEvent(event : CreatePostEvent, onSuccess : ()->Unit, onFailure : ()->Unit){

        when(event){
            is CreatePostEvent.CreatePost ->{
                viewModelScope.launch {
                    val result = postManager.createPost(
                        event.createPostRequest,
                        cover = event.file
                    )
                    if (result is CreatePostResponse.Success){
                        onSuccess()
                    }else {
                        onFailure()
                    }
                }
            }
        }

    }

}