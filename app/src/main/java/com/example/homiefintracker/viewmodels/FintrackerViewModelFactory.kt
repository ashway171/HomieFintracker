package com.example.homiefintracker.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.homiefintracker.repository.FintrackerRepository

class FintrackerViewModelFactory(
    private val repository: FintrackerRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        try {
            val constructor = modelClass.getDeclaredConstructor(FintrackerRepository::class.java)
            return constructor.newInstance(repository)
        } catch (e: Exception) {
            Log.e("Error@ViewModelFactory", e.message.toString())
        }
        return super.create(modelClass)
    }
}