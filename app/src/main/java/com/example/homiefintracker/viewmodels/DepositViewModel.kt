package com.example.homiefintracker.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homiefintracker.db.DepositDetails
import com.example.homiefintracker.repository.FintrackerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DepositViewModel(private val repository: FintrackerRepository) : ViewModel(){

    suspend fun insertDeposit(depositDetails: DepositDetails) = repository.insertDeposit(depositDetails)

    suspend fun updateDeposit(depositDetails: DepositDetails) = repository.updateDeposit(depositDetails)

    fun deleteDeposit(depositDetails: DepositDetails) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteDeposit(depositDetails)
    }

    fun getAllDeposits() = repository.getAllDeposits()
}