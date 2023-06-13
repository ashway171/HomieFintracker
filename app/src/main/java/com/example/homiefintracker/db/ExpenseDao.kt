package com.example.homiefintracker.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ExpenseDAO {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expenseDetails: ExpenseDetails)

    @Update
    suspend fun updateExpense(expenses: ExpenseDetails)

    @Delete
    suspend fun deleteExpense(expenseDetails: ExpenseDetails)

    @Query("SELECT * FROM expenses order by id DESC")
    fun getAllExpenses() : LiveData<List<ExpenseDetails>>

}