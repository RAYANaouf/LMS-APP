package com.jethings.study.data.api.req_res_classes.TrainingProgramModule.requestForCourse

import com.jethings.study.data.api.req_res_classes.TrainingProgramModule.createTrainingProgram.CreateTrainingProgramSuccessResponse
import kotlinx.serialization.Serializable


//request
@Serializable
data class RequestForCourseRequest(
    val userId   : Long,
    val courseId : Long
)

//response
@Serializable
sealed class RequestForCourseResponse {
    class Success(val data : Boolean)                            : RequestForCourseResponse()
    class Failed( val data : RequestForCourseFailureResponse)    : RequestForCourseResponse()
    class Exception(val data : kotlin.Exception)                 : RequestForCourseResponse()
}



@Serializable
data class RequestForCourseFailureResponse(
    val statusCode : Int,
    val message    : List<String>,
    val error      : String
)