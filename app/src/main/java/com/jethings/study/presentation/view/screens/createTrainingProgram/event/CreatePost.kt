package com.jethings.study.presentation.view.screens.createTrainingProgram.event

import com.jethings.study.data.api.req_res_classes.TrainingProgramModule.createTrainingProgram.CreateTrainingProgramRequest
import java.io.File

sealed class CreatePost {


    data class CreatePost(val createPostRequest: CreatePostRequest , val file : File?) : CreatePost()
}