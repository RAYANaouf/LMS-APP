package com.jethings.study.data.api.req_res_classes.PostModule.getAllByAcademy

import com.jethings.study.data.db.entities.entities.Post
import kotlinx.serialization.Serializable


//response

sealed class GetAllByAcademyResponse{
    class Success(   val data : GetAllByAcademySuccessResponse)  : GetAllByAcademyResponse()
    class Failure(   val data : GetAllByAcademyFailureResponse)  : GetAllByAcademyResponse()
    class Exception( val data : kotlin.Exception)    : GetAllByAcademyResponse()
}

@Serializable
data class GetAllByAcademySuccessResponse(
    val posts     : List<Post> = emptyList(),
)

@Serializable
data class GetAllByAcademyFailureResponse(
    val statusCode : Int,
    val message    : String,
    val error      : String
)