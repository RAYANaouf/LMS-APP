package com.jethings.study.data.manager

import android.util.Log
import com.jethings.study.data.api.req_res_classes.CreateAcademyResponse
import com.jethings.study.data.api.req_res_classes.TrainingProgramModule.createTrainingProgram.CreateTrainingProgramRequest
import com.jethings.study.data.api.req_res_classes.TrainingProgramModule.createTrainingProgram.CreateTrainingProgramResponse
import com.jethings.study.data.api.req_res_classes.TrainingProgramModule.getAllByAcademy.GetAllByAcademyResponse
import com.jethings.study.data.api.req_res_classes.TrainingProgramModule.getAllByAcademy.GetAllByAcademySuccessResponse
import com.jethings.study.domain.manager.TrainingProgramManager
import com.jethings.study.util.objects.Constants.BASE_URL
import com.jethings.study.util.objects.Constants.CREATE_ACADEMY
import com.jethings.study.util.objects.Constants.CREATE_TrainingProgram
import com.jethings.study.util.objects.Constants.GET_ALL_TrainingProgram_BY_ACADEMY
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

class TrainingProgramManager_imp(
    private val client : HttpClient
) : TrainingProgramManager {

    override suspend fun createTrainingProgram(request: CreateTrainingProgramRequest , cover : File?): CreateTrainingProgramResponse {
        return try {
            val response = client.submitFormWithBinaryData(
                url = BASE_URL + CREATE_TrainingProgram,
                formData = formData {
                    append("academyId" , request.academyId)
                    append("name" , request.name)
                    append("description" , request.description)

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


        }catch (e : Exception){
            Log.d("exception create training program  " , e.toString() )
            CreateTrainingProgramResponse.Exception(e)
        }
    }

    override suspend fun getAllByAcademy(academyId: Int): GetAllByAcademyResponse {
        return try{
            var path =  GET_ALL_TrainingProgram_BY_ACADEMY.replace("{id}" , academyId.toString())
            val  response = client.get( BASE_URL + path ){
                contentType(ContentType.Application.Json)
            }

            Log.d("the top result :  " , response.body<String>().toString())

            if (response.status == HttpStatusCode.OK ) {
                GetAllByAcademyResponse.Success( data = GetAllByAcademySuccessResponse(response.body()))
            }else{
                GetAllByAcademyResponse.Failure(response.body())
            }
        }catch (e : Exception){

            Log.d("the exception get training programs by academy " , e.toString() )
            GetAllByAcademyResponse.Exception(e)
        }
    }

}