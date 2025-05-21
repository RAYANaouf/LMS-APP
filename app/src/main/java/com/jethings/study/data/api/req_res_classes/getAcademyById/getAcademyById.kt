package com.jethings.study.data.api.req_res_classes.getAcademyById

import kotlinx.serialization.Serializable


//response

sealed class GetAcademyByIdResponse{
    class Success(val data : GetAcademyByIdSuccessResponse)  : GetAcademyByIdResponse()
    class Failure(val data : GetAcademyByIdFailureResponse)  : GetAcademyByIdResponse()
    class Exception(val data : kotlin.Exception)    : GetAcademyByIdResponse()
}

@Serializable
data class GetAcademyByIdSuccessResponse(
    val id         : Int,
    val name       : String,
    val phone      : String?,
    val email      : String?,
    val logo      : String?
)

@Serializable
data class GetAcademyByIdFailureResponse(
    val statusCode : Int,
    val message    : List<String>,
    val error      : String
)