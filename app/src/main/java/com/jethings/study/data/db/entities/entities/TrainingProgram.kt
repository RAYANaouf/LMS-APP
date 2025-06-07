package com.jethings.study.data.db.entities.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


@Serializable
@Entity
data class TrainingProgram (
    @PrimaryKey()
    val id                 : Long        = 0,
    val academyId          : Long        = 0,
    val name               : String      = "",
    val description        : String?     = null,
    val coverPhoto         : String?     = null,
    val targetAudience     : String?     = null,
    val prerequisites      : String?     = null,
    val whatYouCanDoAfter  : String?     = null,
    val minAge             : String?     = null,
    val maxAge             : String?     = null,
    val price              : String?     = null,
    val enabled            : Boolean     = true
)