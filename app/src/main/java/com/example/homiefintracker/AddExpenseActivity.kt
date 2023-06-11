package com.example.homiefintracker

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homiefintracker.adapters.ExpensesCategoryRVAdapter
import com.example.homiefintracker.databinding.ActivityAddExpenseBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AddExpenseActivity : AppCompatActivity(), ExpensesCategoryRVAdapter.ItemClickListener {

    private lateinit var binding: ActivityAddExpenseBinding
    private lateinit var expenseObject: ArrayList<ExpensesCategoryData>
    private lateinit var dateString: String
    private lateinit var categoryName: String
    private var selectedChild: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_expense)


        // Category Recycler View

        expenseObject = ArrayList()
        dataInitialize()
        binding.categoryRV.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        binding.categoryRV.setHasFixedSize(true)
        val adapter = ExpensesCategoryRVAdapter(this)
        binding.categoryRV.adapter = adapter
        adapter.updateUsers(expenseObject)


        // Date Segment

        val formatter = SimpleDateFormat("dd-MM-yy")
        val date = Date()
        val current = formatter.format(date)
        binding.setDateTV.text = current
        // dateString = current
        // Open calendar by dateDropDownIV and perform operations
        binding.dateDropDownIV.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog =
                DatePickerDialog(this, { view, year, monthOfYear, dayOfMonth ->
                    // handle date selection here

                    val selectedDate = Calendar.getInstance().apply {
                        set(year, monthOfYear, dayOfMonth)
                    }.time

                    val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                    dateString = dateFormat.format(selectedDate)

                    // Set the selected date on currentDateTV Text View
                    binding.setDateTV.text = dateString.toString()

                }, year, month, day)

            datePickerDialog.show()
        }


        // Payment Mode Selections

        for (i in 0 until binding.paymentOptionsRL.childCount) {
            val child: View = binding.paymentOptionsRL.getChildAt(i)

            child.setOnClickListener {
                // Deselect the previously selected child
                selectedChild?.isSelected = false

                // Update the selected child
                selectedChild = child
                selectedChild?.isSelected = true

                // Change the background of the selected child
                selectedChild?.setBackgroundResource(R.drawable.payment_mode_selected_bg)

                // Perform any additional actions with the selected child
                // ...
            }
        }

    }

    override fun onItemClick(position: Int, fieldName: String) {
        // Handle category item  click event
        categoryName = fieldName
        Toast.makeText(this, "$fieldName selected", Toast.LENGTH_SHORT).show()
    }

    // For Expenses Category
    private fun dataInitialize() {
        expenseObject = arrayListOf<ExpensesCategoryData>()
        expenseObject.add(ExpensesCategoryData(1, R.drawable.cigarettes, "Cigarettes"))
        expenseObject.add(ExpensesCategoryData(2, R.drawable.liquor, "Alcohol"))
        expenseObject.add(ExpensesCategoryData(3, R.drawable.person, "Person"))
        expenseObject.add(ExpensesCategoryData(4, R.drawable.clg_fine, "Fine"))
        expenseObject.add(ExpensesCategoryData(5, R.drawable.transport, "Transport"))
        expenseObject.add(ExpensesCategoryData(6, R.drawable.phone, "Recharge"))
        expenseObject.add(ExpensesCategoryData(7, R.drawable.restaurant, "Food"))
        expenseObject.add(ExpensesCategoryData(8, R.drawable.movie, "Entertainment"))
        expenseObject.add(ExpensesCategoryData(9, R.drawable.health, "Health"))
        expenseObject.add(ExpensesCategoryData(10, R.drawable.clothes, "Clothes"))
        expenseObject.add(ExpensesCategoryData(11, R.drawable.gifts, "Gifts"))
        expenseObject.add(ExpensesCategoryData(12, R.drawable.misc, "Others"))
    }
}