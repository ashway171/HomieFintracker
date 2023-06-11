package com.example.homiefintracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.homiefintracker.adapters.ViewPagerAdapter
import com.example.homiefintracker.databinding.ActivityHomeBinding
import nl.joery.animatedbottombar.AnimatedBottomBar

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private lateinit var animatedBottomBar: AnimatedBottomBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.animBottomBar.onTabSelected

        // Setting up the view pager
        val fragmentList: ArrayList<Fragment> = arrayListOf(
            HomeFragment(),
            ManagementFragment(),
            HistoryFragment()
        )
        val pagerAdapter = ViewPagerAdapter(fragmentList, this)
        binding.viewPager.adapter = pagerAdapter

        // Linking animated bottom bar to view pager
        binding.animBottomBar.setupWithViewPager2(binding.viewPager)

    }
}