package com.example.homiefintracker.repository

import androidx.lifecycle.LiveData
import com.example.homiefintracker.db.ExpenseDetails
import com.example.homiefintracker.db.FintrackerDatabase

class FintrackerRepository(private val fintrackerDatabase: FintrackerDatabase) {

    suspend fun insertExpense(expenseDetails: ExpenseDetails) = fintrackerDatabase.expenseDao().insertExpense(expenseDetails)

    suspend fun updateExpense(expenseDetails: ExpenseDetails) = fintrackerDatabase.expenseDao().updateExpense(expenseDetails)

    suspend fun deleteExpense(expenseDetails: ExpenseDetails) = fintrackerDatabase.expenseDao().deleteExpense(expenseDetails)

    fun getAllExpenses(): LiveData<List<ExpenseDetails>> = fintrackerDatabase.expenseDao().getAllExpenses()


}