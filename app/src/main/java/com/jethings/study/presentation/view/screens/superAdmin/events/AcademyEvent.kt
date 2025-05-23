package com.jethings.study.presentation.view.screens.superAdmin.events

sealed class SuperAdminEvent {

    data class GetSuperAdminDetails(val superAdmin_id : Int): SuperAdminEvent()
    data class DeleteSuperAdminDetails(val superAdmin_id : Int): SuperAdminEvent()

}