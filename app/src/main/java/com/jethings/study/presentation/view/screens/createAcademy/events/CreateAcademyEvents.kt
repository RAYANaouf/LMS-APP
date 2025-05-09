package com.jethings.study.presentation.view.screens.createAcademy.events

sealed class CreateAcademyEvents {
    class CreateAcademy(val name : String) : CreateAcademyEvents()
}