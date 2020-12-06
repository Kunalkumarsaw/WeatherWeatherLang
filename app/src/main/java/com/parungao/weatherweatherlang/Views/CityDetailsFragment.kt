package com.parungao.weatherweatherlang.Views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.parungao.weatherweatherlang.Models.WeatherData
import com.parungao.weatherweatherlang.Models.WeatherModel
import com.parungao.weatherweatherlang.R
import kotlinx.android.synthetic.main.city_item.view.*
import kotlinx.android.synthetic.main.fragment_weather_details.*
import java.math.RoundingMode
import java.text.DecimalFormat

class CityDetailsFragment : Fragment() {
    companion object {

        private const val MODEL = "model"
        lateinit var weather : WeatherData
        private var clicked: Boolean = false

        fun newInstance(weatherModel: WeatherData): CityDetailsFragment {
            val args = Bundle()
            args.putSerializable(MODEL, weatherModel)
            val fragment = CityDetailsFragment()
            fragment.arguments = args
            weather = weatherModel
            return fragment
        }
    }
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather_details, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var df = DecimalFormat("#.#")
        df.roundingMode = RoundingMode.CEILING
        cityTxt.text = weather.name
        temperatureTxt.text = "${df.format(weather.main.temp-273.14)}°C"
        df = DecimalFormat("#")
        df.roundingMode = RoundingMode.CEILING
        highLowTxt.text = "High: ${df.format(weather.main.tempMax-273.14)}°C / Low: ${df.format(weather.main.tempMin-273.14)}°C"
        conditionTxt.text = weather.weather.first().main

        if ((context as MainActivity).favoritesList.contains(weather!!.name)){
            favoritesButton.setImageResource(R.drawable.heart_red)
            clicked = true
        }else{
            favoritesButton.setImageResource(R.drawable.heart_white)
            clicked = false
        }

        favoritesButton.setOnClickListener(){
            clicked = !clicked
            if (clicked){
                favoritesButton.setImageResource(R.drawable.heart_red)
                (context as MainActivity).favoritesList.add(weather.name)
            } else {
                favoritesButton.setImageResource(R.drawable.heart_white)
                (context as MainActivity).favoritesList.remove(weather.name)
            }
        }
    }
}