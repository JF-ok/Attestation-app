package com.example.emoty

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.emoty.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var bottomNavigarionMenu: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigarionMenu = findViewById(R.id.bottom_navigation_menu)
        var fragment: Fragment? = null
        bottomNavigarionMenu.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.calendar_fragment_page -> {
                    fragment = CalendarFragment()
                }
                R.id.feed_fragment_page -> {
                    fragment = FeedFragment()
                }
                R.id.profile_fragment_page -> {
                    fragment = ProfileFragment()
                }
            }
            replaceFragment(fragment!!)

            true
        }
        bottomNavigarionMenu.selectedItemId = R.id.feed_fragment_page
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainFragmentContainerView, fragment)
            .addToBackStack(fragment.tag)
            .commit()
    }
}