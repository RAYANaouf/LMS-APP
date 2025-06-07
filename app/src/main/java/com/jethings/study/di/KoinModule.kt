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
import com.jethings.study.data.manager.LocalUserManager_imp
import com.jethings.study.data.manager.RemoteAccountManager_imp
import com.jethings.study.data.manager.SuperAcademyManager_imp
import com.jethings.study.data.manager.TrainingProgramManager_imp
import com.jethings.study.data.manager.UserManager_imp
import com.jethings.study.domain.manager.AcademyManager
import com.jethings.study.domain.manager.AppEntryManager
import com.jethings.study.domain.manager.LocalUserManager
import com.jethings.study.domain.manager.RemoteAccountManager
import com.jethings.study.domain.manager.SuperAdminManager
import com.jethings.study.domain.manager.TrainingProgramManager
import com.jethings.study.domain.manager.UserManager
import com.jethings.study.presentation.view.screens.academy.viewModel.AcademyViewModel
import com.jethings.study.presentation.view.screens.academyOwners.viewModel.AcademyOwnersViewModel
import com.jethings.study.presentation.view.screens.createAcademy.viewModel.CreateAcademyViewModel
import com.jethings.study.presentation.view.screens.createSuperAdmin.viewModel.CreateSuperAdminViewModel
import com.jethings.study.presentation.view.screens.createTrainingProgram.viewModel.CreateTrainingProgramViewModel
import com.jethings.study.presentation.view.screens.home.viewModel.HomeViewModel
import com.jethings.study.presentation.view.screens.logIn.viewModel.LogInViewModel
import com.jethings.study.presentation.view.screens.profile.viewModel.ProfileViewModel
import com.jethings.study.presentation.view.screens.signUp.signUpViewModel.SignUpViewModel
import com.jethings.study.presentation.view.screens.superAdmin.viewModel.SuperAdminViewModel
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
                json(get()) // 👈 Inject my custom Json instance here
            }

        }
    }





    /********************  manager  ***********************/

    single<LocalUserManager>{
        LocalUserManager_imp(
            context = get()
        )
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

    single<SuperAdminManager> {
        SuperAcademyManager_imp(
            client = get()
        )
    }

    single<UserManager> {
        UserManager_imp(
            client = get()
        )
    }

    single<TrainingProgramManager> {
        TrainingProgramManager_imp(
            client = get()
        )
    }



    /********************  viewModels   **************************/

    viewModel {
        MainViewModel(
            localUserManager = get(),
            academyManager = get()
        )
    }




    viewModel {
        LogInViewModel(
            remoteAccountManager = get(),
            localUserManager = get()
        )
    }

    viewModel {
        SignUpViewModel(
            remoteAccountManager = get(),
            localUserManager = get()
        )
    }

    viewModel {
        CreateAcademyViewModel(
            academyManager = get()
        )
    }

    viewModel {
        HomeViewModel(
            academyManager = get(),
            superAdminManager = get()
        )
    }

    viewModel {
        AcademyViewModel(
            academyManager = get(),
            trainingProgramManager = get()
        )
    }

    viewModel {
        CreateSuperAdminViewModel(
            createSuperAdminManager = get()
        )
    }

    viewModel {
        SuperAdminViewModel(
            superAdminManager = get()
        )
    }

    viewModel {
        AcademyOwnersViewModel(
            academyManager = get(),
            userManager = get()
        )
    }

    viewModel {
        ProfileViewModel(
            localUserManager = get()
        )
    }

    viewModel {
        CreateTrainingProgramViewModel(
            trainingProgramManager = get()
        )
    }




}