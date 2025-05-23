package com.jethings.study.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Account(
    @PrimaryKey()
    val accountId  : Long    = 0,
    val userId     : Long    = 0,
    val firstName  : String  = "",
    val lastName   : String  = "",
    val profilePhoto : String? = "",
    val verified   : Boolean = false,
    val phone      : String  = "",
    val email      : String  = "",
    val password   : String  = "",
)
