package com.jethings.study.data.api.req_res_classes

import kotlinx.serialization.Serializable


//request
@Serializable
data class LogInRequest(
    val email    : String,
    val password : String
)



//response

sealed class LogInResponse{
    class Success(val data : LogInSuccessResponse)  : LogInResponse()
    class Failure(val data : LogInFailureResponse)  : LogInResponse()
    class Exception(val data : kotlin.Exception)    : LogInResponse()
}

@Serializable
data class LogInSuccessResponse(
    val access_token       : String,
    val accountId          : Long,
    val userId             : Long,
    val firstName          : String,
    val lastName           : String,
    val profilePhoto       : String,
    val verified           : Boolean,
    val phone              : String,
    val email              : String,
    val isSuperAdmin       : Boolean,
    val isStudent          : Boolean,
    val isParent           : Boolean,
    val isTeacher          : Boolean,
)

@Serializable
data class LogInFailureResponse(
    val statusCode : Int,
    val message    : String,
    val error      : String
)