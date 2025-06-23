package com.jethings.study.presentation.view.screens.createTrainingProgram.event

import com.jethings.study.data.api.req_res_classes.TrainingProgramModule.createTrainingProgram.CreateTrainingProgramRequest
import java.io.File

sealed class CreateTrainingProgramEvent {


    data class CreateTrainingProgram(val createTrainingProgramRequest: CreateTrainingProgramRequest , val file : File?) : CreateTrainingProgramEvent()
}