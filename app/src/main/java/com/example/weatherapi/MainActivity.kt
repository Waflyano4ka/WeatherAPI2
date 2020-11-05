package com.example.weatherapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapi.fragments.AddFragment
import com.example.weatherapi.fragments.City
import com.example.weatherapi.fragments.Weather
import com.example.weatherapi.fragments.WeatherFragment
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_weather.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity(), Weather, City {
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
        var sPref = getPreferences(MODE_PRIVATE)
        var gson = Gson()
        var json = sPref.getString("DATA_CITY", null)
        var type = object : TypeToken<List<DataCity>>() {}.type
        var cityList: ArrayList<DataCity> = ArrayList<DataCity>()
        cityList = gson.fromJson(json, type)

        var urlArray = ArrayList<String>()
        for (city in cityList) {
            urlArray.add(GetSource(city.name, "metric"))
        }
        return urlArray
    }

    override fun DataCitySave(cityList: DataCity) {
        var sPref = getPreferences(MODE_PRIVATE)
        var ed = sPref.edit()
        var gson = Gson()
        var json = gson.toJson(cityList)
        ed.putString("DATA_CITY", json)
        ed.apply()
    }
}