package com.jethings.study

import android.accounts.Account
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jethings.study.presentation.nvgraph.AppScreen
import com.jethings.study.presentation.nvgraph.homeScreen
import com.jethings.study.presentation.nvgraph.logInScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext




class MainViewModel (
): ViewModel() {


    var startDestination by mutableStateOf<AppScreen>(logInScreen)
        private set


    init {

        viewModelScope.launch {

        }
    }





    fun onEvent(event : MainEvent, onSuccees : () -> Unit = {}){
        when(event){
            is MainEvent.LogOutEvent -> {

            }
        }
    }

}