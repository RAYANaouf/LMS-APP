package com.jethings.study.data.api.req_res_classes.PostModule.createPost

import kotlinx.serialization.Serializable


// request
@Serializable
data class CreatePostRequest(
    val academyId          : Long,
    val title              : String ,
    val content            : String ,
)


//response
@Serializable
sealed class CreatePostResponse{
    class Success(val data : CreatePostSuccessResponse)  : CreatePostResponse()
    class Failure(val data : CreatePostFailureResponse)  : CreatePostResponse()
    class Exception(val data : kotlin.Exception)         : CreatePostResponse()
}

@Serializable
data class CreatePostSuccessResponse(
    val id               : Int     = 0
)

@Serializable
data class CreatePostFailureResponse(
    val statusCode : Int,
    val message    : List<String>,
    val error      : String
)