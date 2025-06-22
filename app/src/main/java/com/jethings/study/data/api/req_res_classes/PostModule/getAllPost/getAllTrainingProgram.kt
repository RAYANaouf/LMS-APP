package com.jethings.study.data.api.req_res_classes.TrainingProgramModule.getAllTrainingProgram

import com.jethings.study.data.db.entities.entities.Academy
import com.jethings.study.data.db.entities.entities.TrainingProgram
import kotlinx.serialization.Serializable





//response

sealed class GetAllTrainingProgramResponse{
    class Success(val data : GetAllTrainingProgramSuccessResponse)  : GetAllTrainingProgramResponse()
    class Failure(val data : GetAllTrainingProgramFailureResponse)  : GetAllTrainingProgramResponse()
    class Exception(val data : kotlin.Exception)                    : GetAllTrainingProgramResponse()
}

@Serializable
data class GetAllTrainingProgramSuccessResponse(
    val trainingPrograms : List<TrainingProgram>
)

@Serializable
data class GetAllTrainingProgramFailureResponse(
    val statusCode : Int,
    val message    : List<String>,
    val error      : String
)