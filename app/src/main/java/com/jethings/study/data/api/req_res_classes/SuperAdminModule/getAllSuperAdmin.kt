package com.jethings.study.data.api.req_res_classes.SuperAdminModule

import com.jethings.study.data.db.entities.entities.SuperAdmin
import kotlinx.serialization.Serializable


//response
@Serializable
sealed class GetAllSuperAdminResponse{
    class Success(val data : GetAllSuperAdminSuccessResponse)  : GetAllSuperAdminResponse()
    class Failure(val data : GetAllSuperAdminFailureResponse)  : GetAllSuperAdminResponse()
    class Exception(val data : kotlin.Exception)    : GetAllSuperAdminResponse()
}

@Serializable
data class GetAllSuperAdminSuccessResponse(
    val superAdminList : List<SuperAdmin>
)

@Serializable
data class GetAllSuperAdminFailureResponse(
    val statusCode : Int,
    val message    : List<String>,
    val error      : String
)