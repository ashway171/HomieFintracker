package com.example.homiefintracker.ui

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.homiefintracker.R
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.homiefintracker.databinding.ActivityAddDepositBinding
import com.example.homiefintracker.db.DepositDetails
import com.example.homiefintracker.db.FintrackerDatabase
import com.example.homiefintracker.repository.FintrackerRepository
import com.example.homiefintracker.viewmodels.DepositViewModel
import com.example.homiefintracker.viewmodels.FintrackerViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AddDepositActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddDepositBinding

    private lateinit var depositDateString: String
    private var selectedChild: View? = null
    private var selectedDepositPaymentOption: String? = null

    // Declaring database and mvvm variables
    private lateinit var database: FintrackerDatabase
    private lateinit var repository: FintrackerRepository
    private lateinit var factory: FintrackerViewModelFactory
    private lateinit var viewModel: DepositViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_deposit)


        // This is meant to be done by DI....not a properly correct approach
        database = FintrackerDatabase.getDatabase(this)
        repository = FintrackerRepository(database)
        factory = FintrackerViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[DepositViewModel::class.java]


        // Date Segment

        val formatter = SimpleDateFormat("dd-MM-yy")
        val date = Date()
        val current = formatter.format(date)
        binding.setDateTV.text = current
        depositDateString = current
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
                    depositDateString = dateFormat.format(selectedDate)

                    // Set the selected date on currentDateTV Text View
                    binding.setDateTV.text = depositDateString

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

                selectedDepositPaymentOption = selectedOptionsTV.text.toString()
            }
        }


        // Save the expense details in the database

        binding.saveTV.setOnClickListener{

            CoroutineScope(Dispatchers.IO).launch {
                viewModel.insertDeposit(
                    DepositDetails(
                        null,
                        depositDateString,
                        binding.amountET.text.toString(),
                        binding.nameET.text.toString(),
                        selectedDepositPaymentOption
                    )
                )
            }.also {
                Toast.makeText(
                    this@AddDepositActivity,
                    "Deposit recorded successfully",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }
}