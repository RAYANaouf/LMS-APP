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
import com.jethings.study.domain.manager.SuperAdminManager
import com.jethings.study.util.objects.Constants.BASE_URL
import com.jethings.study.util.objects.Constants.CREATE_SUPER_ADMIN
import com.jethings.study.util.objects.Constants.DELETE_SUPER_ADMIN_BY_ID
import com.jethings.study.util.objects.Constants.GET_ACADEMY_BY_ID
import com.jethings.study.util.objects.Constants.GET_ALL_SUPER_ADMIN
import com.jethings.study.util.objects.Constants.GET_SUPER_ADMIN_BY_ID
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

class SuperAcademyManager_imp(
    private val client : HttpClient
) : SuperAdminManager {

    override suspend fun createSuperAdmin(createSuperAdminRequest : CreateSuperAdminRequest, profilePhoto : File?) : CreateSuperAdminResponse {
            return try{

                val response  = client.submitFormWithBinaryData(
                    url = BASE_URL + CREATE_SUPER_ADMIN ,
                    formData = formData {
                        append("firstName" , createSuperAdminRequest.firstName ?: "")
                        append("lastName"  , createSuperAdminRequest.lastName  ?: "")
                        append("email"     , createSuperAdminRequest.email     ?: "")
                        append("password"  , createSuperAdminRequest.password  ?: "")

                        //only append image if file is not null
                        profilePhoto?.let{
                            append("profilePhoto" , it.readBytes() , Headers.build {
                                append(HttpHeaders.ContentType , "image/jpg")
                                append(HttpHeaders.ContentDisposition , "filename=${profilePhoto.name}")
                            })
                        }
                    }
                )

                if (response.status.value == HttpStatusCode.Created.value){
                    CreateSuperAdminResponse.Success( data = response.body())
                }else{
                    CreateSuperAdminResponse.Failure(response.body())
                }
            }catch (e : Exception){
                Log.d("catch ", "createSuperAdmin: ${e}")
                CreateSuperAdminResponse.Exception(e)
            }
    }

    override suspend fun getAllSuperAdmins(): GetAllSuperAdminResponse {
        return try{
            val response  = client.get( BASE_URL + GET_ALL_SUPER_ADMIN){
                contentType(ContentType.Application.Json)
            }

            Log.d("ow response status ", "createSuperAdmin: ${response.status.value}")
            Log.d("ow response obj", "createSuperAdmin: ${response}")

            if(response.status.value == 200){
                Log.d("ow response obj", "createSuperAdmin: ${response.body<GetAllSuperAdminSuccessResponse>()}")
                GetAllSuperAdminResponse.Success(data = response.body())

            }else{
                Log.d("ow response obj", "createSuperAdmin: ${response.body<GetAllSuperAdminFailureResponse>()}")

                GetAllSuperAdminResponse.Failure(data = response.body())

            }

        }catch (e : Exception){
            Log.d("Catch" , "get all super admins: ${e}")
            GetAllSuperAdminResponse.Exception(e)
        }
    }




    override suspend fun getSuperAdminById(superAdminId: Int): GetSuperAdminByIdResponse {

        return try {
            val response = client.get( BASE_URL + GET_SUPER_ADMIN_BY_ID + superAdminId ){
                contentType(ContentType.Application.Json)
            }
            if (response.status == HttpStatusCode.OK ) {
                GetSuperAdminByIdResponse.Success(data = GetSuperAdminByIdSuccessResponse(response.body()) )
            }else{
                GetSuperAdminByIdResponse.Failure(response.body())
            }

        }catch (e : Exception){
            GetSuperAdminByIdResponse.Exception(e)
        }

    }

    override suspend fun deleteSuperAdminById( superAdminId: Int): DeleteSuperAdminByIdResponse {
        return try {
            val response = client.delete( BASE_URL + DELETE_SUPER_ADMIN_BY_ID + superAdminId ){
                contentType(ContentType.Application.Json)
            }
            if (response.status == HttpStatusCode.OK ) {
                DeleteSuperAdminByIdResponse.Success(response.body())
            }else{
                DeleteSuperAdminByIdResponse.Failure(response.body())
            }
        }catch ( exception : Exception ){
            DeleteSuperAdminByIdResponse.Exception(exception)
        }
    }

}