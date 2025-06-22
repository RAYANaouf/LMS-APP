package com.jethings.study.data.api.req_res_classes.PostModule.getAllPost

import com.jethings.study.data.db.entities.entities.Academy
import com.jethings.study.data.db.entities.entities.Post
import com.jethings.study.data.db.entities.entities.TrainingProgram
import kotlinx.serialization.Serializable





//response

sealed class GetAllPostsResponse{
    class Success(val data : GetAllPostsSuccessResponse)  : GetAllPostsResponse()
    class Failure(val data : GetAllPostsFailureResponse)  : GetAllPostsResponse()
    class Exception(val data : kotlin.Exception)                    : GetAllPostsResponse()
}

@Serializable
data class GetAllPostsSuccessResponse(
    val posts : List<Post>
)

@Serializable
data class GetAllPostsFailureResponse(
    val statusCode : Int,
    val message    : List<String>,
    val error      : String
)