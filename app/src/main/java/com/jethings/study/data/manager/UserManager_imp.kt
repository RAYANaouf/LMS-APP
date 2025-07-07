package com.jethings.study.data.manager

import android.util.Log
import com.jethings.study.data.api.req_res_classes.SuperAdminModule.DeleteSuperAdminByIdResponse
import com.jethings.study.data.api.req_res_classes.SuperAdminModule.GetAllSuperAdminFailureResponse
import com.jethings.study.data.api.req_res_classes.SuperAdminModule.GetAllSuperAdminResponse
import com.jethings.study.data.api.req_res_classes.SuperAdminModule.GetAllSuperAdminSuccessResponse
import com.jethings.study.data.api.req_res_classes.SuperAdminModule.GetSuperAdminByIdResponse
import com.jethings.study.data.api.req_res_classes.SuperAdminModule.GetSuperAdminByIdSuccessResponse
import com.jethings.study.data.api.req_res_classes.AuthModule.createSuperAdmin.CreateSuperAdminRequest
import com.jethings.study.data.api.req_res_classes.AuthModule.createSuperAdmin.CreateSuperAdminResponse
import com.jethings.study.data.api.req_res_classes.AcademyModule.getAcademyById.GetAcademyByIdResponse
import com.jethings.study.data.api.req_res_classes.CreateAcademyResponse
import com.jethings.study.data.api.req_res_classes.UserModule.GetUserByEmailResponse
import com.jethings.study.data.api.req_res_classes.UserModule.GetUserByEmailSuccessResponse
import com.jethings.study.data.api.req_res_classes.UserModule.UpdateUserProfilePhotoResponse
import com.jethings.study.domain.manager.SuperAdminManager
import com.jethings.study.domain.manager.UserManager
import com.jethings.study.util.objects.Constants.BASE_URL
import com.jethings.study.util.objects.Constants.CREATE_ACADEMY
import com.jethings.study.util.objects.Constants.CREATE_SUPER_ADMIN
import com.jethings.study.util.objects.Constants.DELETE_SUPER_ADMIN_BY_ID
import com.jethings.study.util.objects.Constants.GET_ACADEMY_BY_ID
import com.jethings.study.util.objects.Constants.GET_ALL_SUPER_ADMIN
import com.jethings.study.util.objects.Constants.GET_SUPER_ADMIN_BY_ID
import com.jethings.study.util.objects.Constants.GET_USER_BY_EMAIL
import com.jethings.study.util.objects.Constants.UPDATE_PROFILE_PHOTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import java.io.File

class UserManager_imp(
    private val client : HttpClient
) : UserManager {


    override suspend fun getUserByEmail(email: String): GetUserByEmailResponse {
        return try{
            val path = GET_USER_BY_EMAIL.replace("{email}" , email)
            val response = client.get(BASE_URL + path){
                contentType(ContentType.Application.Json)
            }
            if (response.status == HttpStatusCode.OK){
                GetUserByEmailResponse.Success(data = GetUserByEmailSuccessResponse(response.body()))
            }else{
                GetUserByEmailResponse.Failure(response.body())
            }
        }catch (e : Exception){
            Log.d("get user by id" , e.toString())
            GetUserByEmailResponse.Exception(e)
        }
    }

    override suspend fun updateUserProfilePhoto(userId: Long , profilePhoto : File?): UpdateUserProfilePhotoResponse {
        return try {

            Log.d("updating ..  ", "step1")
            var url = BASE_URL + UPDATE_PROFILE_PHOTO
            url = url.replace("{id}" , userId.toString())

            val response = client.submitFormWithBinaryData(
                url = url,
                formData = formData {
                    //only append image if file is not null
                    profilePhoto?.let{
                        append("profilePhoto" , it.readBytes() , Headers.build {
                            append(HttpHeaders.ContentType , "image/jpg")
                            append(HttpHeaders.ContentDisposition , "filename=${profilePhoto.name}")
                        })
                    }
                }
            )
            if ( response.status == HttpStatusCode.Created ) {
                Log.d("update success : ", response.body())
                UpdateUserProfilePhotoResponse.Success(response.body())
            }else{
                Log.d("update failed : ", response.body())
                UpdateUserProfilePhotoResponse.Failure(response.body())
            }


        }catch (e : Exception){
            UpdateUserProfilePhotoResponse.Exception(e)
        }
    }

}