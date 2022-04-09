package com.example.campuscuisine

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException


class OrderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.i(TAG,"DOES THIS REACH")
        return inflater.inflate(R.layout.fragment_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i(TAG,"DOES THIS REACH")

//        fun getJsonDataFromAsset(context: Context, fileName: String): String? {
//            val jsonString: String
//            try {
//                jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
//            } catch (ioException: IOException) {
//                ioException.printStackTrace()
//                return null
//            }
//            return jsonString
//        }

        val jsonFileString = context?.let { getJsonDataFromAsset(it, "assets/RestaurantMenus.json") }

        if (jsonFileString != null) {
            Log.i(TAG, jsonFileString)
        }

        val gson = Gson()
        val listItemType = object : TypeToken<List<RestaurantMenu>>() {}.type
        var items: List<RestaurantMenu> = gson.fromJson(jsonFileString, listItemType)
        items.forEachIndexed { idx, person -> Log.i(TAG, "> Item $idx:\n$person") }

    }

    public fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    companion object{
       const val TAG = "ORDER"
    }

}