package com.jethings.study.domain.manager


import com.jethings.study.data.db.entities.Account
import com.jethings.study.data.db.entities.entities.Academy
import kotlinx.coroutines.flow.Flow

interface LocalUserManager {


    suspend fun saveAppEntry()
    suspend fun readAppEntry() : Flow<Boolean>

    suspend fun saveAccount(account : Account)
    suspend fun readAccount() : Flow<Account?>


    suspend fun logInAccount(email : String, password : String)   : Account?
    suspend fun logOutAccount()                          : Boolean

    fun isLogInAccount() : Account?
    fun getAllAccounts() : Flow<List<Account>>

    suspend fun setSelectedAcademy( academy : Academy) : Boolean
    suspend fun readSelectedAcademy() : Flow<Academy?>
}