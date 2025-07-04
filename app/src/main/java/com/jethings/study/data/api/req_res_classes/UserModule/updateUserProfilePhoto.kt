package com.jethings.study.data.api.req_res_classes.UserModule

import com.jethings.study.data.db.entities.Account
import kotlinx.serialization.Serializable


//response

sealed class UpdateUserProfilePhotoResponse{
    class Success(val data : UpdateUserProfilePhotoSuccessResponse)  : UpdateUserProfilePhotoResponse()
    class Failure(val data : UpdateUserProfilePhotoFailureResponse)  : UpdateUserProfilePhotoResponse()
    class Exception(val data : kotlin.Exception)    : UpdateUserProfilePhotoResponse()
}

@Serializable
data class UpdateUserProfilePhotoSuccessResponse(
    val profilePhoto : String
)

@Serializable
data class UpdateUserProfilePhotoFailureResponse(
    val statusCode : Int,
    val message    : List<String>,
    val error      : String
)