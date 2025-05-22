package com.jethings.study.data.api.req_res_classes.SuperAdminModule

import com.jethings.study.data.db.entities.entities.SuperAdmin
import kotlinx.serialization.Serializable


//response

sealed class GetSuperAdminByIdResponse{
    class Success(val data : GetSuperAdminByIdSuccessResponse)  : GetSuperAdminByIdResponse()
    class Failure(val data : GetSuperAdminByIdFailureResponse)  : GetSuperAdminByIdResponse()
    class Exception(val data : kotlin.Exception)    : GetSuperAdminByIdResponse()
}

@Serializable
data class GetSuperAdminByIdSuccessResponse(
    val superAdmin : SuperAdmin
)

@Serializable
data class GetSuperAdminByIdFailureResponse(
    val statusCode : Int,
    val message    : List<String>,
    val error      : String
)