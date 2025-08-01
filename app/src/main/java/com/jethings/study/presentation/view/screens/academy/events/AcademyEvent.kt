package com.jethings.study.presentation.view.screens.academy.events

sealed class AcademyEvent {

    data class GetAllTrainingProgramByAcademy(val academy_id : Int): AcademyEvent()
    data class GetAllPostByAcademy(val academy_id : Int): AcademyEvent()

}