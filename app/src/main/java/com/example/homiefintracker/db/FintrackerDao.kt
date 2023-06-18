package com.example.homiefintracker.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ExpenseDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expenseDetails: ExpenseDetails)

    @Update
    suspend fun updateExpense(expenseDetails: ExpenseDetails)

    @Delete
    suspend fun deleteExpense(expenseDetails: ExpenseDetails)

    @Query("SELECT * FROM expenses order by id DESC")
    fun getAllExpenses() : LiveData<List<ExpenseDetails>>

}

@Dao
interface DepositDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDeposit(depositsDetails: DepositDetails)

    @Update
    suspend fun updateDeposit(depositDetails: DepositDetails)

    @Delete
    suspend fun deleteDeposit(depositDetails: DepositDetails)

    @Query("SELECT * FROM deposits order by id DESC")
    fun getAllDeposits() : LiveData<List<DepositDetails>>

}

@Dao
interface AssetDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAsset(assetDetails: AssetDetails)

    @Update
    suspend fun updateAsset(assetDetails: AssetDetails)

    @Delete
    suspend fun deleteAsset(assetDetails: AssetDetails)

    @Query("SELECT * FROM assets order by id DESC")
    fun getAllAssets() : LiveData<List<AssetDetails>>

}