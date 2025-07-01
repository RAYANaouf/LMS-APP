package com.jethings.study.presentation.view.screens.setting.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jethings.study.data.db.entities.Account
import com.jethings.study.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SettingsViewModel(
    localUserManager : LocalUserManager
) : ViewModel() {

    var account : Account? by mutableStateOf(null)

    init {
        viewModelScope.launch {
            account = localUserManager.readAccount().first()
        }
    }

}