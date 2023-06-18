package com.example.homiefintracker.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homiefintracker.db.AssetDetails
import com.example.homiefintracker.repository.FintrackerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AssetViewModel(private val repository: FintrackerRepository) : ViewModel(){

    suspend fun insertAsset(assetDetails: AssetDetails) = repository.insertAsset(assetDetails)

    suspend fun updateAsset(assetDetails: AssetDetails) = repository.updateAsset(assetDetails)

    fun deleteAsset(assetDetails: AssetDetails) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAsset(assetDetails)
    }

    fun getAllAssets() = repository.getAllAssets()
}