package com.parungao.weatherweatherlang.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.parungao.weatherweatherlang.Models.WeatherData
import com.parungao.weatherweatherlang.R
import com.parungao.weatherweatherlang.Views.CityDetailsFragment
import com.parungao.weatherweatherlang.Views.MainActivity
import kotlinx.android.synthetic.main.city_item.view.*

class WeatherAdapter(val context: Context, val list: List<WeatherData>) : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.city_item, parent, false)

        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return list.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weather = list[position]
        holder.setData(weather, position)
        holder.itemView.setOnClickListener {
            (context as MainActivity).onCitySelected(weather)
        }
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun setData(weather: WeatherData?,position : Int){
            itemView.cityTxt.text = "${weather!!.name}"
            itemView.tempTxt.text = "${weather!!.main.temp}"
        }
    }
}