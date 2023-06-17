package com.example.homiefintracker.repository

import androidx.lifecycle.LiveData
import com.example.homiefintracker.db.DepositDetails
import com.example.homiefintracker.db.ExpenseDetails
import com.example.homiefintracker.db.FintrackerDatabase

class FintrackerRepository(private val fintrackerDatabase: FintrackerDatabase) {

    // expense methods

    suspend fun insertExpense(expenseDetails: ExpenseDetails) = fintrackerDatabase.expenseDao().insertExpense(expenseDetails)

    suspend fun updateExpense(expenseDetails: ExpenseDetails) = fintrackerDatabase.expenseDao().updateExpense(expenseDetails)

    suspend fun deleteExpense(expenseDetails: ExpenseDetails) = fintrackerDatabase.expenseDao().deleteExpense(expenseDetails)

    fun getAllExpenses(): LiveData<List<ExpenseDetails>> = fintrackerDatabase.expenseDao().getAllExpenses()


    // deposit methods
    suspend fun insertDeposit(depositDetails: DepositDetails) = fintrackerDatabase.depositDao().insertDeposit(depositDetails)

    suspend fun updateDeposit(depositDetails: DepositDetails) = fintrackerDatabase.depositDao().updateDeposit(depositDetails)

    suspend fun deleteDeposit(depositDetails: DepositDetails) = fintrackerDatabase.depositDao().deleteDeposit(depositDetails)

    fun getAllDeposits(): LiveData<List<DepositDetails>> = fintrackerDatabase.depositDao().getAllDeposits()

}