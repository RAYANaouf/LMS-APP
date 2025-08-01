package com.jethings.study.util.objects


object Constants {



    //DataStore
    const val USER_SETTINGS      = "USER_SETTINGS"
    const val APP_ENRTY          = "APP_ENTRY"
    const val ACCOUNT_ID         = "ACCOUNT_ID"
    const val USER_ID            = "USER_ID"
    const val ACCESS_TOKEN       = "ACCESS_TOKEN"
    const val USER_FIRST_NAME    = "USER_FIRST_NAME"
    const val USER_LAST_NAME     = "USER_LAST_NAME"
    const val USER_EMAIL         = "USER_EMAIL"
    const val USER_PHONE         = "USER_PHONE"
    const val USER_PASSWORD      = "USER_PASSWORD"
    const val USER_PROFILE_PHOTO = "USER_PROFILE_PHOTO"
    const val IS_SUPER_ADMIN     = "IS_SUPER_ADMIN"
    const val IS_TEACHER         = "IS_TEACHER"
    const val IS_STUDENT         = "IS_STUDENT"
    const val IS_PARENT          = "IS_PARENT"
    const val OWNED_ACADEMiES    = "OWNED_ACADEMiES"


    //API
    const val BASE_URL             = "https://j-study.onrender.com"
    //to review
    const val SIGN_UP              = "/auth/signup"
    const val LOG_IN               = "/auth/login"
    const val CREATE_SUPER_ADMIN   = "/auth/createSuperAdmin"
    //user
    const val GET_USER_BY_EMAIL       = "/user/by-email/{email}"
    const val UPDATE_PROFILE_PHOTO    = "/user/change-profilePhoto/{id}"
    //super admin
    const val GET_ALL_SUPER_ADMIN       = "/super-admin/all"
    const val GET_SUPER_ADMIN_BY_ID     = "/super-admin/"
    const val DELETE_SUPER_ADMIN_BY_ID  = "/super-admin/"
    //academy
    const val CREATE_ACADEMY             = "/academy/create"
    const val GET_ALL_ACADEMIES          = "/academy/all"
    const val GET_ACADEMY_BY_ID          = "/academy/"
    const val GET_ACADEMY_OWNERS_BY_ID   = "/academy/{id}/owners"
    const val ADD_ACADEMY_OWNER          = "/academy/{id}/add-owner"
    const val GET_ACADEMIES_BY_OWNER_ID  = "/academy/user/{id}"

    //course
    const val GET_ALL_COURSE             = "/course/all"
    const val CREATE_COURSE              = "/course/create"
    const val GET_ALL_COURSE_BY_ACADEMY  = "/course/academy/{id}"
    //enrollment-request
    const val REQUEST_FOR_COURSE         = "/enrollment-request/create"
    //Post
    const val GET_ALL_POST             = "/post/all"
    const val CREATE_POST              = "/post/create"
    const val GET_ALL_POST_BY_ACADEMY  = "/post/academy/{id}"


    val topBarMenu = listOf( "Help" , "Settings"  , "App Info" ,  "About Us" , "Log Out")


}


