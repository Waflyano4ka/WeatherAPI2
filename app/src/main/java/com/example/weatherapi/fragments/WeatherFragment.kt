package com.example.weatherapi.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapi.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


class WeatherFragment : Fragment() {
    private lateinit var mainView: View
    private lateinit var recyclerView: RecyclerView
    private var mainContext: Context? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainView = inflater.inflate(R.layout.fragment_weather, container, false)
        mainContext = context
        return mainView
    }

    var weatherList: ArrayList<DataWeather> = ArrayList<DataWeather>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView = mainView.findViewById<RecyclerView>(R.id.recycleView)
        recyclerView.setHasFixedSize(true)

        weatherList.clear()

        var getWeatherData: Weather = activity as MainActivity

        for(url in getWeatherData.GetWeatherData()){
            loadData(url)
        }
    }

    fun loadData(URL: String) {
        var request: Request = Request.Builder()
            .url(URL)
            .build()
        var okHttpClient: OkHttpClient = OkHttpClient()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("Error", e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                val json = JSONObject(response!!.body!!.string())

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

                weatherList.add(
                    DataWeather(
                        temp,
                        feels_like,
                        temp_min,
                        temp_max,
                        pressure,
                        humidity,
                        visibility,
                        wind_speed,
                        wind_deg,
                        clouds,
                        name
                    )
                )
                activity?.runOnUiThread {
                    recyclerView.adapter = ItemAdapter(mainContext, weatherList) {
                        val intent = Intent(mainContext, DetailActivity::class.java)
                        intent.putExtra("OBJECT_INTENT", it)
                        startActivity(intent)
                    }
                }
            }
        })
    }
}

interface Weather {
    fun GetWeatherData(): ArrayList<String>
}