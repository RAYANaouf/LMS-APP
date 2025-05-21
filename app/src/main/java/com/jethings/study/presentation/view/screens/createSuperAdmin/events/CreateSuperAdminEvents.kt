package com.jethings.study.presentation.view.screens.createSuperAdmin.events

import com.jethings.study.data.api.req_res_classes.createSuperAdmin.CreateSuperAdminRequest
import java.io.File


sealed class CreateSuperAdminEvents {

    class CreateSuperAdmin(val createSuperAdminRequest: CreateSuperAdminRequest , val profilePhoto : File?) : CreateSuperAdminEvents()

}