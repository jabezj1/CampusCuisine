package com.example.campuscuisine

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class OrderActivity : AppCompatActivity() {

    private val menu = mutableListOf<Menu>()
    lateinit var tvRestaurntname : TextView
    lateinit var restaurnName : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)


        Log.e(TAG,"REACH")

        val jsonFileString = getJsonDataFromAsset(this, "RestaurantMenus.json")

        if (jsonFileString != null) {
            Log.i(TAG, jsonFileString)
        }

        val gson = Gson()
        val listItemType = object : TypeToken<List<RestaurantMenu>>() {}.type

        var items: List<RestaurantMenu> = gson.fromJson(jsonFileString, listItemType)
        //var food : List<MenuItems> = gson.fromJson(jsonFileString,)
        //items.forEachIndexed { idx, person -> Log.i(TAG, "> Item $idx:\n$person") }
        items.forEachIndexed { idx, person ->
            if(person.id == "121721"){
                restaurnName = person.name

            }
        }

        tvRestaurntname = findViewById(R.id.tvRestaurantName)
        tvRestaurntname.text = restaurnName


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