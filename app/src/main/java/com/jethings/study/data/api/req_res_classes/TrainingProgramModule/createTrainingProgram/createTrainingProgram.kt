package com.jethings.study.data.api.req_res_classes.TrainingProgramModule.createTrainingProgram

import kotlinx.serialization.Serializable


// request
@Serializable
data class CreateTrainingProgramRequest(
    val name               : String ,
    val description        : String ,
    val targetAudience     : String?,
    val prerequisites      : String?,
    val whatYouWillLearn   : String?,
    val whatYouCanDoAfter  : String?,
    val minAge             : Int?   ,
    val maxAge             : Int?   ,
    val price              : Float
)


//response
@Serializable
sealed class CreateTrainingProgramResponse{
    class Success(val data : CreateTrainingProgramSuccessResponse)  : CreateTrainingProgramResponse()
    class Failure(val data : CreateTrainingProgramFailureResponse)  : CreateTrainingProgramResponse()
    class Exception(val data : kotlin.Exception)    : CreateTrainingProgramResponse()
}

@Serializable
data class CreateTrainingProgramSuccessResponse(
    val id               : Int     = 0 ,
    val academyId        : Long    = 0 ,
    val name             : String  = "",
    val description      : String  = "",
    val targetAudience   : String? = "",
    val prerequisites    : String? = "",
    val whatYouWillLearn : String? = "",
    val whatYouCanDoAfter: String? = "",
    val minAge           : Int?    = 0,
    val maxAge           : Int?    = 0,
    val price            : Float?  = 0f
)

@Serializable
data class CreateTrainingProgramFailureResponse(
    val statusCode : Int,
    val message    : List<String>,
    val error      : String
)