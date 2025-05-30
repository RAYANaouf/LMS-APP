package com.jethings.study.presentation.view.screens.signUp.signUpEvents

sealed class SignUpEvents {

    class SignUpBtnClicked(val firstName:String ,val lastName:String , val email : String , val password : String , val phone : String ) : SignUpEvents()

}