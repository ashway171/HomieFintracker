package com.example.homiefintracker.ui

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.homiefintracker.R
import java.text.SimpleDateFormat
import java.util.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homiefintracker.adapters.AssetRVAdapter
import com.example.homiefintracker.adapters.AssetTransactionDeleteInterface
import com.example.homiefintracker.databinding.ActivityAddAssetBinding
import com.example.homiefintracker.db.AssetDetails
import com.example.homiefintracker.db.FintrackerDatabase
import com.example.homiefintracker.repository.FintrackerRepository
import com.example.homiefintracker.viewmodels.AssetViewModel
import com.example.homiefintracker.viewmodels.FintrackerViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddAssetActivity : AppCompatActivity(), AssetTransactionDeleteInterface {

    private lateinit var binding: ActivityAddAssetBinding

    private lateinit var assetDateString: String

    // Declaring database and mvvm variables
    private lateinit var database: FintrackerDatabase
    private lateinit var repository: FintrackerRepository
    private lateinit var factory: FintrackerViewModelFactory
    private lateinit var viewModel: AssetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_asset)


        // This is meant to be done by DI....not a properly correct approach
        database = FintrackerDatabase.getDatabase(this)
        repository = FintrackerRepository(database)
        factory = FintrackerViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[AssetViewModel::class.java]


        // Asset Recycler View
        binding.assetRV.layoutManager = LinearLayoutManager(this)
        val assetRVAdapter = AssetRVAdapter(this, this)
        binding.assetRV.adapter = assetRVAdapter

        // Getting the list with viewModel
        viewModel.getAllAssets().observe(this) { list ->
            list?.let {
                assetRVAdapter.updateList(it)
            }
        }


        // Date Segment

        val formatter = SimpleDateFormat("dd-MM-yy")
        val date = Date()
        val current = formatter.format(date)
        binding.setDateTV.text = current
        assetDateString = current
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
                    assetDateString = dateFormat.format(selectedDate)

                    // Set the selected date on currentDateTV Text View
                    binding.setDateTV.text = assetDateString

                }, year, month, day)

            datePickerDialog.show()
        }


        // Save the expense details in the database

        binding.saveTV.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {
                viewModel.insertAsset(
                    AssetDetails(
                        null,
                        assetDateString,
                        binding.nameET.text.toString(),
                        binding.amountET.text.toString()
                    )
                )
            }.also {
                Toast.makeText(
                    this@AddAssetActivity,
                    "Asset recorded successfully",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }

    override fun onDeleteIconClick(asset: AssetDetails) {
        viewModel.deleteAsset(asset)
    }
}