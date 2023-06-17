package com.example.homiefintracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homiefintracker.adapters.ExpenseTransactionDeleteInterface
import com.example.homiefintracker.adapters.HistoryExpenseRVAdapter
import com.example.homiefintracker.db.ExpenseDetails
import com.example.homiefintracker.db.FintrackerDatabase
import com.example.homiefintracker.repository.FintrackerRepository
import com.example.homiefintracker.viewmodels.ExpenseViewModel
import com.example.homiefintracker.viewmodels.ExpenseViewModelFactory

class HistoryFragment : Fragment(), ExpenseTransactionDeleteInterface {

    private lateinit var layoutView: View
    private lateinit var mainRV: RecyclerView
    private lateinit var viewModel: ExpenseViewModel
    private lateinit var factory: ExpenseViewModelFactory
    private lateinit var repository: FintrackerRepository
    private lateinit var database: FintrackerDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        layoutView = inflater.inflate(R.layout.fragment_history, container, false)
        return layoutView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = context?.let { FintrackerDatabase.getDatabase(it) }!!

        repository = FintrackerRepository(database)

        factory = ExpenseViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory)[ExpenseViewModel::class.java]

        // Setting up the RecyclerView
        mainRV = view.findViewById(R.id.historyRV)
        mainRV.layoutManager = LinearLayoutManager(context)
        val historyRVAdapter = HistoryExpenseRVAdapter(requireContext(), this)
        mainRV.adapter = historyRVAdapter

        // Getting the list with viewModel
        viewModel.getAllExpenses().observe(viewLifecycleOwner) { list ->
            list?.let {
                historyRVAdapter.updateList(it)
            }
        }

    }

    override fun onDeleteIconClick(expense: ExpenseDetails) {
        viewModel.deleteExpense(expense)
    }

}