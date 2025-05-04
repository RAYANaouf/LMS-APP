package com.jethings.study.presentation.view.screens.logIn.events

sealed class LogInEvents {

    class LogIn(val email : String , val password : String) : LogInEvents()
}