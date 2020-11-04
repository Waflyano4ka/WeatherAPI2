package com.example.weatherapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapi.fragments.AddFragment
import com.example.weatherapi.fragments.WeatherFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_weather.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addFragment = AddFragment()
        val weatherFragment = WeatherFragment()

        makeCurrentFragment(addFragment)

        bottom_navigation.setOnNavigationItemSelectedListener{
            when (it.itemId){
                R.id.ic_add -> makeCurrentFragment(addFragment)
                R.id.ic_weather -> makeCurrentFragment(weatherFragment)
            }
            true
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }
    }
}