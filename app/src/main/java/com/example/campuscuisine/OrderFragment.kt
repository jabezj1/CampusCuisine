package com.example.campuscuisine

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException


class OrderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fun getJsonDataFromAsset(context: Context, fileName: String): String? {
            val jsonString: String
            try {
                jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
            } catch (ioException: IOException) {
                ioException.printStackTrace()
                return null
            }
            return jsonString
        }

        val jsonFileString = context?.let { getJsonDataFromAsset(it, "RestaurantMenus.json") }

        if (jsonFileString != null) {
            Log.i("data", jsonFileString)
        }

        val gson = Gson()
        val listItemType = object : TypeToken<List<Menu>>() {}.type
        var items: List<Menu> = gson.fromJson(jsonFileString, listItemType)
        items.forEachIndexed { idx, person -> Log.i("data", "> Item $idx:\n$person") }

        return inflater.inflate(R.layout.fragment_order, container, false)
    }

}