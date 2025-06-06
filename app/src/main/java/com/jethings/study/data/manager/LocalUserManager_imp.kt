package com.jethings.study.data.manager

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.jethings.study.data.db.entities.Account
import com.jethings.study.data.db.entities.entities.Academy
import com.jethings.study.domain.manager.LocalUserManager
import com.jethings.study.util.objects.Constants
import com.jethings.study.util.objects.Constants.APP_ENRTY
import com.jethings.study.util.objects.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManager_imp(
    private val context : Context
) : LocalUserManager {


    override suspend fun saveAppEntry() {
        context.dataStore.edit { settings ->
            settings[PrefrencesKeys.APP_ENTRY] = true
            Toast.makeText(context , "app entry saved" , Toast.LENGTH_SHORT).show()
            Log.d("app entry saved" , "saveAppEntry: ${settings[PrefrencesKeys.APP_ENTRY]}")
        }
    }

    override suspend fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            Toast.makeText(context , "app entry read" , Toast.LENGTH_SHORT).show()
            Log.d("app entry read " , "saveAppEntry: ${preferences[PrefrencesKeys.APP_ENTRY]}")
            preferences[PrefrencesKeys.APP_ENTRY] ?: false
        }
    }


    override suspend fun saveAccount(account: Account) {
        context.dataStore.edit { settings ->
            settings[PrefrencesKeys.USER_ACCOUNT_ID] = account.accountId
            settings[PrefrencesKeys.USER_ID] = account.userId
            settings[PrefrencesKeys.USER_EMAIL] = account.email
            settings[PrefrencesKeys.USER_PASSWORD] = account.password
            settings[PrefrencesKeys.USER_PHONE] = account.phone
            settings[PrefrencesKeys.USER_FIRST_NAME] = account.firstName
            settings[PrefrencesKeys.USER_LAST_NAME] = account.lastName
            settings[PrefrencesKeys.USER_PROFILE_PHOTO] = account.profilePhoto ?: ""
            settings[PrefrencesKeys.IS_SUPER_ADMIN] = account.isSuperAdmin
            settings[PrefrencesKeys.IS_TEACHER]  = account.isTeacher
            settings[PrefrencesKeys.IS_STUDENT]   = account.isStudent
            settings[PrefrencesKeys.IS_PARENT]   = account.isParent
            settings[PrefrencesKeys.OWNED_ACADEMiES]   = account.ownedAcademies
        }
    }

    override suspend fun readAccount() : Flow<Account?>  {

        return context.dataStore.data.map { preferences ->

            if (preferences[PrefrencesKeys.USER_ACCOUNT_ID] == null || preferences[PrefrencesKeys.USER_ACCOUNT_ID] == 0L){
                null
            }else{
                val account = Account(
                    accountId =  preferences[PrefrencesKeys.USER_ACCOUNT_ID] ?: 0L,
                    userId =   preferences[PrefrencesKeys.USER_ID] ?: 0L ,
                    email =  preferences[PrefrencesKeys.USER_EMAIL] ?: "@gmail.com",
                    password = preferences[PrefrencesKeys.USER_PASSWORD] ?: "",
                    phone =  preferences[PrefrencesKeys.USER_PHONE] ?: "",
                    firstName = preferences[PrefrencesKeys.USER_FIRST_NAME] ?: "F_NAME" ,
                    lastName =  preferences[PrefrencesKeys.USER_LAST_NAME] ?: "L_NAME" ,
                    profilePhoto = preferences[PrefrencesKeys.USER_PROFILE_PHOTO] ?: "" ,
                    isSuperAdmin = preferences[PrefrencesKeys.IS_SUPER_ADMIN] ?: false ,
                    isTeacher = preferences[PrefrencesKeys.IS_TEACHER]        ?: false ,
                    isStudent = preferences[PrefrencesKeys.IS_STUDENT]        ?: false ,
                    isParent = preferences[PrefrencesKeys.IS_PARENT]          ?: false ,
                    ownedAcademies = preferences[PrefrencesKeys.OWNED_ACADEMiES]  ?: 0 ,
                )
                account
            }


        }
    }

    override suspend fun logInAccount(email: String, password: String): Account? {
        TODO("Not yet implemented")
    }

    override suspend fun logOutAccount(): Boolean {
        try {
            context.dataStore.edit { settings ->
                settings[PrefrencesKeys.USER_ACCOUNT_ID] = 0L
                settings[PrefrencesKeys.USER_ID] = 0L
                settings[PrefrencesKeys.USER_EMAIL] = ""
                settings[PrefrencesKeys.USER_PASSWORD] = ""
                settings[PrefrencesKeys.USER_PHONE] = ""
                settings[PrefrencesKeys.USER_FIRST_NAME] = ""
                settings[PrefrencesKeys.USER_LAST_NAME] = ""
                settings[PrefrencesKeys.USER_PROFILE_PHOTO] = ""
                settings[PrefrencesKeys.IS_SUPER_ADMIN]     = false
                settings[PrefrencesKeys.IS_TEACHER]         = false
                settings[PrefrencesKeys.IS_STUDENT]         = false
                settings[PrefrencesKeys.IS_PARENT]          = false
                settings[PrefrencesKeys.OWNED_ACADEMiES]    = 0
            }
            return true
        }catch (e : Exception){
            Log.d("log out error : " , e.toString())
            return false
        }
    }

    override fun isLogInAccount(): Account? {
        TODO("Not yet implemented")
    }

    override fun getAllAccounts(): Flow<List<Account>> {
        TODO("Not yet implemented")
    }

    override suspend fun setSelectedAcademy(academy : Academy) : Boolean {
        try {
            context.dataStore.edit { settings ->
                settings[PrefrencesKeys.ACADEMY_ID] = academy.id
                settings[PrefrencesKeys.ACADEMY_NAME] = academy.name
                settings[PrefrencesKeys.ACADEMY_LOGO] = academy.logo ?: ""
            }
            return true
        }catch (e : Exception){
            Log.d("log out error : " , e.toString())
            return false
        }
    }
}


