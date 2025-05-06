package com.jethings.study.presentation.view.screens.logIn.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jethings.study.data.api.req_res_classes.LogInRequest
import com.jethings.study.data.api.req_res_classes.LogInResponse
import com.jethings.study.domain.manager.RemoteAccountManager
import com.jethings.study.presentation.view.screens.logIn.events.LogInEvents
import kotlinx.coroutines.launch

class LogInViewModel(
    private val remoteAccountManager : RemoteAccountManager
) : ViewModel() {

    fun onEvent(event: LogInEvents, onSucces : () -> Unit, onFailure : ()-> Unit){
        when(event){
            is LogInEvents.LogIn -> {
                viewModelScope.launch{
                    val response = remoteAccountManager.logIn(
                        LogInRequest(event.email , event.password)
                    )
                    if(response is LogInResponse.Success){
                        onSucces()
                        Log.d("heeeeeeeeeeeeeeeere success  =====>> " , response.data.access_token)
                    }else{
                        if ( response is LogInResponse.Failure ){
                            Log.d("heeeeeeeeeeeeeeeere  =====>> " , response.data.toString())
                        }else if ( response is LogInResponse.Exception ){
                            Log.d("heeeeeeeeeeeeeeeere =====>> " , response.data.toString())
                        }
                        onFailure()
                    }
                }
            }
        }
    }

}