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
    "Russians swim in such weather",

    "Everybody loves summer rain",
    "Take an umbrella with you and don't get sick",
    "br-r-r ... better to stay at home",
    "Is this even possible?",

    "Don't go! will kill",
    "It's time to make lemon tea",
    "Time to walk the dog",
    "The end of the world is near"
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
        var cloudLeftAnim: Animation = AnimationUtils.loadAnimation(context, R.anim.cloud_day_animation)
        var rainLeftAnim: Animation = AnimationUtils.loadAnimation(context, R.anim.cloud_night_animation)
        var lightningTopAnim: Animation = AnimationUtils.loadAnimation(context, R.anim.lightning_night_animation)
        var snowTopAnim: Animation = AnimationUtils.loadAnimation(context, R.anim.snow_day_animation)

        var dayBackgroundColor = R.drawable.background_day_style
        var nightBackgroundColor = R.drawable.background_night_style
        var dayCloudBackgroundColor = R.drawable.background_day_style_cloud
        var nightCloudBackgroundColor = R.drawable.background_night_style_cloud

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
                mainView.ImageCloud1.setImageResource(R.drawable.w03cloud)
                mainView.ImageCloud1.animation = cloudLeftAnim
                GetClearSkyPhrase(weather.temp.toInt())
            }
            "03d" -> {
                mainView.MainFrame.setBackgroundResource(dayBackgroundColor)
                mainView.ImageCloud1.setImageResource(R.drawable.w03cloud)
                mainView.ImageCloud1.animation = cloudLeftAnim
                mainView.ImageCloud2.setImageResource(R.drawable.w03drain)
                mainView.ImageCloud2.animation = rainLeftAnim
                GetRainPhrase(weather.temp.toInt())
            }
            "04d" -> {
                mainView.MainFrame.setBackgroundResource(dayCloudBackgroundColor)
                mainView.ImageCloud1.setImageResource(R.drawable.w03cloud)
                mainView.ImageCloud1.animation = cloudLeftAnim
                mainView.ImageCloud2.setImageResource(R.drawable.w03drain)
                mainView.ImageCloud2.animation = rainLeftAnim
                GetRainPhrase(weather.temp.toInt())
            }
            "09d" -> {
                mainView.MainFrame.setBackgroundResource(dayCloudBackgroundColor)
                mainView.ImageCloud1.setImageResource(R.drawable.w03cloud)
                mainView.ImageCloud1.animation = cloudLeftAnim
                mainView.ImageCloud2.setImageResource(R.drawable.w03drain)
                mainView.ImageCloud2.animation = rainLeftAnim
                GetRainPhrase(weather.temp.toInt())
            }
            "10d" -> {
                mainView.MainFrame.setBackgroundResource(dayCloudBackgroundColor)
                mainView.ImageSun.setImageResource(R.drawable.w01d)
                mainView.ImageSun.animation = sunTopAnim
                mainView.ImageCloud1.setImageResource(R.drawable.w03cloud)
                mainView.ImageCloud1.animation = cloudLeftAnim
                mainView.ImageCloud2.setImageResource(R.drawable.w03drain)
                mainView.ImageCloud2.animation = rainLeftAnim
                GetRainPhrase(weather.temp.toInt())
            }
            "11d" -> {
                mainView.MainFrame.setBackgroundResource(dayCloudBackgroundColor)
                mainView.ImageCloud1.setImageResource(R.drawable.w03cloud)
                mainView.ImageCloud1.animation = cloudLeftAnim
                mainView.ImageCloud2.setImageResource(R.drawable.w03drain)
                mainView.ImageCloud2.animation = rainLeftAnim
                mainView.ImageLightning1.setImageResource(R.drawable.w03lightning)
                mainView.ImageLightning1.animation = lightningTopAnim
                mainView.ImageLightning2.setImageResource(R.drawable.w03lightning)
                mainView.ImageLightning2.animation = lightningTopAnim
                GetStormPhrase(weather.temp.toInt())
            }
            "13d" -> {
                mainView.MainFrame.setBackgroundResource(dayCloudBackgroundColor)
                mainView.ImageSnow.setImageResource(R.drawable.w13dsnow)
                mainView.ImageSnow.animation = snowTopAnim
            }
            "50d" -> {
                mainView.MainFrame.setBackgroundResource(dayCloudBackgroundColor)
            }
            "01n" -> {
                mainView.MainFrame.setBackgroundResource(nightBackgroundColor)
                mainView.ImageSun.setImageResource(R.drawable.w01m)
                mainView.ImageSun.animation = sunTopAnim
                GetClearSkyPhrase(weather.temp.toInt())
            }
            "02n" -> {
                mainView.MainFrame.setBackgroundResource(nightBackgroundColor)
                mainView.ImageSun.setImageResource(R.drawable.w01m)
                mainView.ImageSun.animation = sunTopAnim
                mainView.ImageCloud1.setImageResource(R.drawable.w03cloud)
                mainView.ImageCloud1.animation = cloudLeftAnim
                GetClearSkyPhrase(weather.temp.toInt())
            }
            "03n" -> {
                mainView.MainFrame.setBackgroundResource(nightBackgroundColor)
                mainView.ImageCloud1.setImageResource(R.drawable.w03cloud)
                mainView.ImageCloud1.animation = cloudLeftAnim
                mainView.ImageCloud2.setImageResource(R.drawable.w03drain)
                mainView.ImageCloud2.animation = rainLeftAnim
                GetRainPhrase(weather.temp.toInt())
            }
            "04n" -> {
                mainView.MainFrame.setBackgroundResource(nightCloudBackgroundColor)
                mainView.ImageCloud1.setImageResource(R.drawable.w03cloud)
                mainView.ImageCloud1.animation = cloudLeftAnim
                mainView.ImageCloud2.setImageResource(R.drawable.w03drain)
                mainView.ImageCloud2.animation = rainLeftAnim
                GetRainPhrase(weather.temp.toInt())
            }
            "09n" -> {
                mainView.MainFrame.setBackgroundResource(nightCloudBackgroundColor)
                mainView.ImageCloud1.setImageResource(R.drawable.w03cloud)
                mainView.ImageCloud1.animation = cloudLeftAnim
                mainView.ImageCloud2.setImageResource(R.drawable.w03drain)
                mainView.ImageCloud2.animation = rainLeftAnim
                GetRainPhrase(weather.temp.toInt())
            }
            "10n" -> {
                mainView.MainFrame.setBackgroundResource(nightCloudBackgroundColor)
                mainView.ImageSun.setImageResource(R.drawable.w01m)
                mainView.ImageSun.animation = sunTopAnim
                mainView.ImageCloud1.setImageResource(R.drawable.w03cloud)
                mainView.ImageCloud1.animation = cloudLeftAnim
                mainView.ImageCloud2.setImageResource(R.drawable.w03drain)
                mainView.ImageCloud2.animation = rainLeftAnim
                GetRainPhrase(weather.temp.toInt())
            }
            "11n" -> {
                mainView.MainFrame.setBackgroundResource(nightCloudBackgroundColor)
                mainView.ImageCloud1.setImageResource(R.drawable.w03cloud)
                mainView.ImageCloud1.animation = cloudLeftAnim
                mainView.ImageCloud2.setImageResource(R.drawable.w03drain)
                mainView.ImageCloud2.animation = rainLeftAnim
                mainView.ImageLightning1.setImageResource(R.drawable.w03lightning)
                mainView.ImageLightning1.animation = lightningTopAnim
                mainView.ImageLightning2.setImageResource(R.drawable.w03lightning)
                mainView.ImageLightning2.animation = lightningTopAnim
                GetStormPhrase(weather.temp.toInt())
            }
            "13n" -> {
                mainView.MainFrame.setBackgroundResource(nightCloudBackgroundColor)
                mainView.ImageSnow.setImageResource(R.drawable.w13dsnow)
                mainView.ImageSnow.animation = snowTopAnim
            }
            "50n" -> {
                mainView.MainFrame.setBackgroundResource(nightCloudBackgroundColor)
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
    fun GetRainPhrase(t: Int){
        if (t > 20){
            mainView.Phrase.text = phraze[5]
        }
        if (t > 0 && t < 21){
            mainView.Phrase.text = phraze[6]
        }
        if (t > -20 && t < 1){
            mainView.Phrase.text = phraze[7]
        }
        if (t > -20 && t < -19){
            mainView.Phrase.text = phraze[8]
        }
    }
    fun GetStormPhrase(t: Int){
        if (t > 20){
            mainView.Phrase.text = phraze[9]
        }
        if (t > 0 && t < 21){
            mainView.Phrase.text = phraze[10]
        }
        if (t > -20 && t < 1){
            mainView.Phrase.text = phraze[11]
        }
        if (t > -20 && t < -19){
            mainView.Phrase.text = phraze[12]
        }
    }
}