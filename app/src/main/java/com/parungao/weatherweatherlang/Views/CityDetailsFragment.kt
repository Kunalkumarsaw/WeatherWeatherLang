package com.parungao.weatherweatherlang.Views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.parungao.weatherweatherlang.Models.WeatherData
import com.parungao.weatherweatherlang.R
import com.parungao.weatherweatherlang.Utilities.InjectorUtils
import com.parungao.weatherweatherlang.ViewModels.WeatherViewModel
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
        val factory = InjectorUtils.provideWeatherViewModelFactory()
        val viewModel = ViewModelProviders.of(this , factory).get(WeatherViewModel::class.java)
        viewModel.getCityObject().observe(this, Observer { weatherAndCitiesObject ->
            var df = DecimalFormat("#.#")
            df.roundingMode = RoundingMode.CEILING
            cityTxt.text = weatherAndCitiesObject.name
            temperatureTxt.text = "${df.format(weatherAndCitiesObject.main.temp-273.14)}°C"
            df = DecimalFormat("#")
            df.roundingMode = RoundingMode.CEILING
            highLowTxt.text = "High: ${df.format(weatherAndCitiesObject.main.tempMax-273.14)}°C / Low: ${df.format(weatherAndCitiesObject.main.tempMin-273.14)}°C"
            conditionTxt.text = weatherAndCitiesObject.weather.first().main
            clicked = if ((context as MainActivity).favoritesList.contains(weatherAndCitiesObject!!.name)){
                favoritesButton.setImageResource(R.drawable.heart_red)
                true
            }else{
                favoritesButton.setImageResource(R.drawable.heart_white)
                false
            }
            favoritesButton.setOnClickListener(){
                clicked = !clicked
                if (clicked){
                    favoritesButton.setImageResource(R.drawable.heart_red)
                    (context as MainActivity).favoritesList.add(weatherAndCitiesObject.name)
                } else {
                    favoritesButton.setImageResource(R.drawable.heart_white)
                    (context as MainActivity).favoritesList.remove(weatherAndCitiesObject.name)
                }
            }
        })
        viewModel.callOpenWeatherForCitiesData(weather.id.toString())

    }
}