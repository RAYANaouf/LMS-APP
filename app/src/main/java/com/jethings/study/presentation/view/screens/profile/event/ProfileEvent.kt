package com.jethings.study.presentation.view.screens.profile.event

import java.io.File

sealed class ProfileEvent {
    data class UpdateUserProfilePhotoEvent(val userId : Long , val profilePhoto : File? = null) : ProfileEvent()
}