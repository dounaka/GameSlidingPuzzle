package com.game.slidingpuzzle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_puzzle -> {
                showPuzzle()
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_profile -> {
                showProfile()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        if (savedInstanceState == null) showPuzzle()
    }

    private fun showProfile() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ProfileFragment.newInstance())
            .commitNow()
    }

    private fun showPuzzle() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, PuzzleFragment.newInstance())
            .commitNow()
    }
}
