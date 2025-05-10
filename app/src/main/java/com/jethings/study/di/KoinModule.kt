package com.jethings.study.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.jethings.study.MainViewModel
import com.jethings.study.data.db.AppDataBase
import com.jethings.study.data.db.dao.AccountDao
import com.jethings.study.data.manager.AcademyManager_imp
import com.jethings.study.data.manager.AppEntryManager_imp
import com.jethings.study.data.manager.LocalAccountManager_imp
import com.jethings.study.data.manager.RemoteAccountManager_imp
import com.jethings.study.domain.manager.AcademyManager
import com.jethings.study.domain.manager.AppEntryManager
import com.jethings.study.domain.manager.LocalAccountManager
import com.jethings.study.domain.manager.RemoteAccountManager
import com.jethings.study.presentation.view.screens.academy.viewModel.AcademyViewModel
import com.jethings.study.presentation.view.screens.createAcademy.viewModel.CreateAcademyViewModel
import com.jethings.study.presentation.view.screens.home.viewModel.HomeViewModel
import com.jethings.study.presentation.view.screens.logIn.viewModel.LogInViewModel
import com.jethings.study.presentation.view.screens.signUp.signUpViewModel.SignUpViewModel
import com.jethings.study.util.preferencesKeys.preferencesKeys.USER_SETTING
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = USER_SETTING)

val koinModule = module{



    /***************    GSON     ***********************/

    single<Json> {
        Json {
            ignoreUnknownKeys = true // Ignore unknown keys during deserialization
            isLenient = true         // Allow lenient parsing of JSON
            encodeDefaults = true    // Include default values in serialization
        }
    }





    /**************   database   ***********************/
    //db
    single<AppDataBase> {
        Room.databaseBuilder(
            context = get(),
            klass = AppDataBase::class.java,
            name = "study_database_test0.00"
        ).build()
    }

    //Dao
    single<AccountDao>{
        get<AppDataBase>().accountDao()
    }



    /**************   HttpClient   ***********************/

    single<HttpClient> {
        HttpClient(Android){
            install(Logging){
                level = LogLevel.ALL
            }

            install(ContentNegotiation) {
                json()
            }

        }
    }





    /********************  manager  ***********************/

    single<LocalAccountManager>{
        LocalAccountManager_imp()
    }

    single<RemoteAccountManager>{
        RemoteAccountManager_imp(
            client = get()
        )
    }

    single<AppEntryManager>{
        AppEntryManager_imp()
    }

    single<AcademyManager> {
        AcademyManager_imp(
            client = get()
        )
    }



    /********************  viewModels   **************************/

    viewModel {
        MainViewModel()
    }




    viewModel {
        LogInViewModel(
            remoteAccountManager = get()
        )
    }

    viewModel {
        SignUpViewModel(
            remoteAccountManager = get()
        )
    }

    viewModel {
        CreateAcademyViewModel(
            academyManager = get()
        )
    }

    viewModel {
        HomeViewModel(
            academyManager = get()
        )
    }

    viewModel {
        AcademyViewModel(
            academyManager = get()
        )
    }




}