private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = USER_SETTINGS)

private object PrefrencesKeys{
    val APP_ENTRY        = booleanPreferencesKey(name = APP_ENRTY)
    val USER_ACCOUNT_ID  = longPreferencesKey(name = Constants.ACCOUNT_ID)
    val USER_ID          = longPreferencesKey(name = Constants.USER_ID)
    val USER_FIRST_NAME  = stringPreferencesKey(name = Constants.USER_FIRST_NAME)
    val USER_LAST_NAME   = stringPreferencesKey(name = Constants.USER_LAST_NAME)
    val USER_EMAIL       = stringPreferencesKey(name = Constants.USER_EMAIL)
    val USER_PASSWORD    = stringPreferencesKey(name = Constants.USER_PASSWORD)
    val USER_PROFILE_PHOTO = stringPreferencesKey(name = Constants.USER_PROFILE_PHOTO)
    val USER_PHONE       = stringPreferencesKey(name = Constants.USER_PHONE)
    val IS_SUPER_ADMIN   = booleanPreferencesKey(name = Constants.IS_SUPER_ADMIN)
    val IS_TEACHER       = booleanPreferencesKey(name = Constants.IS_TEACHER)
    val IS_STUDENT       = booleanPreferencesKey(name = Constants.IS_STUDENT)
    val IS_PARENT        = booleanPreferencesKey(name = Constants.IS_PARENT)
    val OWNED_ACADEMiES  = intPreferencesKey(name = Constants.OWNED_ACADEMiES)

    //academy
    val ACADEMY_ID       = intPreferencesKey(name = "ACADEMY_ID")
    val ACADEMY_NAME     = stringPreferencesKey(name = "ACADEMY_NAME")
    val ACADEMY_LOGO     = stringPreferencesKey(name = "ACADEMY_LOGO")

}