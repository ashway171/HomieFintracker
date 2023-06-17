package com.example.homiefintracker.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homiefintracker.R
import com.example.homiefintracker.db.ExpenseDetails


class HistoryExpenseRVAdapter(
    val context: Context,
    private val expenseClickDeleteInterface: ExpenseTransactionDeleteInterface
) : RecyclerView.Adapter<HistoryExpenseRVAdapter.ViewHolder>() {

    private val expenses = ArrayList<ExpenseDetails>()


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryTV: TextView = itemView.findViewById(R.id.historyCategoryTV)
        val amountTV: TextView = itemView.findViewById<TextView>(R.id.historyAmountTV)
        val dateTV: TextView = itemView.findViewById<TextView>(R.id.historyDateTV)
        val nameTV: TextView = itemView.findViewById<TextView>(R.id.historyNameTV)
        val iconIV: ImageView = itemView.findViewById(R.id.historyCategoryIconIV)
        val paymentModeTV: TextView = itemView.findViewById(R.id.historyPaymentModeTV)
        val deleteIV: ImageView = itemView.findViewById<ImageView>(R.id.historyTransactionDeleteIV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.history_item,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.categoryTV.text = expenses[position].category
        holder.amountTV.text = expenses[position].amount
        holder.nameTV.text = expenses[position].name
        val imageId = expenses[position].icon
        if (imageId != null) {
            holder.iconIV.setImageResource(imageId)
        }
        holder.dateTV.text = expenses[position].date
        holder.paymentModeTV.text = expenses[position].paymentMode
        holder.deleteIV.setOnClickListener {
            expenseClickDeleteInterface.onDeleteIconClick(expenses[position])
        }

    }

    override fun getItemCount(): Int {
        return expenses.size
    }

    // update list method which inherently uses the notifyDataSetChanged() method
    // It shall  be replaced by diff utils
    fun updateList(newList: List<ExpenseDetails>) {
        expenses.clear()
        expenses.addAll(newList)
        notifyDataSetChanged()
    }
}

interface ExpenseTransactionDeleteInterface {
    fun onDeleteIconClick(expense: ExpenseDetails)
}
