package com.jethings.study.data.api.req_res_classes.AcademyModule.getAcademyById

import kotlinx.serialization.Serializable


//response

sealed class GetAcademyByIdResponse{
    class Success(val data : GetAcademyByIdSuccessResponse)  : GetAcademyByIdResponse()
    class Failure(val data : GetAcademyByIdFailureResponse)  : GetAcademyByIdResponse()
    class Exception(val data : kotlin.Exception)    : GetAcademyByIdResponse()
}

@Serializable
data class GetAcademyByIdSuccessResponse(
    val id         : Int       = 0,
    val name       : String    = "",
    val phone      : String?   = null,
    val email      : String?   = null,
    val logo       : String?   = null,
    val owners     : List<Int>? = null,
)

@Serializable
data class GetAcademyByIdFailureResponse(
    val statusCode : Int,
    val message    : List<String>,
    val error      : String
)