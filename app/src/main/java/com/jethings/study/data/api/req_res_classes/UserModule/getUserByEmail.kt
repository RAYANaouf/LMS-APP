package com.jethings.study.data.api.req_res_classes.UserModule

import com.jethings.study.data.db.entities.Account
import kotlinx.serialization.Serializable


//response

sealed class GetUserByEmailResponse{
    class Success(val data : GetUserByEmailSuccessResponse)  : GetUserByEmailResponse()
    class Failure(val data : GetUserByEmailFailureResponse)  : GetUserByEmailResponse()
    class Exception(val data : kotlin.Exception)    : GetUserByEmailResponse()
}

@Serializable
data class GetUserByEmailSuccessResponse(
    val account : Account
)

@Serializable
data class GetUserByEmailFailureResponse(
    val statusCode : Int,
    val message    : List<String>,
    val error      : String
)