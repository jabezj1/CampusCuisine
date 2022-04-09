package com.example.campuscuisine

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "MenuItemAdapter"

class MenuItemAdapter(private val context: Context, private val menuItems: List<MenuItem>)
    : RecyclerView.Adapter<MenuItemAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val tvItemName = itemView.findViewById<TextView>(R.id.tvItemName)
        private val tvPrice = itemView.findViewById<TextView>(R.id.tvPrice)
        private val tvItemDesc = itemView.findViewById<TextView>(R.id.tvItemDesc)

        val selectedItem = mutableListOf<String>()

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(menuItem: MenuItem) {
            tvItemName.text = menuItem.name
            tvItemDesc.text = menuItem.description
            tvPrice.text = menuItem.price
        }

        override fun onClick(p0: View?) {
            selectedItem.add(tvItemName.text.toString())
            Log.i(TAG, tvItemName.text.toString())
            selectedItem.add(tvPrice.text.toString())
            Log.e(TAG, "added item price")
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemAdapter.ViewHolder {
        Log.i(TAG, "onCreateViewHolder")
        val view = LayoutInflater.from(context).inflate(R.layout.menu_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuItemAdapter.ViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder $position")
        val menuItem = menuItems[position]
        holder.bind(menuItem)
    }

    override fun getItemCount(): Int {
        return menuItems.size
    }

}