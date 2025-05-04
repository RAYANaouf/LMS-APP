package com.jethings.study.presentation.view.screens.logIn.viewModel

import androidx.lifecycle.ViewModel
import com.jethings.study.presentation.view.screens.logIn.events.LogInEvents

class LogInViewModel(

) : ViewModel() {

    fun onEvent(event: LogInEvents, onSucces : () -> Unit, onFailure : ()-> Unit){
        when(event){
            is LogInEvents.LogIn -> {

            }
        }
    }

}