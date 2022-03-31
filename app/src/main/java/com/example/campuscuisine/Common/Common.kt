package com.example.campuscuisine.Common

import com.example.campuscuisine.Remote.IGoogleAPIService
import com.example.campuscuisine.Remote.RetrofitClient

object Common {

    private  val GOOGLE_API_URL = "https://maps.googleapis.com/"

    val googleApiService: IGoogleAPIService
        get()=RetrofitClient.getClient(GOOGLE_API_URL).create(IGoogleAPIService::class.java)
}