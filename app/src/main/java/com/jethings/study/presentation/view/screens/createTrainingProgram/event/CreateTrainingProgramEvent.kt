package com.jethings.study.presentation.view.screens.createTrainingProgram.event

import com.jethings.study.data.api.req_res_classes.TrainingProgramModule.createTrainingProgram.CreateTrainingProgramRequest

sealed class CreateTrainingProgramEvent {


    data class CreateTrainingProgram(val createTrainingProgramRequest: CreateTrainingProgramRequest) : CreateTrainingProgramEvent()
}