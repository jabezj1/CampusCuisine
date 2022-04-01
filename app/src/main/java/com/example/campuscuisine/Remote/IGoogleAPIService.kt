package com.example.campuscuisine.Remote

import com.example.campuscuisine.Model.ClosePlaces
import com.example.campuscuisine.Model.PlaceDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface IGoogleAPIService {
    @GET
    fun getNearByPlaces(@Url url: String ): Call<ClosePlaces>

    @GET
    fun getDetailPlace(@Url url:String ): Call<PlaceDetail>
}