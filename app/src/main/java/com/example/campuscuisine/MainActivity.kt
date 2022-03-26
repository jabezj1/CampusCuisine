package com.example.campuscuisine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.example.campuscuisine.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

//    private lateinit var bottomNavView: BottomNavigationView

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        setContentView(R.layout.activity_main)

//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setUpTabBar()

    }

    private fun setUpTabBar(){
        binding.BottomNavBar.setOnItemSelectedListener {
            when(it){
                R.id.Order -> binding.textMain.text = "Order"
                R.id.Checkout -> binding.textMain.text = "Checkout"
                R.id.Profile -> binding.textMain.text = "Profile"
                R.id.BreadMaker -> binding.textMain.text = "BreadMaker"
            }
        }
    }

}