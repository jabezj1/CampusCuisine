package com.example.campuscuisine

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.parse.ParseObject
import com.parse.ParseQuery
import com.parse.ParseUser


class ProfileFragment : Fragment() {
    lateinit var LogoutButton: ImageButton
    lateinit var FavoritesButton: ImageButton
    lateinit var BreadMakerButton: ImageButton
    lateinit var UserId: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LogoutButton = view.findViewById(R.id.btnLogout)
        UserId = view.findViewById(R.id.tvUser)
        BreadMakerButton = view.findViewById(R.id.btnBreadMaker)

        val getUser = ParseUser.getCurrentUser().get("username")
        UserId.text = getUser.toString()

        LogoutButton.setOnClickListener{
            ParseUser.logOut()
            val currentUser = ParseUser.getCurrentUser()
            Log.i(TAG,"user logged out")
            val intent = Intent(context, IntroActivity::class.java)
            startActivity(intent)
        }

        Log.i(TAG, ParseUser.getCurrentUser().get("Breadmaker").toString())

        BreadMakerButton.setOnClickListener{
          //updateBreadmaker()
            val user = ParseUser.getCurrentUser()
            if (ParseUser.getCurrentUser().get("Breadmaker") == false) {
                user.put("Breadmaker", true)
                user.saveInBackground { e ->
                    if (e == null) {
                        Toast.makeText(
                            context,
                            "You Can Now Deliver to your Campus",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.e(TAG, "user is a breadMaker")
                    } else {
                        Log.i(TAG, "cannot make breadmaker")
                    }
                }
            }else{
                user.put("Breadmaker", false)
                user.saveInBackground { e ->
                    if (e == null) {
                        Toast.makeText(
                            context,
                            "You Can Only Order Now!",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.e(TAG, "user is NOT a BreadMaker")
                    }
                }

            }
        }


    }



    companion object {
        const val TAG = "ProfileFragment"
    }
}