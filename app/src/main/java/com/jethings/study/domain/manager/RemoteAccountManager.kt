package com.jethings.study.domain.manager

import com.jethings.study.data.api.req_res_classes.LogInRequest
import com.jethings.study.data.api.req_res_classes.LogInResponse
import com.jethings.study.data.api.req_res_classes.SignUpRequest
import com.jethings.study.data.api.req_res_classes.SignUpResponse


interface RemoteAccountManager {

    suspend fun signUp(request : SignUpRequest) : SignUpResponse
    suspend fun logIn(request : LogInRequest) : LogInResponse

}