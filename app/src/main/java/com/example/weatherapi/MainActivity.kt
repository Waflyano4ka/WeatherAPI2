package com.example.weatherapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapi.fragments.AddFragment
import com.example.weatherapi.fragments.Weather
import com.example.weatherapi.fragments.WeatherFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_weather.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity(), Weather {
    var weatherList: ArrayList<DataWeather> = ArrayList<DataWeather>()

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

    private fun GetSource(city: String, units: String): String{
        var source = BuildConfig.FORECAST_BASE_URL + city +
                BuildConfig.UNITS_PARAM + units +
                BuildConfig.APPID_PARAM + BuildConfig.API_KEY
        return source
    }

    override fun GetWeatherData(): ArrayList<String> {
        var urlArray = ArrayList<String>()
        urlArray.add(GetSource("Moscow","metric"))
        urlArray.add(GetSource("St. Petersburg","metric"))
        urlArray.add(GetSource("Kazan","metric"))
        urlArray.add(GetSource("Vladivostok","metric"))
        return urlArray
    }
}