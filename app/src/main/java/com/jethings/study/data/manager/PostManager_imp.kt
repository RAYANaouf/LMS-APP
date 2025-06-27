package com.jethings.study.data.manager

import android.util.Log
import com.jethings.study.data.api.req_res_classes.PostModule.createPost.CreatePostRequest
import com.jethings.study.data.api.req_res_classes.PostModule.createPost.CreatePostResponse
import com.jethings.study.data.api.req_res_classes.PostModule.getAllByAcademy.GetAllByAcademyResponse
import com.jethings.study.data.api.req_res_classes.PostModule.getAllByAcademy.GetAllByAcademySuccessResponse
import com.jethings.study.data.api.req_res_classes.PostModule.getAllPost.GetAllPostsResponse
import com.jethings.study.data.api.req_res_classes.PostModule.getAllPost.GetAllPostsSuccessResponse
import com.jethings.study.domain.manager.PostManager
import com.jethings.study.util.objects.Constants.BASE_URL
import com.jethings.study.util.objects.Constants.CREATE_POST
import com.jethings.study.util.objects.Constants.GET_ALL_POST
import com.jethings.study.util.objects.Constants.GET_ALL_POST_BY_ACADEMY
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import java.io.File


class PostManager_imp(
    private val client : HttpClient
) : PostManager {


    override suspend fun createPost(request: CreatePostRequest, cover: File?): CreatePostResponse {
        return try {
            val response = client.submitFormWithBinaryData(
                url = BASE_URL + CREATE_POST,
                formData = formData {
                    append("academyId" , request.academyId)
                    append("title" , request.title)
                    append("content" , request.content)

                    //only append image if file is not null
                    cover?.let{
                        append("cover" , it.readBytes() , Headers.build {
                            append(HttpHeaders.ContentType , "image/jpg")
                            append(HttpHeaders.ContentDisposition , "filename=${cover.name}")
                        })
                    }
                }
            )

            if ( response.status == HttpStatusCode.Created ) {
                Log.d("create post request : " , response.status.value.toString())
                CreatePostResponse.Success(response.body())
            }else{
                Log.d("create post request (error) : " , request.toString())
                Log.d("create post request (error) : " , response.body<String>().toString())
                Log.d("create post request (error) : " , response.status.value.toString())
                CreatePostResponse.Failure(response.body())
            }


        }
        catch (e : Exception){
            Log.d("exception create training program  " , e.toString() )
            CreatePostResponse.Exception(e)
        }
    }

    override suspend fun getAllByAcademy(academyId: Int): GetAllByAcademyResponse {
        return try{
            var path =  GET_ALL_POST_BY_ACADEMY.replace("{id}" , academyId.toString())
            val  response = client.get( BASE_URL + path ){
                contentType(ContentType.Application.Json)
            }

            Log.d("the top result :  " , response.body<String>().toString())

            if (response.status == HttpStatusCode.OK ) {
                GetAllByAcademyResponse.Success( data = GetAllByAcademySuccessResponse(response.body()))
            }else{
                GetAllByAcademyResponse.Failure(response.body())
            }
        }
        catch (e : Exception){

            Log.d("the exception get training programs by academy " , e.toString() )
            GetAllByAcademyResponse.Exception(e)
        }
    }

    override suspend fun getAll(): GetAllPostsResponse {
        return try {
            val response = client.get(BASE_URL + GET_ALL_POST){
                contentType(ContentType.Application.Json)
            }

            if (response.status == HttpStatusCode.OK){
                GetAllPostsResponse.Success(data = GetAllPostsSuccessResponse(response.body()))
            }else{
                GetAllPostsResponse.Failure(data = response.body())
            }
        }catch (e : Exception){
            GetAllPostsResponse.Exception(e)
        }
    }

}