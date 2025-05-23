package com.jethings.study.presentation.view.screens.superAdmin.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jethings.study.data.api.req_res_classes.SuperAdminModule.DeleteSuperAdminByIdResponse
import com.jethings.study.data.api.req_res_classes.SuperAdminModule.GetSuperAdminByIdResponse
import com.jethings.study.data.api.req_res_classes.getAcademyById.GetAcademyByIdResponse
import com.jethings.study.data.api.req_res_classes.getAcademyById.GetAcademyByIdSuccessResponse
import com.jethings.study.data.db.entities.entities.Academy
import com.jethings.study.data.db.entities.entities.SuperAdmin
import com.jethings.study.domain.manager.AcademyManager
import com.jethings.study.domain.manager.SuperAdminManager
import com.jethings.study.presentation.view.screens.academy.events.AcademyEvent
import com.jethings.study.presentation.view.screens.superAdmin.events.SuperAdminEvent
import kotlinx.coroutines.launch

class SuperAdminViewModel(
    private val superAdminManager: SuperAdminManager
) : ViewModel() {

    var suprAdmin by mutableStateOf<SuperAdmin?>(null)
        private set

    fun onEvent( event : SuperAdminEvent , onSuccess : ()->Unit = {} , onFailure : ()->Unit = {}){
        when(event){
            is SuperAdminEvent.GetSuperAdminDetails -> {
                viewModelScope.launch {
                    val result = superAdminManager.getSuperAdminById(event.superAdmin_id)
                    if(result is GetSuperAdminByIdResponse.Success){
                        Log.d("to me ==>", "onEvent1: ${result.data.superAdmin.firstName}")
                        suprAdmin = SuperAdmin(id = result.data.superAdmin.id, firstName = result.data.superAdmin.firstName , lastName = result.data.superAdmin.lastName , profilePhoto = result.data.superAdmin.profilePhoto)
                        onSuccess()
                    }else if(result is GetSuperAdminByIdResponse.Failure){
                        Log.d("to me ==>", "onEvent2: ${result.data.message}")
                        onFailure()
                    }else if(result is GetSuperAdminByIdResponse.Exception){
                        Log.d("to me ==>", "onEvent3: ${result.data.message}")
                        onFailure()
                    }
                }
            }
            is SuperAdminEvent.DeleteSuperAdminDetails -> {
                viewModelScope.launch {
                    val res = superAdminManager.deleteSuperAdminById(event.superAdmin_id)
                    if(res is DeleteSuperAdminByIdResponse.Success){
                        onSuccess()
                    }else if(res is DeleteSuperAdminByIdResponse.Failure){
                        onFailure()
                    }else if(res is DeleteSuperAdminByIdResponse.Exception){
                        onFailure()
                    }
                }
            }
        }
    }
}