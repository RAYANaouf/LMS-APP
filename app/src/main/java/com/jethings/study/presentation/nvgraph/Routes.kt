package com.jethings.study.presentation.nvgraph

import com.jethings.study.data.db.entities.Account
import kotlinx.serialization.Serializable

@Serializable
open class AppScreen()



@Serializable
object logInScreen : AppScreen()
@Serializable
object signUpScreen : AppScreen()


@Serializable
object onBoardingScreen : AppScreen()





@Serializable
object homeScreen  : AppScreen()


@Serializable
object profileScreen  : AppScreen()

@Serializable
object createAcademyScreen  : AppScreen()
@Serializable
object createSuperAdminScreen  : AppScreen()



@Serializable
data class academyScreen(val academy_id: Int) : AppScreen()
@Serializable
data class superAdminScreen(val superAdmin_id: Int) : AppScreen()





@Serializable
data class academyOwnerScreen(val academy_id: Int)  : AppScreen()

@Serializable
object academyOwnerList  : AppScreen()
@Serializable
data class academyHome(val academy_id: Int)  : AppScreen()




object manageAcademy : AppScreen()

@Serializable
object createTrainingProgram : AppScreen()
@Serializable
data class TrainingProgramScreen(
    val trainingProgram_id: Int,
    val title       : String,
    val desc        : String,
    val coverPhoto  : String = ""
) : AppScreen()

