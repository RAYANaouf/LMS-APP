package com.jethings.study.domain.manager

import com.jethings.study.data.api.req_res_classes.UserModule.GetUserByEmailResponse

interface UserManager {

    suspend fun getUserByEmail(email : String) : GetUserByEmailResponse
}