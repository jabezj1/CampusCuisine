package com.example.campuscuisine

import com.google.gson.JsonArray

data class RestaurantMenu(val type: String, val id: String, val name: String ,val menuItems: List<MenuItems>? = null ) {
}

