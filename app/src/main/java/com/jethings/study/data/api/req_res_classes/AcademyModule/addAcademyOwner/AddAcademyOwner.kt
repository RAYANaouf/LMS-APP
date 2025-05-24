package com.jethings.study.data.api.req_res_classes.AcademyModule.addAcademyOwner

import kotlinx.serialization.Serializable


//response

@Serializable
data class AddAcademyOwnerRequest(
    val userId : Int
)

sealed class AddAcademyOwnerResponse{
    class Success(val data : AddAcademyOwnerSuccessResponse)  : AddAcademyOwnerResponse()
    class Failure(val data : AddAcademyOwnerFailureResponse)  : AddAcademyOwnerResponse()
    class Exception(val data : kotlin.Exception)    : AddAcademyOwnerResponse()
}

@Serializable
data class AddAcademyOwnerSuccessResponse(
    val added         : Boolean       = false,
)

@Serializable
data class AddAcademyOwnerFailureResponse(
    val statusCode : Int,
    val message    : List<String>,
    val error      : String
)