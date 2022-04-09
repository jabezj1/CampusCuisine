package com.example.campuscuisine

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.json.JSONArray

@Parcelize
data class MenuItem(
    val id: Int,
    val name: String,
    val position: Int,
    val description: String,
    val price: String
) : Parcelable {
        //@IgnoredOnParcel

        companion object {

            fun fromJsonArray(menuItemJsonArray: JSONArray): List<MenuItem> {
                val menuItems = mutableListOf<MenuItem>()

                for (i in 0 until menuItemJsonArray.length()) {

                    val menuItemJson = menuItemJsonArray.getJSONObject(i)

                    menuItems.add(
                        MenuItem(
                            menuItemJson.getInt("id"),
                            menuItemJson.getString("name"),
                            menuItemJson.getInt("position"),
                            menuItemJson.getString("description"),
                            menuItemJson.getString("price")
                        )
                    )
                }

                return menuItems
            }
        }
    }