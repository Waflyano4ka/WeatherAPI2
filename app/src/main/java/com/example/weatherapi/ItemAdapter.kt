package com.example.weatherapi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter (
    private val context: Context?,
    private val images: ArrayList<DataWeather>,
    val listener: (DataWeather) -> Unit
) : RecyclerView.Adapter<ItemAdapter.ImageViewHolder>(){
    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val titleScr = view.findViewById<TextView>(R.id.titleTv)
        val descriptionScr = view.findViewById<TextView>(R.id.descriptionTv)
        val imageScr = view.findViewById<ImageView>(R.id.imageIv)

        fun bindView(image: DataWeather, listener: (DataWeather) -> Unit){
            titleScr.text = image.name
            descriptionScr.text = image.feels_like.toString()

            when (image.weather_icon){
                "01d" -> imageScr.setImageResource(R.drawable.w01d)
                "02d" -> imageScr.setImageResource(R.drawable.w02d)
                "03d" -> imageScr.setImageResource(R.drawable.w03d)
                "04d" -> imageScr.setImageResource(R.drawable.w04d)
                "09d" -> imageScr.setImageResource(R.drawable.w09d)
                "10d" -> imageScr.setImageResource(R.drawable.w10d)
                "11d" -> imageScr.setImageResource(R.drawable.w11d)
                "13d" -> imageScr.setImageResource(R.drawable.w13d)
                "50d" -> imageScr.setImageResource(R.drawable.w50d)
                "01n" -> imageScr.setImageResource(R.drawable.w01n)
                "02n" -> imageScr.setImageResource(R.drawable.w02n)
                "03n" -> imageScr.setImageResource(R.drawable.w03n)
                "04n" -> imageScr.setImageResource(R.drawable.w04n)
                "09n" -> imageScr.setImageResource(R.drawable.w09n)
                "10n" -> imageScr.setImageResource(R.drawable.w10n)
                "11n" -> imageScr.setImageResource(R.drawable.w11n)
                "13n" -> imageScr.setImageResource(R.drawable.w13n)
                "50n" -> imageScr.setImageResource(R.drawable.w50n)
            }

            itemView.setOnClickListener { listener(image) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder = ImageViewHolder(LayoutInflater.from(context).inflate(R.layout.row, parent, false))

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bindView(images[position], listener)
    }

    override fun getItemCount(): Int = images.size
}