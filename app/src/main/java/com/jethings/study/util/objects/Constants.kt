package com.jethings.study.util.objects


object Constants {



    //API
    const val BASE_URL             = "https://j-study.onrender.com"
    //to review
    const val SIGN_UP              = "/auth/signup"
    const val LOG_IN               = "/auth/login"
    const val CREATE_SUPER_ADMIN   = "/auth/createSuperAdmin"
    //user
    const val GET_USER_BY_EMAIL       = "/user/by-email/{email}"
    //super admin
    const val GET_ALL_SUPER_ADMIN       = "/super-admin/all"
    const val GET_SUPER_ADMIN_BY_ID     = "/super-admin/"
    const val DELETE_SUPER_ADMIN_BY_ID  = "/super-admin/"
    //academy
    const val CREATE_ACADEMY           = "/academy/create"
    const val GET_ALL_ACADEMIES        = "/academy/all"
    const val GET_ACADEMY_BY_ID        = "/academy/"
    const val GET_ACADEMY_OWNERS_BY_ID = "/academy/{id}/owners"
    const val ADD_ACADEMY_OWNER        = "/academy/{id}/add-owner"


    val topBarMenu = listOf( "Help" , "Settings"  , "App Info" ,  "About Us" , "Log Out")


}


