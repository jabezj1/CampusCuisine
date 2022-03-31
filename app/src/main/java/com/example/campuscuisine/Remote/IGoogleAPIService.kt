package com.example.campuscuisine.Remote

import com.example.campuscuisine.Model.ClosePlaces
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface IGoogleAPIService {
    @GET
    fun getNearByPlaces(@Url url: String ): Call<ClosePlaces>
}