package com.example.campuscuisine

import com.parse.ParseClassName
import com.parse.ParseObject

@ParseClassName ("User")
class User: ParseObject() {

    fun getName(): String? {
        return getString(KEY_USER)
    }

    fun setName(fullName: String) {
        put(KEY_USER, fullName)
    }

    companion object {
        const val KEY_USER = "Full_Name"
    }

}