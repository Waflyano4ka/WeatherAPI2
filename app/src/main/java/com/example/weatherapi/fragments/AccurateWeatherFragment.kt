package com.example.weatherapi.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherapi.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_accurate_weather.view.*

class AccurateWeatherFragment : Fragment() {
    lateinit var weather: DataWeather
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val mainView =  inflater.inflate(R.layout.fragment_accurate_weather, container, false)
        var gson = Gson()
        var json = arguments?.getString("weather")
        var type = object : TypeToken<DataWeather>() {}.type

        weather = gson.fromJson(json, type)
        mainView.Text.text = weather.name

        return mainView
    }
}