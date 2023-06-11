package com.example.homiefintracker.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import com.example.homiefintracker.ExpensesCategoryData
import com.example.homiefintracker.ExpensesCategoryDiffCallback
import com.example.homiefintracker.R


/**
 * Basically what we are doing is creating a listener interface and have it's functionality
 * of click implemented in MainActivity
 *
 */

class ExpensesCategoryRVAdapter(private val itemClickListener: ItemClickListener) : RecyclerView.Adapter<ExpensesCategoryRVAdapter.ExpenseViewHolder>() {

    private val categories: MutableList<ExpensesCategoryData> = mutableListOf()
    private var selectedItemPosition: Int = RecyclerView.NO_POSITION


    inner class ExpenseViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val expenseIcon: ImageView = itemView.findViewById(R.id.addExpenseCategoryIV)
        val expenseName: TextView = itemView.findViewById(R.id.addExpenseCategoryTV)
        val rootLayout: LinearLayout = itemView.findViewById(R.id.rootLayout)
        private lateinit var fieldName: String

        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(view: View) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                itemClickListener.onItemClick(position, fieldName)
            }
        }

        fun bind(category: ExpensesCategoryData, position: Int) {


            expenseIcon.setImageResource(categories[position].expenseIcon)
            expenseName.text = category.expenseName

            fieldName = category.expenseName

            // Set the background color based on the selected state
            if (selectedItemPosition == adapterPosition) {
                rootLayout.setBackgroundResource(R.drawable.expenses_category_rv_selected) // Set your desired background color here
            } else {
                rootLayout.setBackgroundColor(Color.TRANSPARENT) // Set the default background color here
            }

            // Set click listener to handle item selection and display Toast message
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    // Deselect previously selected item
                    val previousSelectedItemPosition = selectedItemPosition
                    selectedItemPosition = position
                    notifyItemChanged(previousSelectedItemPosition)

                    // Select newly clicked item
                    notifyItemChanged(position)

                    // Display Toast message with the corresponding field value
                    itemClickListener.onItemClick(position, fieldName)
                }
            }
        }

    }

    fun updateUsers(newCategories: List<ExpensesCategoryData>) {
        val diffResult = DiffUtil.calculateDiff(ExpensesCategoryDiffCallback(categories, newCategories))
        categories.clear()
        categories.addAll(newCategories)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.add_expense_item, parent, false)
        return ExpenseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val category = categories[position]
        holder.bind(category, position)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    interface ItemClickListener {
        fun onItemClick(position: Int, fieldName: String)
    }

}

