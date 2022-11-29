package com.example.homiefintracker

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.VISIBLE
import android.widget.*
import androidx.constraintlayout.widget.ConstraintSet.GONE
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class AddExpenseActivity : AppCompatActivity() {
    private lateinit var expenseObject: ArrayList<ExpenseCategory>
    private lateinit var expenseCategoryRV: RecyclerView
    private lateinit var backDatedSwitch: Switch
    private lateinit var currentDate: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)

        dataInitialize()

        expenseCategoryRV = findViewById(R.id.categoryRV)

        expenseCategoryRV.layoutManager = LinearLayoutManager(this, HORIZONTAL, false)
        expenseCategoryRV.setHasFixedSize(true)
        expenseCategoryRV.adapter = AddExpenseRVAdapter(expenseObject)



        backDatedSwitch = findViewById(R.id.backDatedSwitch)
        currentDate = findViewById(R.id.currentDateTV)
        val formatter = SimpleDateFormat("dd-MM-yyyy")
        val date = Date()
        val current = formatter.format(date)
        currentDate.text = current

        backDatedSwitch.setOnCheckedChangeListener { _, isChecked ->
            // do whatever you need to do when the switch is toggled here

            // If switch is on, only then select date
            if (isChecked) {

                // on below line we are getting the instance of our calendar.
                val c = Calendar.getInstance()

                // on below line we are getting our day, month and year.
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)

                // on below line we are creating a variable for date picker dialog.
                val datePickerDialog = DatePickerDialog(
                    // on below line we are passing context.
                    this,
                    { view, year, monthOfYear, dayOfMonth ->
                        // on below line we are setting
                        // date to our text view.
                        currentDate.text =
                            (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    },
                    // on below line we are passing year, month
                    // and day for the selected date in our date picker.
                    year,
                    month,
                    day
                )
                // at last we are calling show to display our date picker dialog.
                datePickerDialog.show()

            }
        }

    }

    // method to inflate the options menu when the user opens the menu for the first time
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_expense_action_menu, menu)
        return true
    }

    // method to control the operations that will happen when user clicks on the action buttons
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            // Toast for now
            R.id.right -> Toast.makeText(this, "Expense Recorded!!", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun dataInitialize() {
        expenseObject = arrayListOf<ExpenseCategory>()
        expenseObject.add(ExpenseCategory(R.drawable.cigarettes, "Cigarettes"))
        expenseObject.add(ExpenseCategory(R.drawable.clg_fine, "Fine"))
        expenseObject.add(ExpenseCategory(R.drawable.transport, "Transport"))
        expenseObject.add(ExpenseCategory(R.drawable.phone, "Recharge"))
        expenseObject.add(ExpenseCategory(R.drawable.liquor, "Alcohol"))
        expenseObject.add(ExpenseCategory(R.drawable.restaurant, "Food"))
        expenseObject.add(ExpenseCategory(R.drawable.movie, "Entertainment"))
        expenseObject.add(ExpenseCategory(R.drawable.health, "Health"))
        expenseObject.add(ExpenseCategory(R.drawable.clothes, "Clothes"))
        expenseObject.add(ExpenseCategory(R.drawable.gifts, "Gifts"))
        expenseObject.add(ExpenseCategory(R.drawable.misc, "Others"))
    }

}
