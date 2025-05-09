package com.jethings.study.domain.manager

import com.jethings.study.data.api.req_res_classes.CreateAcademyRequest
import com.jethings.study.data.api.req_res_classes.CreateAcademyResponse
import com.jethings.study.data.api.req_res_classes.getAllAcademies.GetAllAcademiesResponse

interface AcademyManager {

    suspend fun createAcademy(createAcademyRequest: CreateAcademyRequest) : CreateAcademyResponse
    suspend fun getAllAcademies() : GetAllAcademiesResponse
    
}