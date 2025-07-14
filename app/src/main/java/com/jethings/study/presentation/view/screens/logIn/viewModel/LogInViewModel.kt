package com.jethings.study.presentation.view.screens.logIn.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jethings.study.data.api.req_res_classes.LogInRequest
import com.jethings.study.data.api.req_res_classes.LogInResponse
import com.jethings.study.data.db.entities.Account
import com.jethings.study.domain.manager.LocalUserManager
import com.jethings.study.domain.manager.RemoteAccountManager
import com.jethings.study.presentation.view.screens.logIn.events.LogInEvents
import kotlinx.coroutines.launch

class LogInViewModel(
    private val remoteAccountManager : RemoteAccountManager,
    private val localUserManager: LocalUserManager
) : ViewModel() {

    fun onEvent(event: LogInEvents, onSucces : () -> Unit, onFailure : ()-> Unit){
        when(event){
            is LogInEvents.LogIn -> {
                viewModelScope.launch{
                    val response = remoteAccountManager.logIn(
                        LogInRequest(event.email , event.password)
                    )
                    if(response is LogInResponse.Success){
                        Log.d("success to log in =====>> " , response.data.toString())
                        localUserManager.saveAppEntry()
                        localUserManager.saveAccount(Account(
                            accountId          = response.data.accountId,
                            userId             = response.data.userId,
                            accessToken        = response.data.accessToken,
                            firstName          = response.data.firstName,
                            lastName           = response.data.lastName,
                            profilePhoto       = response.data.profilePhoto,
                            email              = event.email,
                            isParent           = response.data.isParent,
                            isStudent          = response.data.isStudent,
                            isTeacher          = response.data.isTeacher,
                            isSuperAdmin       = response.data.isSuperAdmin,
                            password           = event.password,
                            ownedAcademies     = response.data.ownedAcademies,
                        ))
                        onSucces()
                    }else{
                        if ( response is LogInResponse.Failure ){
                            Log.d("failed to log in  =====>> " , response.data.toString())
                        }else if ( response is LogInResponse.Exception ){
                            Log.d("failed to log in =====>> " , response.data.toString())
                        }
                        onFailure()
                    }
                }
            }
        }
    }

}