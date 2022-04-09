package com.example.campuscuisine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class BreadMakerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bread_maker, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerview = view?.findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        if (recyclerview != null) {
            recyclerview.layoutManager = LinearLayoutManager(activity)
        }

        // ArrayList of class ItemsViewModel
        val data = ArrayList<BmItemsViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view

        data.add(BmItemsViewModel("Buffalo Wings Q:2 \n Coke Can Q:1", "Order For: " + "Joe"))

        data.add(BmItemsViewModel(
                "Nachos Q:2", "Order For: " + "Bob"))

        data.add(BmItemsViewModel("The Classic Burger Q:1", "Order For: " + "Job"))


        // This will pass the ArrayList to our Adapter
        val adapter = BmCustomAdapter(data)

        // Setting the Adapter with the recyclerview
        if (recyclerview != null) {
            recyclerview.adapter = adapter
        }

        view.findViewById<Button>(R.id.btdone).setOnClickListener {
            Toast.makeText(context,"Great Job! Try not to stay broke.", Toast.LENGTH_SHORT). show()
        }
    }
}