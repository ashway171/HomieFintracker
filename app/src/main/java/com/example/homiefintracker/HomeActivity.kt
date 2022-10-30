package com.example.homiefintracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2
import nl.joery.animatedbottombar.AnimatedBottomBar

class HomeActivity : AppCompatActivity() {

    private lateinit var animatedBottomBar: AnimatedBottomBar
    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        animatedBottomBar = findViewById(R.id.anim_bottom_bar)

        animatedBottomBar.onTabSelected

        // Setting up the view pager
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        val fragmentList: ArrayList<Fragment> = arrayListOf(
            HomeFragment(),
            ManagementFragment(),
            HistoryFragment()
        )
        val pagerAdapter = ViewPagerAdapter(fragmentList, this)
        viewPager.adapter = pagerAdapter

        // This links animated bottom bar to view pager
        animatedBottomBar.setupWithViewPager2(viewPager)

    }
}