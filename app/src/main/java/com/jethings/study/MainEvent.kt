package com.jethings.study

import com.jethings.study.presentation.nvgraph.AppScreen

sealed class MainEvent {

    class LogOutEvent() : MainEvent()

    class ScreenChangeEvent(val screen : AppScreen) : MainEvent()

}