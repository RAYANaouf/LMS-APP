package com.jethings.study.presentation.view.screens.academyOwners.event

sealed class AcademyOwnersEvents {
    class GetAcademyOwnersEvent(val academy_id: Int) : AcademyOwnersEvents()
}