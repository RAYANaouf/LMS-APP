package com.jethings.study.data.manager

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.jethings.study.data.db.entities.Account
import com.jethings.study.domain.manager.LocalUserManager
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
        TODO("Not yet implemented")
    }

    override suspend fun logInAccount(email: String, password: String): Account? {
        TODO("Not yet implemented")
    }

    override suspend fun logOutAccount(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isLogInAccount(): Account? {
        TODO("Not yet implemented")
    }

    override fun getAllAccounts(): Flow<List<Account>> {
        TODO("Not yet implemented")
    }
}


private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = USER_SETTINGS)

private object PrefrencesKeys{
    val APP_ENTRY = booleanPreferencesKey(name = APP_ENRTY)
}