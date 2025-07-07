package com.jethings.study.presentation.view.screens.profile.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jethings.study.data.api.req_res_classes.UserModule.UpdateUserProfilePhotoResponse
import com.jethings.study.data.api.req_res_classes.UserModule.UpdateUserProfilePhotoSuccessResponse
import com.jethings.study.data.db.entities.Account
import com.jethings.study.domain.manager.LocalUserManager
import com.jethings.study.domain.manager.UserManager
import com.jethings.study.presentation.view.screens.profile.event.ProfileEvent
import io.ktor.util.InternalAPI
import io.ktor.util.rootCause
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


class ProfileViewModel(
    private val localUserManager: LocalUserManager,
    private val userManager: UserManager,
) : ViewModel(){


    var user : Account? by mutableStateOf(null)


    init {
        viewModelScope.launch {
            localUserManager.readAccount().onEach {
                user = it
            }.launchIn(viewModelScope)
        }
    }

    @OptIn(InternalAPI::class)
    fun onEvent(event : ProfileEvent, onSuccess : ()->Unit, onFailure : ()->Unit){
        when(event){
            is ProfileEvent.UpdateUserProfilePhotoEvent ->{
                viewModelScope.launch {
                    val result = userManager.updateUserProfilePhoto(event.userId , event.profilePhoto)
                    Log.d("updating photo result : " , result.toString())
                    if(result is UpdateUserProfilePhotoResponse.Success){
                        Log.d("updating photo result :: " , result.data.profilePhoto.toString())
                        user?.let {
                            user = it.copy(profilePhoto = result.data.profilePhoto)
                            localUserManager.saveAccount(it.copy(profilePhoto = result.data.profilePhoto))
                        }
                        onSuccess()
                    }else if(result is UpdateUserProfilePhotoResponse.Failure){
                        Log.d("updating photo result ::: " , result.data.error.toString())
                        onFailure()
                    }else if(result is UpdateUserProfilePhotoResponse.Exception){
                        Log.d("updating photo result ::: " , result.data.message + "\n" + result.data.cause + "\n" + result.data.localizedMessage+ "\n" + result.data.rootCause?.message.toString())
                        onFailure()
                    }else{
                        Log.d("updating photo result :::: " , result.toString())
                        onFailure()
                    }
                }
            }
        }
    }



}