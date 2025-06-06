package com.jethings.study.data.manager

import android.util.Log
import com.jethings.study.data.api.req_res_classes.AcademyModule.addAcademyOwner.AddAcademyOwnerRequest
import com.jethings.study.data.api.req_res_classes.AcademyModule.addAcademyOwner.AddAcademyOwnerResponse
import com.jethings.study.data.api.req_res_classes.AcademyModule.addAcademyOwner.AddAcademyOwnerSuccessResponse
import com.jethings.study.data.api.req_res_classes.AcademyModule.getAcademyById.GetAcademiesByOwnerIdResponse
import com.jethings.study.data.api.req_res_classes.AcademyModule.getAcademyById.GetAcademiesByOwnerIdSuccessResponse
import com.jethings.study.data.api.req_res_classes.CreateAcademyRequest
import com.jethings.study.data.api.req_res_classes.CreateAcademyResponse
import com.jethings.study.data.api.req_res_classes.AcademyModule.getAcademyById.GetAcademyByIdResponse
import com.jethings.study.data.api.req_res_classes.AcademyModule.getAcademyById.GetAcademyOwnersFailureResponse
import com.jethings.study.data.api.req_res_classes.AcademyModule.getAcademyById.GetAcademyOwnersResponse
import com.jethings.study.data.api.req_res_classes.AcademyModule.getAcademyById.GetAcademyOwnersSuccessResponse
import com.jethings.study.data.api.req_res_classes.AcademyModule.getAllAcademies.GetAllAcademiesResponse
import com.jethings.study.data.api.req_res_classes.AcademyModule.getAllAcademies.GetAllAcademiesSuccessResponse
import com.jethings.study.domain.manager.AcademyManager
import com.jethings.study.util.objects.Constants.ADD_ACADEMY_OWNER
import com.jethings.study.util.objects.Constants.BASE_URL
import com.jethings.study.util.objects.Constants.CREATE_ACADEMY
import com.jethings.study.util.objects.Constants.GET_ACADEMIES_BY_OWNER_ID
import com.jethings.study.util.objects.Constants.GET_ACADEMY_BY_ID
import com.jethings.study.util.objects.Constants.GET_ACADEMY_OWNERS_BY_ID
import com.jethings.study.util.objects.Constants.GET_ALL_ACADEMIES
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import java.io.File

class AcademyManager_imp(
    private val client : HttpClient
) : AcademyManager {

    override suspend fun createAcademy(createAcademyRequest: CreateAcademyRequest, logo : File?): CreateAcademyResponse {

        return try {
            val response = client.submitFormWithBinaryData(
                url = BASE_URL + CREATE_ACADEMY,
                formData = formData {
                    append("name" , createAcademyRequest.name)

                    //only append image if file is not null
                    logo?.let{
                        append("logo" , it.readBytes() , Headers.build {
                            append(HttpHeaders.ContentType , "image/jpg")
                            append(HttpHeaders.ContentDisposition , "filename=${logo.name}")
                        })
                    }
                }
            )

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
            if (response.status == HttpStatusCode.OK ) {
                GetAllAcademiesResponse.Success(GetAllAcademiesSuccessResponse(response.body()))
            } else  {
                GetAllAcademiesResponse.Failure(response.body())
            }

        } catch (e: Exception) {
            GetAllAcademiesResponse.Exception(e)
        }
    }

    override suspend fun getAcademyById(academyId: Int): GetAcademyByIdResponse {
        return try {

            val response = client.get( BASE_URL + GET_ACADEMY_BY_ID + academyId ){
                contentType(ContentType.Application.Json)
            }
            if (response.status == HttpStatusCode.OK ) {
                GetAcademyByIdResponse.Success(response.body())
            }else{
                GetAcademyByIdResponse.Failure(response.body())
            }

        }catch (e : Exception){
            GetAcademyByIdResponse.Exception(e)
        }
    }

    override suspend fun getAcademyOwners(academyId: Int): GetAcademyOwnersResponse {
        return try {
            val path = GET_ACADEMY_OWNERS_BY_ID.replace("{id}" , academyId.toString())
            val response = client.get( BASE_URL + path){
                contentType(ContentType.Application.Json)
            }
            if (response.status == HttpStatusCode.OK ) {

                GetAcademyOwnersResponse.Success(response.body())

            }else{
                GetAcademyOwnersResponse.Failure(response.body())
            }

        }catch (e : Exception){
            Log.d("get academy owners" , e.toString())
            GetAcademyOwnersResponse.Exception(e)
        }
    }

    override suspend fun addAcademyOwner(academyId: Int, ownerId: Int): AddAcademyOwnerResponse {
        return try {
            val path = ADD_ACADEMY_OWNER.replace("{id}" , academyId.toString())
            val response = client.post( BASE_URL + path){
                contentType(ContentType.Application.Json)
                setBody(AddAcademyOwnerRequest(userId = ownerId))
            }
            if (response.status == HttpStatusCode.Created ) {
                AddAcademyOwnerResponse.Success(AddAcademyOwnerSuccessResponse() )
            }else{
                AddAcademyOwnerResponse.Failure(response.body())
            }

        }catch (e : Exception){
            Log.d("add academy owner" , e.toString())
            AddAcademyOwnerResponse.Exception(e)
        }
    }

    override suspend fun getAcademiesByOwnerId(ownerId: Long): GetAcademiesByOwnerIdResponse {
        return try {
            val path = GET_ACADEMIES_BY_OWNER_ID.replace("{id}" , ownerId.toString())
            val response = client.get( BASE_URL + path){
                contentType(ContentType.Application.Json)
            }
            if (response.status == HttpStatusCode.OK ) {
                val result = GetAcademiesByOwnerIdResponse.Success(data = GetAcademiesByOwnerIdSuccessResponse(response.body()))
                result
            }else{
                GetAcademiesByOwnerIdResponse.Failure(response.body())
            }

        }catch (e : Exception){
            Log.d("get academies by owner id" , e.toString())
            GetAcademiesByOwnerIdResponse.Exception(e)
        }
    }
}