package com.jethings.study.domain.manager

import com.jethings.study.data.api.req_res_classes.PostModule.createPost.CreatePostRequest
import com.jethings.study.data.api.req_res_classes.PostModule.createPost.CreatePostResponse
import com.jethings.study.data.api.req_res_classes.TrainingProgramModule.createTrainingProgram.CreateTrainingProgramRequest
import com.jethings.study.data.api.req_res_classes.TrainingProgramModule.createTrainingProgram.CreateTrainingProgramResponse
import com.jethings.study.data.api.req_res_classes.TrainingProgramModule.getAllByAcademy.GetAllByAcademyResponse
import com.jethings.study.data.api.req_res_classes.TrainingProgramModule.getAllTrainingProgram.GetAllTrainingProgramResponse
import java.io.File

interface PostManager {

    suspend fun createPost( request : CreatePostRequest , cover : File?) : CreatePostResponse
    suspend fun getAllByAcademy( academyId : Int ) :
    suspend fun getAll() :

}