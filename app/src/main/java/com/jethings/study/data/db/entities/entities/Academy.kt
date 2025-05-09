package com.jethings.study.data.db.entities.entities

import androidx.room.Entity
import kotlinx.serialization.Serializable



@Serializable
@Entity
data class Academy (
    val id : Int ,
    val name : String,
    val phone : String? ,
    val email : String?
)