package com.example.homiefintracker.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="assets")

data class AssetDetails(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")val id: Int?,
    @ColumnInfo(name="date")val assetDate: String?,
    @ColumnInfo(name="name")val assetName: String?,
    @ColumnInfo(name="amount")val assetAmount: String?
)
