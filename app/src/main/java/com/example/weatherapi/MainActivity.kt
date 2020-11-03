package com.example.weatherapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                var request = GetWeatherData()
                var dataWeather = request.GetRequest("http://api.openweathermap.org/data/2.5/weather?q=%D0%9C%D0%BE%D1%81%D0%BA%D0%B2%D0%B0&units=metric&appid=918c0ad6a6ed19ef0077f927868d3327")
                ErrorString.text = dataWeather.name
                return false
            }

        })
    }
}