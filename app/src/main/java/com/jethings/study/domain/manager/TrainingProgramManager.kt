package com.jethings.study.domain.manager

import com.jethings.study.data.api.req_res_classes.TrainingProgramModule.createTrainingProgram.CreateTrainingProgramRequest
import com.jethings.study.data.api.req_res_classes.TrainingProgramModule.createTrainingProgram.CreateTrainingProgramResponse
import com.jethings.study.data.api.req_res_classes.TrainingProgramModule.getAllByAcademy.GetAllByAcademyResponse
import com.jethings.study.data.api.req_res_classes.TrainingProgramModule.getAllTrainingProgram.GetAllTrainingProgramResponse
import java.io.File

interface TrainingProgramManager {

    suspend fun createTrainingProgram( request : CreateTrainingProgramRequest , cover : File?) : CreateTrainingProgramResponse
    suspend fun getAllByAcademy( academyId : Int ) : GetAllByAcademyResponse
    suspend fun getAll() : GetAllTrainingProgramResponse

}