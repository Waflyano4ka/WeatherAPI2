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

        fun bindView(image: DataWeather, listener: (DataWeather) -> Unit){
            titleScr.text = image.name
            descriptionScr.text = image.feels_like.toString()

            itemView.setOnClickListener { listener(image) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder = ImageViewHolder(LayoutInflater.from(context).inflate(R.layout.row, parent, false))

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bindView(images[position], listener)
    }

    override fun getItemCount(): Int = images.size
}