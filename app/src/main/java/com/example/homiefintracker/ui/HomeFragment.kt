package com.example.homiefintracker.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homiefintracker.HomeTransactionsModel
import com.example.homiefintracker.R
import com.example.homiefintracker.adapters.HomeRecyclerViewAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var transactionObject: ArrayList<HomeTransactionsModel>
    private lateinit var floatingButton: FloatingActionButton
    private lateinit var expenseImageView: ImageView
    private lateinit var depositImageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataInitialize()
        recyclerView = view.findViewById(R.id.homeRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = HomeRecyclerViewAdapter(transactionObject)

        // Click Listener for Add Amount BottomSheet
        floatingButton= view.findViewById(R.id.fab)

        floatingButton.setOnClickListener{

            val dialog = activity?.let { it1 -> BottomSheetDialog(it1) }
            val view = layoutInflater.inflate(R.layout.add_amount_bottomsheet, null)
            dialog?.setContentView(view)
            dialog?.show()

            // Listener for AddExpenseActivity
            expenseImageView = view.findViewById(R.id.expense_IV_BottomSheet)
            expenseImageView.setOnClickListener{
                activity?.startActivity(Intent(activity, AddExpenseActivity::class.java))
            }

            // Listener for AddDepositActivity
            depositImageView = view.findViewById(R.id.deposit_IV_BottomSheet)
            depositImageView.setOnClickListener {
                activity?.startActivity(Intent(activity, AddDepositActivity::class.java))
            }

        }

    }

    private fun dataInitialize() {
        transactionObject = arrayListOf<HomeTransactionsModel>()
        transactionObject.add(HomeTransactionsModel( R.drawable.ic_money,"105,000", "PETROL", "60%"))
        transactionObject.add(HomeTransactionsModel( R.drawable.ic_calendar,"5,000", "PETROL", "10%"))
        transactionObject.add(HomeTransactionsModel( R.drawable.ic_money,"5,000", "PETROL", "5%"))
        transactionObject.add(HomeTransactionsModel( R.drawable.ic_arrow_back,"5,000", "PETROL", "5%"))
        transactionObject.add(HomeTransactionsModel( R.drawable.ic_arrow_forward,"5,000", "PETROL", "5%"))
        transactionObject.add(HomeTransactionsModel( R.drawable.ic_expense,"5,000", "PETROL", "8%"))
        transactionObject.add(HomeTransactionsModel( R.drawable.ic_add,"5,000", "PETROL", ".4%"))
        transactionObject.add(HomeTransactionsModel( R.drawable.ic_calendar,"5,000", "PETROL", ".1%"))
        transactionObject.add(HomeTransactionsModel( R.drawable.ic_calendar,"5,000", "PETROL", ".2%"))
        transactionObject.add(HomeTransactionsModel( R.drawable.ic_expense,"5,000", "PETROL", ".3%"))
    }
}