package com.jethings.study.data.api.req_res_classes

import com.jethings.study.data.db.entities.Account
import kotlinx.serialization.Serializable



//request
@Serializable
data class SignUpRequest(
    val email    : String,
    val password : String
)


//response

sealed class SignUpResponse{
    class Success(val data   : SignUpSuccessResponse) : SignUpResponse()
    class Failure(val data   : SignUpFailureResponse) : SignUpResponse()
    class Exception(val data : kotlin.Exception)      : SignUpResponse()
}

@Serializable
data class SignUpSuccessResponse(
    val access_token : String
)

@Serializable
data class SignUpFailureResponse(
    val statusCode : Int,
    val message    : String,
    val error      : String
)