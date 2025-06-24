package com.jethings.study.presentation.view.screens.createPost.event

import com.jethings.study.data.api.req_res_classes.PostModule.createPost.CreatePostRequest
import com.jethings.study.data.api.req_res_classes.TrainingProgramModule.createTrainingProgram.CreateTrainingProgramRequest
import java.io.File

sealed class CreatePostEvent {


    data class CreatePost(val createPostRequest: CreatePostRequest , val file : File?) : CreatePostEvent()
}