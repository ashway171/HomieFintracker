package com.example.homiefintracker.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homiefintracker.R
import com.example.homiefintracker.db.AssetDetails


class AssetRVAdapter(
    val context: Context,
    private val assetClickDeleteInterface: AssetTransactionDeleteInterface
) : RecyclerView.Adapter<AssetRVAdapter.AssetViewHolder>() {

    private val assets = ArrayList<AssetDetails>()


    inner class AssetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTV: TextView = itemView.findViewById<TextView>(R.id.assetNameTV)
        val dateTV: TextView = itemView.findViewById<TextView>(R.id.assetDateTV)
        val amountTV: TextView = itemView.findViewById<TextView>(R.id.assetAmountTV)
        val deleteIV: ImageView = itemView.findViewById<ImageView>(R.id.assetTransactionDeleteIV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetViewHolder {
        return AssetViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.asset_rv_item,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: AssetViewHolder, position: Int) {

        holder.nameTV.text = assets[position].assetName
        holder.dateTV.text = assets[position].assetDate
        holder.amountTV.text = assets[position].assetAmount

        holder.deleteIV.setOnClickListener {
            assetClickDeleteInterface.onDeleteIconClick(assets[position])
        }

    }

    override fun getItemCount(): Int {
        return assets.size
    }

    // update list method which inherently uses the notifyDataSetChanged() method
    // It shall  be replaced by diff utils
    fun updateList(newList: List<AssetDetails>) {
        assets.clear()
        assets.addAll(newList)
        notifyDataSetChanged()
    }
}

interface AssetTransactionDeleteInterface {
    fun onDeleteIconClick(asset: AssetDetails)
}
