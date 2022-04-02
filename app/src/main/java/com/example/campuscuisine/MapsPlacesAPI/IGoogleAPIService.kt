package com.example.campuscuisine.MapsPlacesAPI

import com.example.campuscuisine.MapsPlacesAPI.ClosePlaces
import com.example.campuscuisine.MapsPlacesAPI.PlaceDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface IGoogleAPIService {
    @GET
    fun getNearByPlaces(@Url url: String ): Call<ClosePlaces>

    @GET
    fun getDetailPlace(@Url url:String ): Call<PlaceDetail>
}