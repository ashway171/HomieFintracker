package com.example.homiefintracker

import androidx.recyclerview.widget.DiffUtil
import com.example.homiefintracker.ExpensesCategoryData

class ExpensesCategoryDiffCallback(private val oldCategories: List<ExpensesCategoryData>, private val newCategories: List<ExpensesCategoryData>): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldCategories.size
    }

    override fun getNewListSize(): Int {
        return newCategories.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldCategory = oldCategories[oldItemPosition]
        val newCategory = newCategories[newItemPosition]
        return oldCategory.id == newCategory.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldCategory = oldCategories[oldItemPosition]
        val newCategory = newCategories[newItemPosition]
        return oldCategory == newCategory
    }

}