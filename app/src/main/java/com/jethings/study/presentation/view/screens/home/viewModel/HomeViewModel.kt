package com.jethings.study.presentation.view.screens.home.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jethings.study.data.api.req_res_classes.SuperAdminModule.GetAllSuperAdminResponse
import com.jethings.study.data.api.req_res_classes.getAllAcademies.GetAllAcademiesResponse
import com.jethings.study.data.db.entities.entities.Academy
import com.jethings.study.data.db.entities.entities.SuperAdmin
import com.jethings.study.domain.manager.AcademyManager
import com.jethings.study.domain.manager.SuperAdminManager
import com.jethings.study.presentation.view.screens.home.events.HomeEvents
import kotlinx.coroutines.launch

class HomeViewModel(
    private val academyManager: AcademyManager,
    private val superAdminManager: SuperAdminManager
) : ViewModel() {


    val academyList    = mutableStateListOf<Academy>()
    val superAdminList = mutableStateListOf<SuperAdmin>()


    fun onEvent( event : HomeEvents , onSuccess : () -> Unit , onFailure : () -> Unit){
        when(event){
            is HomeEvents.refreshAcademyList -> {
                viewModelScope.launch {
                    val response = academyManager.getAllAcademies()
                    academyList.clear()
                    if (response is GetAllAcademiesResponse.Success) {
                        academyList.addAll(response.data.academies)
                        onSuccess()
                    } else {
                        onFailure()
                    }
                }
            }
            is HomeEvents.refreshSuperAdminList -> {
                viewModelScope.launch {
                    val response = superAdminManager.getAllSuperAdmins()
                    superAdminList.clear()
                    if (response is GetAllSuperAdminResponse.Success) {
                        superAdminList.addAll(response.data.superAdminList)
                        onSuccess()
                    } else {
                        onFailure()
                    }
                }
            }
        }
    }
}