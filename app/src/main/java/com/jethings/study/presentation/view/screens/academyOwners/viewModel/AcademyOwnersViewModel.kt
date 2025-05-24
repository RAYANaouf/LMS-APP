package com.jethings.study.presentation.view.screens.academyOwners.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jethings.study.data.api.req_res_classes.AcademyModule.addAcademyOwner.AddAcademyOwnerResponse
import com.jethings.study.data.api.req_res_classes.AcademyModule.addAcademyOwner.AddAcademyOwnerSuccessResponse
import com.jethings.study.data.api.req_res_classes.AcademyModule.getAcademyById.GetAcademyOwnersResponse
import com.jethings.study.data.api.req_res_classes.UserModule.GetUserByEmailResponse
import com.jethings.study.data.db.entities.Account
import com.jethings.study.domain.manager.AcademyManager
import com.jethings.study.domain.manager.UserManager
import com.jethings.study.presentation.view.screens.academyOwners.event.AcademyOwnersEvents
import kotlinx.coroutines.launch

class AcademyOwnersViewModel(
    val academyManager: AcademyManager,
    val userManager: UserManager
) : ViewModel() {


    val academyOwners = mutableStateListOf<Account>()

    var user by mutableStateOf<Account?>(null)


    fun onEvent( events : AcademyOwnersEvents , onSuccess : ()->Unit , onFailure: ()->Unit ){
        when(events){
            is AcademyOwnersEvents.GetAcademyOwnersEvent -> {
                viewModelScope.launch {
                    val result = academyManager.getAcademyOwners(events.academy_id)
                    if (result is GetAcademyOwnersResponse.Success){
                        academyOwners.clear()
                        academyOwners.addAll(result.data.owners)
                        onSuccess()
                    }else if(result is GetAcademyOwnersResponse.Failure){
                        Log.d("owners", "onEvent: ${result.data.message}")
                        onFailure()
                    }else{
                        Log.d("owners", "onEvent: exception")
                        onFailure()
                    }

                }
            }
            is AcademyOwnersEvents.GetUserByEmailEvent ->{
                viewModelScope.launch {
                    user = null
                    val result = userManager.getUserByEmail(events.email)
                    if (result is GetUserByEmailResponse.Success){
                        user = result.data.account
                        onSuccess()
                    }else if(result is GetUserByEmailResponse.Failure){
                        onFailure()
                    }else if (result is GetUserByEmailResponse.Exception){
                        onFailure()
                    }
                }
            }
            is AcademyOwnersEvents.AddAcademyOwnerEvent ->{
                viewModelScope.launch {
                    user = null
                    val result = academyManager.addAcademyOwner(academyId = events.academyId , ownerId = events.ownerId)
                    if (result is AddAcademyOwnerResponse.Success){
                        onEvent(
                            events = AcademyOwnersEvents.GetAcademyOwnersEvent(academy_id = events.academyId),{
                                onEvent(
                                    AcademyOwnersEvents.GetAcademyOwnersEvent(academy_id = events.academyId),{

                                    },{

                                    }
                                )
                            },{

                            }
                        )
                        onSuccess()
                    }else if(result is AddAcademyOwnerResponse.Failure){
                        onFailure()
                    }else if (result is AddAcademyOwnerResponse.Exception){
                        onFailure()
                    }

                }
            }
        }
    }
}