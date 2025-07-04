package com.jethings.study.domain.manager

import com.jethings.study.data.api.req_res_classes.UserModule.GetUserByEmailResponse
import com.jethings.study.data.api.req_res_classes.UserModule.UpdateUserProfilePhotoResponse
import java.io.File

interface UserManager {

    suspend fun getUserByEmail(email : String) : GetUserByEmailResponse

    suspend fun updateUserProfilePhoto(userId : Long , profilePhoto : File?) : UpdateUserProfilePhotoResponse
}