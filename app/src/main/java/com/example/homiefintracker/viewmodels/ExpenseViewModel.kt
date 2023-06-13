package com.example.homiefintracker.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homiefintracker.db.ExpenseDetails
import com.example.homiefintracker.repository.FintrackerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExpenseViewModel(private val repository: FintrackerRepository) : ViewModel(){

    suspend fun insertExpense(expenseDetails: ExpenseDetails) = repository.insertExpense(expenseDetails)

    suspend fun updateExpense(expenseDetails: ExpenseDetails) = repository.updateExpense(expenseDetails)

    fun deleteExpense(expenseDetails: ExpenseDetails) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteExpense(expenseDetails)
    }

    fun getAllExpenses() = repository.getAllExpenses()
}