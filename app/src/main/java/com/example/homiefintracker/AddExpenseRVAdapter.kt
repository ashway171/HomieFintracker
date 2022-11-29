package com.example.homiefintracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView


class AddExpenseRVAdapter(private val categories: List<ExpenseCategory>) :
    RecyclerView.Adapter<AddExpenseRVAdapter.ExpenseViewHolder>() {

    class ExpenseViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val expenseIcon : ImageView = itemView.findViewById(R.id.addExpenseCategoryIV)
        val expenseCategory : TextView = itemView.findViewById(R.id.addExpenseCategoryTV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val inflater : LayoutInflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.add_expense_item, parent, false)
        return ExpenseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        holder.expenseIcon.setImageResource(categories[position].expenseIcon)
        holder.expenseCategory.text = categories[position].category
    }

    override fun getItemCount(): Int {
        return categories.size
    }

}