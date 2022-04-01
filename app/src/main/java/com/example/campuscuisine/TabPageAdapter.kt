package com.example.campuscuisine

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabPageAdapter(activity: FragmentActivity,private val tabCount:Int):FragmentStateAdapter(activity ) {
    override fun getItemCount(): Int = tabCount


    override fun createFragment(position: Int): Fragment {
        return when (position)
        {
            0->OrderFragment()
            1->CheckoutFragment()
            2->ProfileFragment()
            3->BreadMakerFragment()
            else->OrderFragment()
        }
    }
}