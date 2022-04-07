package com.example.campuscuisine.MapsPlacesAPI

object Common {

    private  val GOOGLE_API_URL = "https://maps.googleapis.com/"
    val googleApiService: IGoogleAPIService
        get()= RetrofitClient.getClient(GOOGLE_API_URL).create(IGoogleAPIService::class.java)

    var selectedRestaurant: Results?=null


}