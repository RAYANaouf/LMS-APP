package com.jethings.study.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jethings.study.data.db.dao.AccountDao
import com.jethings.study.data.db.entities.Account


@Database(entities = [Account::class ], version = 2)
abstract class AppDataBase : RoomDatabase() {

    abstract fun accountDao()        : AccountDao

}