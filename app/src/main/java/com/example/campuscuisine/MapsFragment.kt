package com.example.campuscuisine

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentManager
import com.example.campuscuisine.Common.Common
import com.example.campuscuisine.Model.ClosePlaces
import com.example.campuscuisine.Remote.IGoogleAPIService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.StringBuilder


class MapsFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var mLastLocation: Location
    lateinit var fusedLocationClient: FusedLocationProviderClient

    lateinit var googleServices: IGoogleAPIService
    var UserLat: Double = 0.0
    var UserLng: Double= 0.0

    internal lateinit var  currentPlace: ClosePlaces

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        //init service below
        googleServices = Common.googleApiService

        fusedLocationClient = context?.let { LocationServices.getFusedLocationProviderClient(it) }!!
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0

        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.setOnMarkerClickListener(this)
        setUpMap()

        mMap.setOnMarkerClickListener { marker ->
            Common.selectedRestaurant = currentPlace.results!![Integer.parseInt(marker.snippet.toString())]

            val fragmentManager: FragmentManager = parentFragmentManager
            fragmentManager.beginTransaction().replace(R.id.map, PlaceDetailsFragment()).commit()
            true
        }
    }

    @SuppressLint("MissingPermission")
    private fun setUpMap() {
        if(context?.let { ActivityCompat.checkSelfPermission(it,Manifest.permission.ACCESS_FINE_LOCATION) }
            != PackageManager.PERMISSION_GRANTED ){

            ActivityCompat.requestPermissions(context as Activity, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), PERMISSION_CODE)
            return
        }
        mMap.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(Activity()) { location ->
            if(location != null){
                mLastLocation = location
                val currentLatLong = LatLng(location.latitude, location.longitude)
                UserLat = location.latitude
                UserLng = location.longitude

//                nearByPlace("restaurant")
                nearByPlace("meal_takeaway")
                //nearByPlace("meal_delivery")
//                nearByPlace("bakery")
//                nearByPlace("cafe")
                //placeMarkerOnMap(currentLatLong)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong,15.5f))
            }
        }
    }

    override fun onMarkerClick(p0: Marker): Boolean = false

    private fun nearByPlace(typePlace:String){
        mMap.clear()
        val url = findLocations(UserLat,UserLng, typePlace)

        googleServices.getNearByPlaces(url)
            .enqueue(object: Callback<ClosePlaces> {
                override fun onResponse(call: Call<ClosePlaces>, response: Response<ClosePlaces>) {
                    currentPlace = response.body()!!
                    if(response.isSuccessful){
                        for(i in 0 until response.body()!!.results!!.size){
                            val markerOptions = MarkerOptions()
                            val googlePlace = response.body()!!.results!![i]
                            val lat = googlePlace.geometry!!.location!!.lat
                            val lng = googlePlace.geometry!!.location!!.lng
                            val placeName =googlePlace.name
                            val latLng = LatLng(lat,lng)

                            markerOptions.position(latLng)
                            markerOptions.title(placeName)
                            markerOptions.snippet(i.toString())
                            mMap.addMarker(markerOptions)

                        }
                    }
                }

                override fun onFailure(call: Call<ClosePlaces>, t: Throwable) {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }

            })
    }

    private fun findLocations(userLat: Double, userLng: Double, typePlace: String): String {
        val googlePlaceUrl = StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json")
        googlePlaceUrl.append("?location=$userLat,$userLng&radius=6050")
        googlePlaceUrl.append("&type=$typePlace")
        googlePlaceUrl.append("&key=AIzaSyCCvUQH5E9yM-wcB21I2K0RTsAcdQSfW-o")
        return googlePlaceUrl.toString()
    }

    companion object{
        private const val PERMISSION_CODE = 1
    }
}