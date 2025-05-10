package com.jethings.study.data.manager

import android.util.Log
import com.jethings.study.data.api.req_res_classes.createSuperAdmin.CreateSuperAdminRequest
import com.jethings.study.data.api.req_res_classes.createSuperAdmin.CreateSuperAdminResponse
import com.jethings.study.domain.manager.SuperAdminManager
import com.jethings.study.util.objects.Constants.BASE_URL
import com.jethings.study.util.objects.Constants.CREATE_SUPER_ADMIN
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class SuperAcademyManager_imp(
    private val client : HttpClient
) : SuperAdminManager {

    override suspend fun createSuperAdmin(createSuperAdminRequest: CreateSuperAdminRequest) : CreateSuperAdminResponse {
            return try{
                val response  = client.post(  BASE_URL + CREATE_SUPER_ADMIN ){
                    contentType(ContentType.Application.Json)
                    setBody(createSuperAdminRequest)
                }

                Log.d("ow response status ", "createSuperAdmin: ${response.status.value}")
                Log.d("ow response obj", "createSuperAdmin: ${response}")

                if (response.status.value == 201){
                    CreateSuperAdminResponse.Success( data = response.body())
                }else{
                    CreateSuperAdminResponse.Failure(response.body())
                }
            }catch (e : Exception){
                Log.d("catch ", "createSuperAdmin: ${e}")
                CreateSuperAdminResponse.Exception(e)
            }
    }

}