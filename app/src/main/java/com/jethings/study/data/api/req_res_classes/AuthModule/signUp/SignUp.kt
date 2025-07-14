package com.jethings.study.data.api.req_res_classes

import com.jethings.study.data.db.entities.Account
import kotlinx.serialization.Serializable



//request
@Serializable
data class SignUpRequest(
    val firstName : String,
    val lastName  : String,
    val phone     : String,
    val email     : String,
    val password  : String,
)


//response

sealed class SignUpResponse{
    class Success(val data   : SignUpSuccessResponse) : SignUpResponse()
    class Failure(val data   : SignUpFailureResponse) : SignUpResponse()
    class Exception(val data : kotlin.Exception)      : SignUpResponse()
}

@Serializable
data class SignUpSuccessResponse(
    val accessToken       : String  = "",
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
)

@Serializable
data class SignUpFailureResponse(
    val statusCode : Int,
    val message    : String,
    val error      : String
)