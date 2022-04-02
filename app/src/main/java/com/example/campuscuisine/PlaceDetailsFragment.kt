package com.example.campuscuisine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.campuscuisine.MapsPlacesAPI.Common
import com.example.campuscuisine.MapsPlacesAPI.PlaceDetail
import com.example.campuscuisine.MapsPlacesAPI.IGoogleAPIService
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Response
import kotlin.text.StringBuilder


class PlaceDetailsFragment : Fragment() {

    lateinit var restaurantImage: ImageView
    lateinit var orderButton: Button
    lateinit var closeWindow: Button
    lateinit var restaurantName: TextView

    lateinit var googleServices: IGoogleAPIService

    var restaurant: PlaceDetail? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_place_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        googleServices = Common.googleApiService

        restaurantImage = view.findViewById(R.id.restaurant_image)
        restaurantName = view.findViewById(R.id.restaurant_name)
        orderButton = view.findViewById(R.id.order_button)
        closeWindow = view.findViewById(R.id.return_to_map)


//        closeWindow.setOnClickListener{
//            val fragmentManager: FragmentManager = childFragmentManager
//            fragmentManager.beginTransaction().replace(R.id.fragment_place_details, MapsFragment()).commit()
//            //fragmentManager.beginTransaction().remove(this).commit()
//        }

//        orderButton.setOnClickListener{
//            val fragmentManager: FragmentManager = childFragmentManager
//            fragmentManager.popBackStackImmediate()
//            fragmentManager.beginTransaction().replace(R.id.fragment_place_details, OrderFragment())
//
//        }

        if (Common.selectedRestaurant!!.photos != null && Common.selectedRestaurant!!.photos!!.isNotEmpty())
            Picasso.with(context)
                .load(
                    getRestaurantPhoto(
                        Common.selectedRestaurant!!.photos!![0].photo_reference!!,
                        850
                    )
                )
                .into(restaurantImage)

        googleServices.getDetailPlace(getPlaceDetailUrl(Common.selectedRestaurant!!.place_id!!))
            .enqueue(object : retrofit2.Callback<PlaceDetail> {
                override fun onResponse(call: Call<PlaceDetail>, response: Response<PlaceDetail>) {
                    restaurant = response.body()
                    restaurantName.text = restaurant!!.result!!.name

                }

                override fun onFailure(call: Call<PlaceDetail>?, t: Throwable) {
                    Toast.makeText(context, "does not have photo", Toast.LENGTH_SHORT).show()
                }
            })


    }

    private fun getRestaurantPhoto(photo_reference: String, photo_width: Int): String {

        val restaurantUrl = StringBuilder("https://maps.googleapis.com/maps/api/place/photo")
        restaurantUrl.append("?maxwidth=$photo_width&photoreference=$photo_reference&key=")

        return restaurantUrl.toString()
    }

    private fun getPlaceDetailUrl(place_id: String): String {
        val restaurantUrl = StringBuilder("https://maps.googleapis.com/maps/api/place/details/json")
        restaurantUrl.append("?place_id=$place_id&key=")
        return restaurantUrl.toString()

    }

}