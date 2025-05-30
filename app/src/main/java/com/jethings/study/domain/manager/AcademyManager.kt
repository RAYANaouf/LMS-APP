package com.jethings.study.domain.manager

import com.jethings.study.data.api.req_res_classes.AcademyModule.addAcademyOwner.AddAcademyOwnerResponse
import com.jethings.study.data.api.req_res_classes.AcademyModule.getAcademyById.GetAcademiesByOwnerIdResponse
import com.jethings.study.data.api.req_res_classes.CreateAcademyRequest
import com.jethings.study.data.api.req_res_classes.CreateAcademyResponse
import com.jethings.study.data.api.req_res_classes.AcademyModule.getAcademyById.GetAcademyByIdResponse
import com.jethings.study.data.api.req_res_classes.AcademyModule.getAcademyById.GetAcademyOwnersResponse
import com.jethings.study.data.api.req_res_classes.AcademyModule.getAllAcademies.GetAllAcademiesResponse
import java.io.File

interface AcademyManager {

    suspend fun createAcademy(createAcademyRequest: CreateAcademyRequest , logo : File?) : CreateAcademyResponse
    suspend fun getAllAcademies() : GetAllAcademiesResponse
    suspend fun getAcademyById( academyId : Int ) : GetAcademyByIdResponse
    suspend fun getAcademyOwners( academyId : Int ) : GetAcademyOwnersResponse
    suspend fun addAcademyOwner( academyId : Int , ownerId : Int ) : AddAcademyOwnerResponse


    suspend fun getAcademiesByOwnerId( ownerId: Long ) : GetAcademiesByOwnerIdResponse
}