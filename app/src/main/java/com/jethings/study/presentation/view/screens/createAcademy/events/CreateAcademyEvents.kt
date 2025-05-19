package com.jethings.study.presentation.view.screens.createAcademy.events

import java.io.File

sealed class CreateAcademyEvents {
    class CreateAcademy(val name : String , val logo : File? = null) : CreateAcademyEvents()
}