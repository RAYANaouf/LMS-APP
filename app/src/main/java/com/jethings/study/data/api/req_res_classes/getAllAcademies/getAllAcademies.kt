package com.jethings.study.data.api.req_res_classes.getAllAcademies

import com.jethings.study.data.db.entities.entities.Academy
import kotlinx.serialization.Serializable





//response

sealed class GetAllAcademiesResponse{
    class Success(val data : GetAllAcademiesSuccessResponse)  : GetAllAcademiesResponse()
    class Failure(val data : GetAllAcademiesFailureResponse)  : GetAllAcademiesResponse()
    class Exception(val data : kotlin.Exception)    : GetAllAcademiesResponse()
}

@Serializable
data class GetAllAcademiesSuccessResponse(
    val academies : List<Academy>
)

@Serializable
data class GetAllAcademiesFailureResponse(
    val statusCode : Int,
    val message    : List<String>,
    val error      : String
)