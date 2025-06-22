package com.jethings.study.presentation.view.screens.home.events

sealed class  HomeEvents {

    data object getAllTrainingProgram : HomeEvents()
    data object refreshAcademyList    : HomeEvents()
    data object refreshSuperAdminList : HomeEvents()


}