package com.example.campuscuisine.Model

import android.widget.DatePicker

class Results {
    var name:String? = null
    var geometry:Geometry? = null

    var photos:Array<Photos>? = null
    var id:String?= null
    var place_id:String? = null
    var price_level:Int? = 0
    var rating: Double = 0.0
    var types: Array<String>? = null
    var openingHours : OpeningHours? = null

    var address_components: Array<AddressComponent>?= null
    var adr_addresss:String? = null
    var formatted_address: String? = null
    var formatted_phone_number:String? = null

    var international_phone_number:String? = null
    var url:String? = null
    var website:String? = null
}