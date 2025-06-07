package com.jethings.study.data.db.entities.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable



@Serializable
@Entity
data class Academy (
    @PrimaryKey()
    val id     : Int        = 0,
    val name   : String     = "",
    val phone  : String?    = null,
    val email  : String?    = null,
    val logo   : String?    = null,
    val owners : List<Int>  = emptyList(),
    val trainingPrograms : List<TrainingProgram> = emptyList(),
)