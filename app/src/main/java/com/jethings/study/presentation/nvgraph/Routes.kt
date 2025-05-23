package com.jethings.study.presentation.nvgraph

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
