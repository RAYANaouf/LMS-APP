package com.jethings.study

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jethings.study.data.api.req_res_classes.AcademyModule.getAcademyById.GetAcademiesByOwnerIdFailureResponse
import com.jethings.study.data.api.req_res_classes.AcademyModule.getAcademyById.GetAcademiesByOwnerIdResponse
import com.jethings.study.data.api.req_res_classes.AcademyModule.getAcademyById.GetAcademiesByOwnerIdSuccessResponse
import com.jethings.study.data.db.entities.Account
import com.jethings.study.data.db.entities.entities.Academy
import com.jethings.study.domain.manager.AcademyManager
import com.jethings.study.domain.manager.LocalUserManager
import com.jethings.study.presentation.nvgraph.AppScreen
import com.jethings.study.presentation.nvgraph.academyScreen
import com.jethings.study.presentation.nvgraph.createAcademyScreen
import com.jethings.study.presentation.nvgraph.createSuperAdminScreen
import com.jethings.study.presentation.nvgraph.homeScreen
import com.jethings.study.presentation.nvgraph.logInScreen
import com.jethings.study.presentation.nvgraph.profileScreen
import com.jethings.study.presentation.nvgraph.signUpScreen
import com.jethings.study.presentation.nvgraph.superAdminScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext




class MainViewModel (
    private val localUserManager : LocalUserManager,
    private val academyManager   : AcademyManager
): ViewModel() {


    var startDestination by mutableStateOf<AppScreen>(logInScreen)
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

    //navigation drawer

    var show_navigationDrawer    by mutableStateOf(false)
        private set









    //logic vars
    var account : Account? by mutableStateOf(null)
        private set
    val myAcademies  = mutableStateListOf<Academy>(
        Academy(
            id = -1
        )
    )

    var selectedAcademy : Academy? by mutableStateOf(null)
        private set


    init {

        viewModelScope.launch {
            localUserManager.readAppEntry().onEach { shouldStartFromHomeScreen ->
                if (shouldStartFromHomeScreen) {
                    val _account = localUserManager.readAccount().first()
                    if (_account != null) {
                        Log.d("success to log in", "success to read account : ${account}")
                        account = _account
                        startDestination = homeScreen
                    } else {
                        startDestination = logInScreen
                    }

                } else {
                    startDestination = logInScreen
                }

            }.launchIn(viewModelScope)

            localUserManager.readSelectedAcademy().onEach { academy ->
                selectedAcademy = academy
            }.launchIn(viewModelScope)
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
                if (account!= null && account?.isSuperAdmin == true){
                    //top bar
                    show_topbar      =  true
                    topbar_shadow    =  2.dp
                    setTopBarInfo(img = R.drawable.app_store_logo_none_background , txt = "Dirassa" )

                    //bottom bar
                    show_bottombar   =  true
                    bottombar_shadow =  0.dp

                    //navigation drawer
                    show_navigationDrawer = true
                }else{
                    //top bar
                    show_topbar      =  true
                    topbar_shadow    =  2.dp
                    setTopBarInfo(img = R.drawable.app_store_logo_none_background , txt = "Dirassa" )

                    //bottom bar
                    show_bottombar   =  false
                    bottombar_shadow =  0.dp

                    //navigation drawer
                    show_navigationDrawer = true
                }

            }
            profileScreen->{
                if (account!= null && account?.isSuperAdmin == true){
                    //top bar
                    show_topbar      =  true
                    topbar_shadow    =  2.dp
                    setTopBarInfo(img = R.drawable.app_store_logo_none_background , txt = "Dirassa" )

                    //bottom bar
                    show_bottombar   =  true
                    bottombar_shadow =  0.dp


                    //navigation drawer
                    show_navigationDrawer = true
                }else{
                    //top bar
                    show_topbar      =  true
                    topbar_shadow    =  2.dp
                    setTopBarInfo(img = R.drawable.app_store_logo_none_background , txt = "Dirassa" )

                    //bottom bar
                    show_bottombar   =  false

                    bottombar_shadow =  0.dp


                    //navigation drawer
                    show_navigationDrawer = true
                }

            }
            logInScreen ->{
                //top bar
                show_topbar      =  false
                topbar_shadow    =  0.dp
                //bottom bar
                show_bottombar   =  false
                bottombar_shadow =  0.dp


                //navigation drawer
                show_navigationDrawer = false
            }
            signUpScreen ->{
                //top bar
                show_topbar      =  false
                topbar_shadow    =  0.dp
                //bottom bar
                show_bottombar   =  false
                bottombar_shadow =  0.dp


                //navigation drawer
                show_navigationDrawer = false
            }
            createAcademyScreen ->{
                //top bar
                show_topbar      =  true
                topbar_shadow    =  0.dp
                //bottom bar
                show_bottombar   =  false
                bottombar_shadow =  0.dp


                //navigation drawer
                show_navigationDrawer = true
            }
            createSuperAdminScreen ->{
                //top bar
                show_topbar      =  true
                topbar_shadow    =  0.dp
                //bottom bar
                show_bottombar   =  false
                bottombar_shadow =  0.dp


                //navigation drawer
                show_navigationDrawer = true
            }
            is academyScreen ->{
                //top bar
                show_topbar      =  true
                topbar_shadow    =  0.dp
                //bottom bar
                show_bottombar   =  false
                bottombar_shadow =  0.dp

                //navigation drawer
                show_navigationDrawer = true
            }
            is superAdminScreen ->{
                //top bar
                show_topbar      =  true
                topbar_shadow    =  0.dp
                //bottom bar
                show_bottombar   =  false
                bottombar_shadow =  0.dp

                //navigation drawer
                show_navigationDrawer = true
            }
            else ->{
                //top bar
                show_topbar      =  true
                topbar_shadow    =  0.dp
                //bottom bar
                show_bottombar   =  false
                bottombar_shadow =  0.dp

                //navigation drawer
                show_navigationDrawer = false
            }

        }
    }




    fun onEvent(event : MainEvent, onSuccees : () -> Unit = {}, onFailure : () -> Unit = {}){
        when(event){
            is MainEvent.LogOutEvent -> {
                viewModelScope.launch {
                    val result  = localUserManager.logOutAccount()
                    if(result){
                        onSuccees()
                    }else{
                        onFailure()
                    }
                }
            }
            is MainEvent.ScreenChangeEvent -> {
                setCurrentScreen(event.screen)
            }
            is MainEvent.GetMyAcademiesEvent -> {
                viewModelScope.launch {
                    val result = academyManager.getAcademiesByOwnerId(event.userId)
                    if (result is GetAcademiesByOwnerIdResponse.Success){
                        myAcademies.clear()
                        myAcademies.addAll(result.data.academies)
                        onSuccees()
                    }else if (result is GetAcademiesByOwnerIdResponse.Failure){
                        onFailure()
                    }else {
                        onFailure()
                    }
                }
            }
            is MainEvent.SelectAcademy->{
                viewModelScope.launch {
                    val result = localUserManager.setSelectedAcademy(event.academy)
                    if (result){
                        onSuccees()
                    }else {
                        onFailure()
                    }
                }
            }

        }
    }

}