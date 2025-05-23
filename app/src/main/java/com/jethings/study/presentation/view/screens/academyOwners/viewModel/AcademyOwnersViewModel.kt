package com.jethings.study.presentation.view.screens.academyOwners.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jethings.study.data.api.req_res_classes.AcademyModule.getAcademyById.GetAcademyOwnersResponse
import com.jethings.study.data.db.entities.Account
import com.jethings.study.domain.manager.AcademyManager
import com.jethings.study.presentation.view.screens.academyOwners.event.AcademyOwnersEvents
import kotlinx.coroutines.launch

class AcademyOwnersViewModel(
    val academyManager: AcademyManager
) : ViewModel() {


    val academyOwners = mutableStateListOf<Account>()


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
        }
    }
}