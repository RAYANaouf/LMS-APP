package com.jethings.study.data.db.entities.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable



@Serializable
@Entity
data class SuperAdmin (
    @PrimaryKey
    val id           : Int = 0,
    val firstName    : String = "",
    val lastName     : String = "",
    val profilePhoto : String? = ""
)