package com.jethings.study.data.api.req_res_classes.AcademyModule.getAcademyById

import com.jethings.study.data.db.entities.Account
import kotlinx.serialization.Serializable


//response

sealed class GetAcademyOwnersResponse{
    class Success(val data : GetAcademyOwnersSuccessResponse)  : GetAcademyOwnersResponse()
    class Failure(val data : GetAcademyOwnersFailureResponse)  : GetAcademyOwnersResponse()
    class Exception(val data : kotlin.Exception)    : GetAcademyOwnersResponse()
}

@Serializable
data class GetAcademyOwnersSuccessResponse(
    val owners     : List<Account> = emptyList(),
)

@Serializable
data class GetAcademyOwnersFailureResponse(
    val statusCode : Int,
    val message    : String,
    val error      : String
)