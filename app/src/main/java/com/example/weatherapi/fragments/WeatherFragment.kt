package com.example.weatherapi.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapi.DataWeather
import com.example.weatherapi.DetailActivity
import com.example.weatherapi.ItemAdapter
import com.example.weatherapi.R


class WeatherFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_weather, container, false)
        var RecyclerView: RecyclerView = (view.findViewById(R.id.recycleView) as RecyclerView)

        RecyclerView.layoutManager = LinearLayoutManager(getActivity());
        RecyclerView.setHasFixedSize(true)

        var imageList: ArrayList<DataWeather> = ArrayList<DataWeather>()
        imageList.add(DataWeather(0.0, 10.0, 0.0, 0.0, 0, 0, 0, 0, 0, "", "Moscow"))
        imageList.add(DataWeather(0.0, -4.0, 0.0, 0.0, 0, 0, 0, 0, 0, "", "London"))
        imageList.add(DataWeather(0.0, 5.0, 0.0, 0.0, 0, 0, 0, 0, 0, "", "Berlin"))

        RecyclerView.adapter = ItemAdapter(context, imageList){
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("OBJECT_INTENT", it)
            startActivity(intent)
        }
        return view
    }
}