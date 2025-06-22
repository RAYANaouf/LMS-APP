package com.jethings.study.domain.manager

import com.jethings.study.data.api.req_res_classes.PostModule.createPost.CreatePostRequest
import com.jethings.study.data.api.req_res_classes.PostModule.createPost.CreatePostResponse
import com.jethings.study.data.api.req_res_classes.PostModule.getAllByAcademy.GetAllByAcademyResponse
import com.jethings.study.data.api.req_res_classes.PostModule.getAllPost.GetAllPostsResponse
import java.io.File

interface PostManager {

    suspend fun createPost( request : CreatePostRequest , cover : File?) : CreatePostResponse
    suspend fun getAllByAcademy( academyId : Int ) : GetAllByAcademyResponse
    suspend fun getAll() : GetAllPostsResponse

}