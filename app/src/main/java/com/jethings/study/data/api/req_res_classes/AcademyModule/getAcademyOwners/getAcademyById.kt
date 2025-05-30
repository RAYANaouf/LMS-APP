package com.jethings.study.data.api.req_res_classes.AcademyModule.getAcademyById

import com.jethings.study.data.db.entities.Account
import com.jethings.study.data.db.entities.entities.Academy
import kotlinx.serialization.Serializable


//response

sealed class GetAcademiesByOwnerIdResponse{
    class Success(val data : GetAcademiesByOwnerIdSuccessResponse)  : GetAcademiesByOwnerIdResponse()
    class Failure(val data : GetAcademiesByOwnerIdFailureResponse)  : GetAcademiesByOwnerIdResponse()
    class Exception(val data : kotlin.Exception)    : GetAcademiesByOwnerIdResponse()
}

@Serializable
data class GetAcademiesByOwnerIdSuccessResponse(
    val academies     : List<Academy> = emptyList(),
)

@Serializable
data class GetAcademiesByOwnerIdFailureResponse(
    val statusCode : Int,
    val message    : String,
    val error      : String
)