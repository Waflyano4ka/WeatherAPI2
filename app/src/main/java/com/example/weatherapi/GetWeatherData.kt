package com.example.weatherapi

import android.content.Context
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class GetWeather {
    fun GetRequest(URL: String): DataWeather{
        var request: Request = Request.Builder().url(URL).build()
        var okHttpClient: OkHttpClient = OkHttpClient()

        var dataWeather = DataWeather(0.0,0.0,0.0,0.0,0,0,0,0,0,"","null")

        okHttpClient.newCall(request).enqueue(object: Callback{
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

                dataWeather = DataWeather(temp, feels_like, temp_min, temp_max, pressure, humidity, visibility, wind_speed, wind_deg, clouds, name)
            }
        })
        return dataWeather
    }
}