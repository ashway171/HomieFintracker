package com.example.homiefintracker.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="deposits")

data class DepositDetails(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")val id: Int?,
    @ColumnInfo(name="date")val depositDate: String?,
    @ColumnInfo(name="amount")val depositAmount: String?,
    @ColumnInfo(name="name")val depositName: String,
    @ColumnInfo(name="paymentMode")val depositPaymentMode: String?
)
