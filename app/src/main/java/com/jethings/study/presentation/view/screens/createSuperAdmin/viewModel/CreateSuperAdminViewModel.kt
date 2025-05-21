package com.jethings.study.presentation.view.screens.createSuperAdmin.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jethings.study.data.api.req_res_classes.createSuperAdmin.CreateSuperAdminFailureResponse
import com.jethings.study.data.api.req_res_classes.createSuperAdmin.CreateSuperAdminRequest
import com.jethings.study.data.api.req_res_classes.createSuperAdmin.CreateSuperAdminResponse
import com.jethings.study.data.api.req_res_classes.createSuperAdmin.CreateSuperAdminSuccessResponse
import com.jethings.study.domain.manager.SuperAdminManager
import com.jethings.study.presentation.view.screens.createSuperAdmin.events.CreateSuperAdminEvents
import kotlinx.coroutines.launch

class CreateSuperAdminViewModel(
    private val createSuperAdminManager: SuperAdminManager
) : ViewModel() {


    fun onEvent( event : CreateSuperAdminEvents , onSuccessed : ()->Unit , onFailed : ()->Unit ){
        when(event){
            is CreateSuperAdminEvents.CreateSuperAdmin -> {
                viewModelScope.launch {
                    val result = createSuperAdminManager.createSuperAdmin(event.createSuperAdminRequest , event.profilePhoto)
                    if(result is CreateSuperAdminResponse.Success){
                        result.data
                        onSuccessed()
                    }else if(result is CreateSuperAdminResponse.Failure){
                        result.data
                        onFailed()
                    }else if(result is CreateSuperAdminResponse.Exception){
                        onFailed()
                        result.data
                    }else{
                        onFailed()
                        Exception("unknown error")
                    }
                }
            }
        }
    }


}