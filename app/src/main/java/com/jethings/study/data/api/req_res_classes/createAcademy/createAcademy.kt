package com.jethings.study.data.api.req_res_classes

import kotlinx.serialization.Serializable


//request
@Serializable
data class CreateAcademyRequest(
    val name    : String
)



//response

sealed class CreateAcademyResponse{
    class Success(val data : LogInSuccessResponse)  : CreateAcademyResponse()
    class Failure(val data : LogInFailureResponse)  : CreateAcademyResponse()
    class Exception(val data : kotlin.Exception)    : CreateAcademyResponse()
}

@Serializable
data class CreateAcademySuccessResponse(
    val id         : String,
    val name       : String,
    val email      : String,
    val phone      : String
)

@Serializable
data class CreateAcademyFailureResponse(
    val statusCode : Int,
    val message    : List<String>,
    val error      : String
)