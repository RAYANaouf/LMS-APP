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
    val access_token       : String  = "",
    val accountId          : Long    = 0L,
    val userId             : Long    = 0L,
    val firstName          : String  = "",
    val lastName           : String  = "",
    val profilePhoto       : String  = "",
    val verified           : Boolean = false,
    val phone              : String  = "",
    val email              : String  = "",
    val isSuperAdmin       : Boolean = false,
    val isStudent          : Boolean = false,
    val isParent           : Boolean = false,
    val isTeacher          : Boolean = false,
    val ownedAcademies     : Int     = 0,
)

@Serializable
data class LogInFailureResponse(
    val statusCode : Int = 0,
    val message    : String = "",
    val error      : String = "",
)