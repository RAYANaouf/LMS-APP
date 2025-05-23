package com.jethings.study.domain.manager


import com.jethings.study.data.db.entities.Account
import kotlinx.coroutines.flow.Flow

interface LocalAccountManager {

    suspend fun saveAccount(account : Account)


    suspend fun logInAccount(email : String, password : String)   : Account?
    suspend fun  logOutAccount()                          : Boolean

    fun isLogInAccount() : Account?
    fun getAllAccounts() : Flow<List<Account>>


}