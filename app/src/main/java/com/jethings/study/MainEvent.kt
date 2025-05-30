package com.jethings.study

import com.jethings.study.presentation.nvgraph.AppScreen

sealed class MainEvent {

    class LogOutEvent() : MainEvent()


    class GetMyAcademiesEvent(val userId : Long) : MainEvent()

    class ScreenChangeEvent(val screen : AppScreen) : MainEvent()

}