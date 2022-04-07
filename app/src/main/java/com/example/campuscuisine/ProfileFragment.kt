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
import android.widget.Toast
import com.parse.ParseUser


class ProfileFragment : Fragment() {
    lateinit var LogoutButton: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LogoutButton = view.findViewById(R.id.btnLogout)

        LogoutButton.setOnClickListener{
            ParseUser.logOut()
            val currentUser = ParseUser.getCurrentUser()
            Log.i(ProfileFragment.TAG,"user logged out")
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
        }


    }


    companion object {
        const val TAG = "ProfileFragment"
    }
}