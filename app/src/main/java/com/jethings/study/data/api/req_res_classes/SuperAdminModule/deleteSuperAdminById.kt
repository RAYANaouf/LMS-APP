package com.jethings.study.data.api.req_res_classes.SuperAdminModule

import com.jethings.study.data.db.entities.entities.SuperAdmin
import kotlinx.serialization.Serializable




//response

sealed class DeleteSuperAdminByIdResponse{
    class Success(val data : DeleteSuperAdminByIdSuccessResponse)  : DeleteSuperAdminByIdResponse()
    class Failure(val data : DeleteSuperAdminByIdFailureResponse)  : DeleteSuperAdminByIdResponse()
    class Exception(val data : kotlin.Exception)    : DeleteSuperAdminByIdResponse()
}

@Serializable
data class DeleteSuperAdminByIdSuccessResponse(
    val deleted : Boolean
)

@Serializable
data class DeleteSuperAdminByIdFailureResponse(
    val statusCode : Int,
    val message    : List<String>,
    val error      : String
)