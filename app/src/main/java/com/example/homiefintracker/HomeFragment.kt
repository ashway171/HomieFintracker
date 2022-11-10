package com.example.homiefintracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var transactionObject: ArrayList<HomeTransactionsModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}