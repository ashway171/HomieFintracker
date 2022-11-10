package com.example.homiefintracker

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class HomeRecyclerViewAdapter(private val transactions : List<HomeTransactionsModel>) :
    RecyclerView.Adapter<HomeRecyclerViewAdapter.HomeViewHolder>() {

    class HomeViewHolder(itemView : View) : ViewHolder(itemView){
        val max: TextView = itemView.findViewById(R.id.max)
        val imageCategory : ImageView = itemView.findViewById(R.id.imageViewCategory)
        val category : TextView = itemView.findViewById(R.id.category)
        val amount : TextView = itemView.findViewById(R.id.amount)
        val percent: TextView = itemView.findViewById(R.id.percent)

        val container: RelativeLayout = itemView.findViewById(R.id.parent_rl)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val inflater : LayoutInflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.home_view_item, parent, false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        if(position == 0){
            holder.max.visibility = RecyclerView.VISIBLE
        }
        holder.imageCategory.setImageResource(transactions[position].imageCategory)
        holder.amount.text = transactions[position].amount
        holder.category.text = transactions[position].category
        holder.percent.text = transactions[position].percent

        var color = "#E1FFBE"
        if(position % 2 == 0){
            color = "#FFF8C0D3"
        }
        holder.container.setBackgroundColor(Color.parseColor(color))
    }

    override fun getItemCount(): Int {
        return transactions.size
    }
}