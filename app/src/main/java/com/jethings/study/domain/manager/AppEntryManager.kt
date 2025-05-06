package com.jethings.study.domain.manager

interface AppEntryManager {

    suspend fun saveAppEntry()

    fun readAppEntry() : Boolean

}