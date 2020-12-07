package com.parungao.weatherweatherlang.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.parungao.weatherweatherlang.Models.WeatherData
import com.parungao.weatherweatherlang.R
import com.parungao.weatherweatherlang.Utilities.InjectorUtils
import com.parungao.weatherweatherlang.ViewModels.WeatherViewModel
import com.parungao.weatherweatherlang.Views.MainActivity
import kotlinx.android.synthetic.main.city_item.view.*
import kotlinx.android.synthetic.main.city_item.view.cityTxt

import java.math.RoundingMode
import java.text.DecimalFormat


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
            var temperature = (weather!!.main.temp)
            val df = DecimalFormat("#.#")
            df.roundingMode = RoundingMode.CEILING

            if (temperature< 0)
                itemView.cardView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorFreezing))
            else if (temperature > 0 && temperature < 15)
                itemView.cardView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorCold))
            else if (temperature > 15 && temperature < 30)
                itemView.cardView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorWarm))
            else
                itemView.cardView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorHot))

            if ((context as MainActivity).favoritesList.contains(weather!!.name)){
                    itemView.imageView.visibility = View.VISIBLE
                }else{
                itemView.imageView.visibility = View.INVISIBLE
            }

            itemView.cityTxt.text = "${weather!!.name}"
            itemView.tempTxt.text = "${df.format(temperature)}°C"
            itemView.descriptionTxt.text = "${weather!!.weather.first().main}"
        }
    }

}