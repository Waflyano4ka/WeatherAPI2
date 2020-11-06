package com.example.weatherapi.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.weatherapi.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_accurate_weather.view.*

val phraze = listOf(
    "This weather is made for swimming",
    "Great weather today for a walk",
    "Even though the sun is outside, it is better to dress warmly",
    "Russians swim in such weather"
)

class AccurateWeatherFragment : Fragment() {
    lateinit var mainView : View
    lateinit var weather: DataWeather
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mainView =  inflater.inflate(R.layout.fragment_accurate_weather, container, false)
        var gson = Gson()
        var json = arguments?.getString("weather")
        var type = object : TypeToken<DataWeather>() {}.type

        weather = gson.fromJson(json, type)
        mainView.Name.text = weather.name
        mainView.Degrees.text = weather.feels_like.toInt().toString() + "°"
        mainView.MainFirst.text = weather.weather_main + "\n" + weather.temp_min.toInt() + " / " + weather.temp_max.toInt() + "°C"
        mainView.Pressure.text = weather.pressure.toString() + " mb"
        mainView.Humidity.text = weather.humidity.toString() + " %"
        mainView.Visibility.text = weather.visibility.toString() + " m"
        mainView.WindSpeed.text = weather.wind_speed.toString() + " m/s"

        var sunTopAnim: Animation = AnimationUtils.loadAnimation(context, R.anim.clear_sky_day_animation)

        var dayBackgroundColor = R.drawable.background_day_style

        when (weather.weather_icon){
            "01d" -> {
                mainView.MainFrame.setBackgroundResource(dayBackgroundColor)
                mainView.ImageSun.setImageResource(R.drawable.w01d)
                mainView.ImageSun.animation = sunTopAnim
                GetClearSkyPhrase(weather.temp.toInt())
            }
            "02d" -> {
                mainView.MainFrame.setBackgroundResource(dayBackgroundColor)
                mainView.ImageSun.setImageResource(R.drawable.w01d)
                mainView.ImageSun.animation = sunTopAnim
            }
            "03d" -> {
                mainView.MainFrame.setBackgroundResource(dayBackgroundColor)
                mainView.ImageSun.setImageResource(R.drawable.w01d)
                mainView.ImageSun.animation = sunTopAnim
            }
            "04d" -> {
                mainView.MainFrame.setBackgroundResource(dayBackgroundColor)
                mainView.ImageSun.setImageResource(R.drawable.w01d)
                mainView.ImageSun.animation = sunTopAnim
            }
            "09d" -> {
                mainView.MainFrame.setBackgroundResource(dayBackgroundColor)
                mainView.ImageSun.setImageResource(R.drawable.w01d)
                mainView.ImageSun.animation = sunTopAnim
            }
            "10d" -> {
                mainView.MainFrame.setBackgroundResource(dayBackgroundColor)
                mainView.ImageSun.setImageResource(R.drawable.w01d)
                mainView.ImageSun.animation = sunTopAnim
            }
            "11d" -> {
                mainView.MainFrame.setBackgroundResource(dayBackgroundColor)
                mainView.ImageSun.setImageResource(R.drawable.w01d)
                mainView.ImageSun.animation = sunTopAnim
            }
            "13d" -> {
                mainView.MainFrame.setBackgroundResource(dayBackgroundColor)
                mainView.ImageSun.setImageResource(R.drawable.w01d)
                mainView.ImageSun.animation = sunTopAnim
            }
            "50d" -> {
                mainView.MainFrame.setBackgroundResource(dayBackgroundColor)
                mainView.ImageSun.setImageResource(R.drawable.w01d)
                mainView.ImageSun.animation = sunTopAnim
            }
            "01n" -> {
                mainView.MainFrame.setBackgroundResource(dayBackgroundColor)
                mainView.ImageSun.setImageResource(R.drawable.w01d)
                mainView.ImageSun.animation = sunTopAnim
                GetClearSkyPhrase(weather.temp.toInt())
            }
            "02n" -> {
                mainView.MainFrame.setBackgroundResource(dayBackgroundColor)
                mainView.ImageSun.setImageResource(R.drawable.w01d)
                mainView.ImageSun.animation = sunTopAnim
            }
            "03n" -> {
                mainView.MainFrame.setBackgroundResource(dayBackgroundColor)
                mainView.ImageSun.setImageResource(R.drawable.w01d)
                mainView.ImageSun.animation = sunTopAnim
            }
            "04n" -> {
                mainView.MainFrame.setBackgroundResource(dayBackgroundColor)
                mainView.ImageSun.setImageResource(R.drawable.w01d)
                mainView.ImageSun.animation = sunTopAnim
            }
            "09n" -> {
                mainView.MainFrame.setBackgroundResource(dayBackgroundColor)
                mainView.ImageSun.setImageResource(R.drawable.w01d)
                mainView.ImageSun.animation = sunTopAnim
            }
            "10n" -> {
                mainView.MainFrame.setBackgroundResource(dayBackgroundColor)
                mainView.ImageSun.setImageResource(R.drawable.w01d)
                mainView.ImageSun.animation = sunTopAnim
            }
            "11n" -> {
                mainView.MainFrame.setBackgroundResource(dayBackgroundColor)
                mainView.ImageSun.setImageResource(R.drawable.w01d)
                mainView.ImageSun.animation = sunTopAnim
            }
            "13n" -> {
                mainView.MainFrame.setBackgroundResource(dayBackgroundColor)
                mainView.ImageSun.setImageResource(R.drawable.w01d)
                mainView.ImageSun.animation = sunTopAnim
            }
            "50n" -> {
                mainView.MainFrame.setBackgroundResource(dayBackgroundColor)
                mainView.ImageSun.setImageResource(R.drawable.w01d)
                mainView.ImageSun.animation = sunTopAnim
            }
        }
        return mainView
    }

    fun GetClearSkyPhrase(t: Int){
        if (t > 20){
            mainView.Phrase.text = phraze[0]
        }
        if (t > 0 && t < 21){
            mainView.Phrase.text = phraze[1]
        }
        if (t > -20 && t < 1){
            mainView.Phrase.text = phraze[3]
        }
        if (t > -20 && t < -19){
            mainView.Phrase.text = phraze[4]
        }
    }
}