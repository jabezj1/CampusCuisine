package com.example.campuscuisine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.campuscuisine.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ismaeldivita.chipnavigation.ChipNavigationBar

class MainActivity : AppCompatActivity() {

    //    private lateinit var bottomNavView: BottomNavigationView
    val fragment = OrderFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)
        openDefaultFragment()
        val BottomNavBars = findViewById<ChipNavigationBar>(R.id.BottomNavBar)
        BottomNavBars.setItemSelected(R.id.Order)
        BottomNavBars.setOnItemSelectedListener{ it ->
            when(it){
                R.id.Order -> {
                    val mapsFragment = OrderFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout,mapsFragment).commit()
                }
                R.id.Checkout -> {
                    val checkoutFragment = CheckoutFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout,checkoutFragment).commit()
                }
                R.id.Profile -> {
                    val profileFragment = ProfileFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout,profileFragment).commit()
                }
                R.id.BreadMaker-> {
                    val breadmakerFragment = BreadMakerFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout,breadmakerFragment).commit()
                }
            }
        }



    }

    private fun openDefaultFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment)
        transaction.commit()
    }


//        setContentView(R.layout.activity_main)

//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)

    //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    //setUpTabBar()


//    private fun setUpTabBar(){
//        binding.BottomNavBar.setOnItemSelectedListener {
//            when(it){
//                R.id.Checkout -> binding.textMain.text = "Checkout"
//                R.id.Profile -> binding.textMain.text = "Profile"
//                R.id.BreadMaker -> binding.textMain.text = "BreadMaker"
//            }
//        }
//    }

}