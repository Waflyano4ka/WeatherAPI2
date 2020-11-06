package com.example.weatherapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.weatherapi.fragments.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity(), Weather, City, Communicator {
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

    fun LoadData(): ArrayList<DataCity>{
        var sPref = getPreferences(MODE_PRIVATE)
        var gson = Gson()
        var json = sPref.getString("DATA_CITY", null)
        var type = object : TypeToken<List<DataCity>>() {}.type
        if (json == null){
            return ArrayList<DataCity>()
        }
        else{
            return gson.fromJson(json, type)
        }
    }

    override fun GetWeatherData(): ArrayList<String> {
        var urlArray = ArrayList<String>()
        for (city in LoadData()) {
            urlArray.add(GetSource(city.name, "metric"))
        }
        return urlArray
    }

    override fun DataCitySave(cityList: ArrayList<DataCity>) {
        var sPref = getPreferences(MODE_PRIVATE)
        var ed = sPref.edit()
        var gson = Gson()
        var json = gson.toJson(cityList)
        ed.putString("DATA_CITY", json)
        ed.apply()
    }

    override fun DataCityLoad(): ArrayList<DataCity> {
        return LoadData()
    }

    override fun PassData(weather: DataWeather) {
        val bundle = Bundle()

        var gson = Gson()
        var json = gson.toJson(weather)

        bundle.putString("weather", json)

        val transaction = this.supportFragmentManager.beginTransaction()
        val accurateWeatherFragment = AccurateWeatherFragment()
        accurateWeatherFragment.arguments = bundle

        transaction.replace(R.id.fl_wrapper, accurateWeatherFragment)
        transaction.commit()
    }
}