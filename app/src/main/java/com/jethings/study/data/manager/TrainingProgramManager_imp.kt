package com.jethings.study.data.manager

import android.util.Log
import com.jethings.study.data.api.req_res_classes.CreateAcademyResponse
import com.jethings.study.data.api.req_res_classes.TrainingProgramModule.createTrainingProgram.CreateTrainingProgramRequest
import com.jethings.study.data.api.req_res_classes.TrainingProgramModule.createTrainingProgram.CreateTrainingProgramResponse
import com.jethings.study.data.api.req_res_classes.TrainingProgramModule.getAllByAcademy.GetAllByAcademyResponse
import com.jethings.study.data.api.req_res_classes.TrainingProgramModule.getAllByAcademy.GetAllByAcademySuccessResponse
import com.jethings.study.data.api.req_res_classes.TrainingProgramModule.getAllTrainingProgram.GetAllTrainingProgramFailureResponse
import com.jethings.study.data.api.req_res_classes.TrainingProgramModule.getAllTrainingProgram.GetAllTrainingProgramResponse
import com.jethings.study.data.api.req_res_classes.TrainingProgramModule.getAllTrainingProgram.GetAllTrainingProgramSuccessResponse
import com.jethings.study.data.api.req_res_classes.TrainingProgramModule.requestForCourse.RequestForCourseRequest
import com.jethings.study.data.api.req_res_classes.TrainingProgramModule.requestForCourse.RequestForCourseResponse
import com.jethings.study.domain.manager.TrainingProgramManager
import com.jethings.study.presentation.view.screens.AcademyHome.components.AcademicPhases.sections.Header
import com.jethings.study.util.objects.Constants.BASE_URL
import com.jethings.study.util.objects.Constants.CREATE_ACADEMY
import com.jethings.study.util.objects.Constants.CREATE_COURSE
import com.jethings.study.util.objects.Constants.GET_ALL_COURSE
import com.jethings.study.util.objects.Constants.GET_ALL_COURSE_BY_ACADEMY
import com.jethings.study.util.objects.Constants.REQUEST_FOR_COURSE
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.request
import io.ktor.client.utils.EmptyContent.headers
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.http.headers
import java.io.File

class TrainingProgramManager_imp(
    private val client : HttpClient
) : TrainingProgramManager {

    override suspend fun createTrainingProgram(request: CreateTrainingProgramRequest , cover : File?): CreateTrainingProgramResponse {
        return try {
            val response = client.submitFormWithBinaryData(
                url = BASE_URL + CREATE_COURSE,
                formData = formData {
                    append("academyId" , request.academyId)
                    append("name" , request.name)
                    append("description" , request.description)
                    append("targetAudience" , request.targetAudience       ?: "")
                    append("prerequisites" , request.prerequisites         ?: "")
                    append("whatYouWillLearn" , request.whatYouWillLearn   ?: "")
                    append("whatYouCanDoAfter" , request.whatYouCanDoAfter ?: "")

                    append("minAge" , request.minAge ?: -1)
                    append("maxAge" , request.maxAge ?: -1)
                    append("price" , request.price )

                    //only append image if file is not null
                    cover?.let{
                        append("cover" , it.readBytes() , Headers.build {
                            append(HttpHeaders.ContentType , "image/jpg")
                            append(HttpHeaders.ContentDisposition , "filename=${cover.name}")
                        })
                    }
                }
            )

            Log.d("create training program result : " , response.body() )
            Log.d("the body  " , request.toString() )

            Log.d("the status  " , response.status.toString() )

            if ( response.status == HttpStatusCode.Created ) {
                CreateTrainingProgramResponse.Success(response.body())
            }else{
                CreateTrainingProgramResponse.Failure(response.body())
            }


        }
        catch (e : Exception){
            Log.d("exception create training program  " , e.toString() )
            CreateTrainingProgramResponse.Exception(e)
        }
    }

    override suspend fun getAllByAcademy(academyId: Int): GetAllByAcademyResponse {
        return try{
            var path =  GET_ALL_COURSE_BY_ACADEMY.replace("{id}" , academyId.toString())
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

    override suspend fun getAll(userToken: String?): GetAllTrainingProgramResponse {
        return try {
            val response = client.request {
                method = HttpMethod.Get
                url(BASE_URL + GET_ALL_COURSE)

                contentType(ContentType.Application.Json)

                Log.d("heeere ","heree")

                this.headers.append(HttpHeaders.Authorization  , "Bearer $userToken")

            }

            // ✅ Explicit check
            val auth = response.request.headers[HttpHeaders.Authorization]
            Log.d("debug the header ====>>", auth ?: "Authorization not set")
            Log.d("debug all header ====>>", "${response.request.headers.names()}")

            if (response.status == HttpStatusCode.OK) {
                val res = GetAllTrainingProgramSuccessResponse(response.body())
                Log.d("response of jwt get course ====>", res.trainingPrograms.toString())
                GetAllTrainingProgramResponse.Success(data = res)
            } else {
                GetAllTrainingProgramResponse.Failure(data = response.body())
            }
        } catch (e: Exception) {
            Log.e("response of jwt get course Exception", "error => ${e.localizedMessage}", e)
            Log.e("response of jwt get course Exception", "error => ${e.cause} ${e.message} ", e)
            GetAllTrainingProgramResponse.Exception(e)
        }
    }


    override suspend fun requestForCourse(request: RequestForCourseRequest): RequestForCourseResponse {
        return try {
            val response = client.post(BASE_URL + REQUEST_FOR_COURSE){
                contentType(ContentType.Application.Json)
                setBody(request)
            }

            if(response.status == HttpStatusCode.Created){
                Log.d("request for course Success" , response.body())
                RequestForCourseResponse.Success(true)
            }else{
                Log.d("request for course Failure" , response.body())
                RequestForCourseResponse.Failed(response.body())
            }
        }catch (e : Exception){

            Log.d("request for course Exception" , e.toString())
            RequestForCourseResponse.Exception(e)
        }
    }

}