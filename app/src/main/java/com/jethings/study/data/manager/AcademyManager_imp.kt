package com.jethings.study.data.manager

import com.jethings.study.data.api.req_res_classes.CreateAcademyRequest
import com.jethings.study.data.api.req_res_classes.CreateAcademyResponse
import com.jethings.study.domain.manager.AcademyManager
import com.jethings.study.util.objects.Constants.BASE_URL
import com.jethings.study.util.objects.Constants.CREATE_ACADEMY
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.call.body
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

            if ( response.status == HttpStatusCode.Created ) {
                CreateAcademyResponse.Success(response.body())
            }else{
                CreateAcademyResponse.Failure(response.body())
            }


        }catch (e : Exception){
            CreateAcademyResponse.Exception(e)
        }
    }

}