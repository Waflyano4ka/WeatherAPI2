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
        weatherList = getWeatherData.GetWeatherData()

        RecyclerView.adapter = ItemAdapter(context, weatherList){
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("OBJECT_INTENT", it)
            startActivity(intent)
        }
        return view
    }
}
interface Weather {
    fun GetWeatherData() : ArrayList<DataWeather>
}