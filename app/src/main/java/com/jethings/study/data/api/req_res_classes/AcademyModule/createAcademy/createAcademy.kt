package com.jethings.study.data.api.req_res_classes

import kotlinx.serialization.Serializable
import java.io.File


//request
@Serializable
data class CreateAcademyRequest(
    val name    : String
)



//response

sealed class CreateAcademyResponse{
    class Success(val data : CreateAcademySuccessResponse)  : CreateAcademyResponse()
    class Failure(val data : CreateAcademyFailureResponse)  : CreateAcademyResponse()
    class Exception(val data : kotlin.Exception)    : CreateAcademyResponse()
}

@Serializable
data class CreateAcademySuccessResponse(
    val id         : Int,
    val name       : String,
    val phone      : String?,
    val email      : String?
)

@Serializable
data class CreateAcademyFailureResponse(
    val statusCode : Int,
    val message    : List<String>,
    val error      : String
)