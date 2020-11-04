package com.example.weatherapi.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapi.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


class WeatherFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_weather, container, false)
        var RecyclerView: RecyclerView = (view.findViewById(R.id.recycleView) as RecyclerView)

        RecyclerView.layoutManager = LinearLayoutManager(getActivity());
        RecyclerView.setHasFixedSize(true)

        var weatherList: ArrayList<DataWeather> = ArrayList<DataWeather>()
        var getWeatherData : Weather = MainActivity()

        var request: Request = Request.Builder().url("https://api.openweathermap.org/data/2.5/weather?q=London&appid=918c0ad6a6ed19ef0077f927868d3327").build()
        var okHttpClient: OkHttpClient = OkHttpClient()

        var dataWeather = DataWeather(0.0,0.0,0.0,0.0,0,0,0,0,0,"","null")

        okHttpClient.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                dataWeather = DataWeather(0.0,0.0,0.0,0.0,0,0,0,0,0,"","Error")
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

                weatherList.add(DataWeather(temp, feels_like, temp_min, temp_max, pressure, humidity, visibility, wind_speed, wind_deg, clouds, name))

                RecyclerView.adapter = ItemAdapter(context, weatherList){
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra("OBJECT_INTENT", it)
                    startActivity(intent)
                }
            }
        })
        return view
    }
}
interface Weather {
    fun GetWeatherData() : ArrayList<DataWeather>
}