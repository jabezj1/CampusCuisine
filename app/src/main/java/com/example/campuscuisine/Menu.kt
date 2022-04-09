package com.example.campuscuisine

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import org.json.JSONException

private const val TAG = "Menu.kt"
private const val JSON_PATH = "assets/RestaurantMenus.json"

class Menu : AppCompatActivity() { // added : AppCompatActivity(), removed () from Menu
    private val menuItems = mutableListOf<MenuItem>()
    private lateinit var rvMenuItems:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_order)
        rvMenuItems = findViewById(R.id.rvMenuItems)

        val menuItemAdapter = MenuItemAdapter(this, menuItems)
        rvMenuItems.adapter = menuItemAdapter
        rvMenuItems.layoutManager = LinearLayoutManager(this)

        val client = AsyncHttpClient()
        client.get(JSON_PATH, object: JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int, headers: Headers?, response: String?, throwable: Throwable?
            ) {
                Log.e(TAG, "onFailure $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
                Log.i(TAG, "onSuccess: JSON data $json")
                try {
                    val menuItemJSONArray = json.jsonObject.getJSONArray("results")
                    menuItems.addAll(MenuItem.fromJsonArray(menuItemJSONArray))
                    menuItemAdapter.notifyDataSetChanged()
                    Log.i(TAG, "Menu items $menuItems")
                } catch (e: JSONException) {
                    Log.e(TAG, "Encountered exception $e")
                }
            }
        })
    }
}