package com.jethings.study.data.db.entities.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable



@Serializable
@Entity
data class Post (
    @PrimaryKey()
    val id       : Long        = 0,
    val title    : String      = "",
    val content  : String      = "",
    val photo    : String?     = "",

    val academy: Academy?      = null
)