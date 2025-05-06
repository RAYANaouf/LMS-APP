package com.jethings.study.data.manager

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.jethings.study.data.api.req_res_classes.LogInRequest
import com.jethings.study.data.api.req_res_classes.LogInResponse
import com.jethings.study.data.api.req_res_classes.SignUpRequest
import com.jethings.study.data.api.req_res_classes.SignUpResponse
import com.jethings.study.domain.manager.RemoteAccountManager
import com.jethings.study.util.objects.Constants.BASE_URL
import com.jethings.study.util.objects.Constants.LOG_IN
import com.jethings.study.util.objects.Constants.SIGN_UP
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType

class RemoteAccountManager_imp(
    private val client : HttpClient
) : RemoteAccountManager {

    override suspend fun signUp(request: SignUpRequest): SignUpResponse {
        return try {
            val response = client.post(BASE_URL + SIGN_UP) {
                contentType(ContentType.Application.Json)
                setBody(request)
            }

            if ( response.status == HttpStatusCode.Created ) {
                SignUpResponse.Success(response.body())
            }else{
                SignUpResponse.Failure(response.body())
            }
        }catch (e : Exception){
            SignUpResponse.Exception(e)
        }
    }

    override suspend fun logIn(request: LogInRequest): LogInResponse {
        return try {
            val response = client.post(BASE_URL + LOG_IN) {
                contentType(ContentType.Application.Json)
                setBody(request)
            }

            if ( response.status == HttpStatusCode.OK ) {
                LogInResponse.Success(response.body())
            }else{
                LogInResponse.Failure(response.body())
            }
        }catch (e : Exception){
            LogInResponse.Exception(e)
        }
    }

}