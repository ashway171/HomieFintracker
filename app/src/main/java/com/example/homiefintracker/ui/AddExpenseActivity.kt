package com.example.homiefintracker.ui

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homiefintracker.ExpensesCategoryData
import com.example.homiefintracker.R
import com.example.homiefintracker.adapters.ExpensesCategoryRVAdapter
import com.example.homiefintracker.databinding.ActivityAddExpenseBinding
import com.example.homiefintracker.db.ExpenseDetails
import com.example.homiefintracker.db.FintrackerDatabase
import com.example.homiefintracker.repository.FintrackerRepository
import com.example.homiefintracker.viewmodels.ExpenseViewModel
import com.example.homiefintracker.viewmodels.FintrackerViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AddExpenseActivity : AppCompatActivity(), ExpensesCategoryRVAdapter.ItemClickListener {

    private lateinit var binding: ActivityAddExpenseBinding
    private lateinit var expenseObject: ArrayList<ExpensesCategoryData>
    private lateinit var dateString: String
    private lateinit var categoryName: String
    private var categoryIcon: Int? = null
    private var selectedChild: View? = null
    private var selectedPaymentOption: String? = null

    // Declaring database and mvvm variables
    private lateinit var database: FintrackerDatabase
    private lateinit var repository: FintrackerRepository
    private lateinit var factory: FintrackerViewModelFactory
    private lateinit var viewModel: ExpenseViewModel


    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_expense)

        // This is meant to be done by DI....not a properly correct approach
        database = FintrackerDatabase.getDatabase(this)
        repository = FintrackerRepository(database)
        factory = FintrackerViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[ExpenseViewModel::class.java]


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
        dateString = current
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
                    binding.setDateTV.text = dateString

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

                // Access the TextView of the Selected PaymentLinearLayout
                val optionsLinearLayout: LinearLayout = selectedChild as LinearLayout

                val selectedOptionsTV: TextView = optionsLinearLayout.getChildAt(1) as TextView

                selectedPaymentOption = selectedOptionsTV.text.toString()
            }
        }


        // Save the expense details in the database

        binding.saveTV.setOnClickListener{

            CoroutineScope(Dispatchers.IO).launch {
                viewModel.insertExpense(
                    ExpenseDetails(
                        null,
                        dateString,
                        categoryName,
                        categoryIcon,
                        binding.amountET.text.toString(),
                        binding.nameET.text.toString(),
                        selectedPaymentOption
                    )
                )
            }.also {
                Toast.makeText(
                    this@AddExpenseActivity,
                    "Expense recorded successfully",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }

    override fun onItemClick(position: Int, fieldName: String, iconId: Int) {
        // Handle category item  click event
        categoryName = fieldName
        categoryIcon = iconId
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