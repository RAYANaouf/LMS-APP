package com.jethings.study.presentation.view.screens.academyOwners.event

sealed class AcademyOwnersEvents {
    class GetAcademyOwnersEvent(val academy_id: Int) : AcademyOwnersEvents()
    class GetUserByEmailEvent(val email: String) : AcademyOwnersEvents()
    class AddAcademyOwnerEvent(val ownerId: Int, val academyId: Int) : AcademyOwnersEvents()
}