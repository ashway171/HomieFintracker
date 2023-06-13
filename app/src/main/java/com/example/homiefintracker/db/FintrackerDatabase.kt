package com.example.homiefintracker.db

import android.content.Context
import com.example.homiefintracker.Converters
import androidx.room.*


import kotlinx.coroutines.InternalCoroutinesApi

@Database(entities = [ExpenseDetails::class], version = 1, exportSchema = false )
@TypeConverters(Converters::class)
abstract class FintrackerDatabase : RoomDatabase() {

    abstract fun expenseDao(): ExpenseDAO

    companion object {

        @Volatile
        private var INSTANCE: FintrackerDatabase? = null


        fun getDatabase(context: Context): FintrackerDatabase {

            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        FintrackerDatabase::class.java,
                        "fintrackerDB"
                    )
                        .build()
                }
            }
            return INSTANCE!!
        }

    }


}