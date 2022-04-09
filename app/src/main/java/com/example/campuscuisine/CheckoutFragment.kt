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


class CheckoutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_checkout, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerview = view?.findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        if (recyclerview != null) {
            recyclerview.layoutManager = LinearLayoutManager(activity)
        }

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemsViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
//        for (i in 1..20) {
//            data.add(ItemsViewModel("$"+i, "Item " + i))
//        }
        data.add(ItemsViewModel(
            "$4.00 x 2", "Buffalo Wings Q:2"))
        data.add(ItemsViewModel(
            "$1.50 x 1", "Coke Can Q:1"))

        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter(data)

        // Setting the Adapter with the recyclerview
        if (recyclerview != null) {
            recyclerview.adapter = adapter
        }

        view.findViewById<Button>(R.id.button).setOnClickListener {
            Toast.makeText(context,"Order Has Been Made! \nStill want to head to class?", Toast.LENGTH_LONG). show()
        }
    }
}