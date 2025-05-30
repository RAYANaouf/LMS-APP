package com.jethings.study.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Account(
    @PrimaryKey()
    val accountId     : Long    = 0,
    val userId        : Long    = 0,
    val firstName     : String  = "",
    val lastName      : String  = "",
    val profilePhoto  : String? = "",
    val verified      : Boolean = false,
    val access_token  : String  = "",
    val phone         : String  = "",
    val email         : String  = "",
    val password      : String  = "",
    val isSuperAdmin  : Boolean = false,
    val isStudent     : Boolean = false,
    val isParent      : Boolean = false,
    val isTeacher     : Boolean = false,

    val ownedAcademies : Int = 0,
)
