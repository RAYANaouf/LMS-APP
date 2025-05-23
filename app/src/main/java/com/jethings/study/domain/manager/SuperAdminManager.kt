package com.jethings.study.domain.manager

import com.jethings.study.data.api.req_res_classes.SuperAdminModule.DeleteSuperAdminByIdResponse
import com.jethings.study.data.api.req_res_classes.SuperAdminModule.GetAllSuperAdminResponse
import com.jethings.study.data.api.req_res_classes.SuperAdminModule.GetSuperAdminByIdResponse
import com.jethings.study.data.api.req_res_classes.createSuperAdmin.CreateSuperAdminRequest
import com.jethings.study.data.api.req_res_classes.createSuperAdmin.CreateSuperAdminResponse
import java.io.File

interface SuperAdminManager {

    suspend fun createSuperAdmin(createSuperAdminRequest: CreateSuperAdminRequest , profilePhoto : File?) : CreateSuperAdminResponse

    suspend fun getSuperAdminById( superAdminId : Int) : GetSuperAdminByIdResponse
    suspend fun deleteSuperAdminById( superAdminId : Int) : DeleteSuperAdminByIdResponse

    suspend fun getAllSuperAdmins() : GetAllSuperAdminResponse

}