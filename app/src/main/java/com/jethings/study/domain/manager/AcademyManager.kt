package com.jethings.study.domain.manager

import com.jethings.study.data.api.req_res_classes.CreateAcademyRequest
import com.jethings.study.data.api.req_res_classes.CreateAcademyResponse
import com.jethings.study.data.api.req_res_classes.getAcademyById.GetAcademyByIdResponse
import com.jethings.study.data.api.req_res_classes.getAllAcademies.GetAllAcademiesResponse
import java.io.File

interface AcademyManager {

    suspend fun createAcademy(createAcademyRequest: CreateAcademyRequest , logo : File?) : CreateAcademyResponse
    suspend fun getAllAcademies() : GetAllAcademiesResponse
    suspend fun getAcademyById( academyId : Int ) : GetAcademyByIdResponse
    
}