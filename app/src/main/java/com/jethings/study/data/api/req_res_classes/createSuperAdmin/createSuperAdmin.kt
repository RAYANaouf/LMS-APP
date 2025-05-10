package com.jethings.study.data.api.req_res_classes.createSuperAdmin

import kotlinx.serialization.Serializable


// request

data class CreateSuperAdminRequest(
    val email : String ,
    val password : String
)




//response

sealed class CreateSuperAdminResponse{
    class Success(val data : CreateSuperAdminSuccessResponse)  : CreateSuperAdminResponse()
    class Failure(val data : CreateSuperAdminFailureResponse)  : CreateSuperAdminResponse()
    class Exception(val data : kotlin.Exception)    : CreateSuperAdminResponse()
}

@Serializable
data class CreateSuperAdminSuccessResponse(
    val id         : Int,
    val name       : String,
    val phone      : String?,
    val email      : String?
)

@Serializable
data class CreateSuperAdminFailureResponse(
    val statusCode : Int,
    val message    : List<String>,
    val error      : String
)