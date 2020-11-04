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

    fun GetRequest(URL: String): DataWeather{
        var request: Request = Request.Builder().url(URL).build()
        var okHttpClient: OkHttpClient = OkHttpClient()

        var dataWeather = DataWeather(0.0,0.0,0.0,0.0,0,0,0,0,0,"","null")

        okHttpClient.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                dataWeather = DataWeather(0.0,0.0,0.0,0.0,0,0,0,0,0,"","Error")
            }
            override fun onResponse(call: Call, response: Response) {
                val json = JSONObject(response!!.body!!.string())
                runOnUiThread {
                    var jsonMain = json.getJSONObject("main")
                    var temp: Double = jsonMain.getDouble("temp")
                    var feels_like: Double = jsonMain.getDouble("feels_like")
                    var temp_min: Double = jsonMain.getDouble("temp_min")
                    var temp_max: Double = jsonMain.getDouble("temp_max")
                    var pressure: Int = jsonMain.getInt("pressure")
                    var humidity: Int = jsonMain.getInt("humidity")

                    var visibility: Int = json.getInt("visibility")

                    var jsonWind = json.getJSONObject("wind")
                    var wind_speed: Int = jsonWind.getInt("speed")
                    var wind_deg: Int = jsonWind.getInt("deg")

                    var clouds: String = json.getJSONObject("clouds").toString()
                    var name: String = json.getString("name")

                    dataWeather = DataWeather(temp, feels_like, temp_min, temp_max, pressure, humidity, visibility, wind_speed, wind_deg, clouds, name
                    )
                }
            }
        })
        return dataWeather
    }

    private fun GetSource(city: String, units: String): String{
        var source = BuildConfig.FORECAST_BASE_URL + city +
                BuildConfig.UNITS_PARAM + units +
                BuildConfig.APPID_PARAM + BuildConfig.API_KEY
        return source
    }

    override fun GetWeatherData(): ArrayList<DataWeather> {
        weatherList.add(GetRequest(GetSource("Moscow", "metric")))
        return weatherList
    }
}