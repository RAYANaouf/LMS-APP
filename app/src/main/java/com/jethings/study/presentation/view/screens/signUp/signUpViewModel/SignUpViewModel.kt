package com.jethings.study.presentation.view.screens.signUp.signUpViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jethings.study.data.api.req_res_classes.SignUpFailureResponse
import com.jethings.study.data.api.req_res_classes.SignUpRequest
import com.jethings.study.data.api.req_res_classes.SignUpResponse
import com.jethings.study.data.api.req_res_classes.SignUpSuccessResponse
import com.jethings.study.domain.manager.LocalUserManager
import com.jethings.study.domain.manager.RemoteAccountManager
import com.jethings.study.presentation.view.screens.signUp.signUpEvents.SignUpEvents
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val remoteAccountManager: RemoteAccountManager,
    private val localUserManager: LocalUserManager
) : ViewModel() {


    fun onEvent(event: SignUpEvents, onSuccess:(SignUpSuccessResponse)->Unit, onFailure:(SignUpFailureResponse)->Unit, onException: (Exception)->Unit ){
        when(event){
            is SignUpEvents.SignUpBtnClicked -> {
                viewModelScope.launch {
                    val result = remoteAccountManager.signUp(SignUpRequest(email = event.email , password = event.password))
                    if (result is SignUpResponse.Success){
                        localUserManager.saveAppEntry()
                        onSuccess(SignUpSuccessResponse(result.data.access_token ))
                    }else if (result is SignUpResponse.Failure){
                        onFailure(SignUpFailureResponse(
                            statusCode = result.data.statusCode,
                            message    = result.data.message,
                            error      = result.data.error
                        ))
                    }else{
                        onException(Exception())
                    }
                }
            }
        }
    }



}