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
import com.jethings.study.presentation.nvgraph.academyScreen
import com.jethings.study.presentation.nvgraph.createAcademyScreen
import com.jethings.study.presentation.nvgraph.createSuperAdminScreen
import com.jethings.study.presentation.nvgraph.homeScreen
import com.jethings.study.presentation.nvgraph.logInScreen
import com.jethings.study.presentation.nvgraph.profileScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext




class MainViewModel (
): ViewModel() {


    var startDestination by mutableStateOf<AppScreen>(homeScreen)
        private set


    //topbar
    var show_topbar    by mutableStateOf(false)
        private set
    var topbar_shadow  by mutableStateOf(0.dp)
        private set
    var topBarImg by mutableStateOf<Int?>(null)
        private set
    var topBarTxt by mutableStateOf<String>("")
        private set


    var current_screen : AppScreen by mutableStateOf(homeScreen)
        private set
    var current_scene  : String    by mutableStateOf("")
        private set


    //bottom bar
    var show_bottombar by mutableStateOf(false)
        private set
    var bottombar_shadow  by mutableStateOf(0.dp)
        private set


    init {

        viewModelScope.launch {

        }
    }



    private fun setTopBarInfo(img : Int?, txt : String?) {
        if (img != null)
            topBarImg = img
        if (txt != null)
            topBarTxt = txt

    }


    private fun setCurrentScreen( appScreen : AppScreen) {
        current_screen = appScreen
        when(current_screen){
            homeScreen->{
                //top bar
                show_topbar      =  true
                topbar_shadow    =  2.dp
                setTopBarInfo(img = R.drawable.app_store_logo_none_background , txt = "Dirassa" )

                //bottom bar
                show_bottombar   =  true
                bottombar_shadow =  0.dp
            }
            profileScreen->{
                //top bar
                show_topbar      =  true
                topbar_shadow    =  2.dp
                setTopBarInfo(img = R.drawable.app_store_logo_none_background , txt = "Dirassa" )

                //bottom bar
                show_bottombar   =  true
                bottombar_shadow =  0.dp
            }
            logInScreen ->{
                //top bar
                show_topbar      =  false
                topbar_shadow    =  0.dp
                //bottom bar
                show_bottombar   =  false
                bottombar_shadow =  0.dp
            }
            createAcademyScreen ->{
                //top bar
                show_topbar      =  true
                topbar_shadow    =  0.dp
                //bottom bar
                show_bottombar   =  false
                bottombar_shadow =  0.dp
            }
            createSuperAdminScreen ->{
                //top bar
                show_topbar      =  true
                topbar_shadow    =  0.dp
                //bottom bar
                show_bottombar   =  false
                bottombar_shadow =  0.dp
            }
            academyScreen ->{
                //top bar
                show_topbar      =  true
                topbar_shadow    =  0.dp
                //bottom bar
                show_bottombar   =  false
                bottombar_shadow =  0.dp
            }

        }
    }




    fun onEvent(event : MainEvent, onSuccees : () -> Unit = {}){
        when(event){
            is MainEvent.LogOutEvent -> {

            }
            is MainEvent.ScreenChangeEvent -> {
                setCurrentScreen(event.screen)
            }

        }
    }

}