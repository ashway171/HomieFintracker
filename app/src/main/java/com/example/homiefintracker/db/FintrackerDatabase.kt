package com.example.homiefintracker.db

import android.content.Context
import com.example.homiefintracker.Converters
import androidx.room.*



@Database(entities = [ExpenseDetails::class], version = 2, exportSchema = false )
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
                        // Must write a migration from version 2 to 3
                        // Just added category icon to be stored in the database
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE!!
        }

    }


}