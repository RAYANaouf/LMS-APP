package com.jethings.study.domain.manager

import com.jethings.study.data.api.req_res_classes.SuperAdminModule.GetAllSuperAdminResponse
import com.jethings.study.data.api.req_res_classes.createSuperAdmin.CreateSuperAdminRequest
import com.jethings.study.data.api.req_res_classes.createSuperAdmin.CreateSuperAdminResponse

interface SuperAdminManager {

    suspend fun createSuperAdmin(createSuperAdminRequest: CreateSuperAdminRequest) : CreateSuperAdminResponse

    suspend fun getAllSuperAdmins() : GetAllSuperAdminResponse

}