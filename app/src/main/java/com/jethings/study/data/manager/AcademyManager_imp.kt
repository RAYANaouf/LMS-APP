package com.jethings.study.data.manager

import android.util.Log
import com.jethings.study.data.api.req_res_classes.CreateAcademyRequest
import com.jethings.study.data.api.req_res_classes.CreateAcademyResponse
import com.jethings.study.data.api.req_res_classes.getAllAcademies.GetAllAcademiesFailureResponse
import com.jethings.study.data.api.req_res_classes.getAllAcademies.GetAllAcademiesResponse
import com.jethings.study.data.api.req_res_classes.getAllAcademies.GetAllAcademiesSuccessResponse
import com.jethings.study.domain.manager.AcademyManager
import com.jethings.study.util.objects.Constants.BASE_URL
import com.jethings.study.util.objects.Constants.CREATE_ACADEMY
import com.jethings.study.util.objects.Constants.GET_ALL_ACADEMIES
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType

class AcademyManager_imp(
    private val client : HttpClient
) : AcademyManager {

    override suspend fun createAcademy(createAcademyRequest: CreateAcademyRequest): CreateAcademyResponse {
        return try {
            val response = client.post(BASE_URL + CREATE_ACADEMY) {
                contentType(ContentType.Application.Json)
                setBody(createAcademyRequest)
            }

            Log.d("response Body ", response.body())
            Log.d("response status ", "onEvent: ${response.status}")

            if ( response.status == HttpStatusCode.Created ) {
                CreateAcademyResponse.Success(response.body())
            }else{
                CreateAcademyResponse.Failure(response.body())
            }


        }catch (e : Exception){
            CreateAcademyResponse.Exception(e)
        }
    }

    override suspend fun getAllAcademies(): GetAllAcademiesResponse {
        return try {
            val response = client.get(BASE_URL + GET_ALL_ACADEMIES) {
                contentType(ContentType.Application.Json)
            }
            Log.d("response get all academies : status : " , response.status.toString())
            Log.d("response get all academies : " , response.body())
            if (response.status == HttpStatusCode.OK ) {
                GetAllAcademiesResponse.Success(GetAllAcademiesSuccessResponse(response.body()))
            } else  {
                GetAllAcademiesResponse.Failure(response.body())
            }

        } catch (e: Exception) {
            GetAllAcademiesResponse.Exception(e)
        }
    }
}