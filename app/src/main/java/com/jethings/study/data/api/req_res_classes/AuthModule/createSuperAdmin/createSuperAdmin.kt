package com.jethings.study.data.api.req_res_classes.AuthModule.createSuperAdmin

import kotlinx.serialization.Serializable


// request
@Serializable
data class CreateSuperAdminRequest(
    val email     : String ,
    val password  : String,
    val firstName : String?,
    val lastName  : String?
)


//response
@Serializable
sealed class CreateSuperAdminResponse{
    class Success(val data : CreateSuperAdminSuccessResponse)  : CreateSuperAdminResponse()
    class Failure(val data : CreateSuperAdminFailureResponse)  : CreateSuperAdminResponse()
    class Exception(val data : kotlin.Exception)    : CreateSuperAdminResponse()
}

@Serializable
data class CreateSuperAdminSuccessResponse(
    val access_token : String
)

@Serializable
data class CreateSuperAdminFailureResponse(
    val statusCode : Int,
    val message    : List<String>,
    val error      : String
)