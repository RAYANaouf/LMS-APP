package com.jethings.study.presentation.view.screens.profile.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jethings.study.data.db.entities.Account
import com.jethings.study.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


class ProfileViewModel(
    private val localUserManager: LocalUserManager
) : ViewModel(){


    var user : Account? by mutableStateOf(null)


    init {
        viewModelScope.launch {
            localUserManager.readAccount().onEach {
                user = it
            }.launchIn(viewModelScope)
        }
    }



}