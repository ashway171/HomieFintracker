package com.example.homiefintracker.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName="expenses")

data class ExpenseDetails(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")val id: Int?,
    @ColumnInfo(name="date")val date: String?,
    @ColumnInfo(name="category") val category: String?,
    @ColumnInfo(name="categoryIcon") val icon: Int?,
    @ColumnInfo(name="amount")val amount: String?,
    @ColumnInfo(name="name")val name: String,
    @ColumnInfo(name="paymentMode")val paymentMode: String?
)
