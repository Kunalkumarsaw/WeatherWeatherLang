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
import kotlinx.android.synthetic.main.fragment_weather_details.*

class CityDetailsFragment : Fragment() {
    companion object {

        private const val MODEL = "model"

        fun newInstance(weatherModel: WeatherData): CityDetailsFragment {
            val args = Bundle()
            args.putSerializable(MODEL, weatherModel)
            val fragment = CityDetailsFragment()
            fragment.arguments = args
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
    }
}