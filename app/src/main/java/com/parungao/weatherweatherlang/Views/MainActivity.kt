package com.parungao.weatherweatherlang.Views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.parungao.weatherweatherlang.Models.WeatherData
import com.parungao.weatherweatherlang.Models.WeatherModel
import com.parungao.weatherweatherlang.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.root_layout, CitiesListFragment.newInstance(), "citiesList")
                .commit()
        }
    }
    fun onCitySelected(weatherData: WeatherData) {
        val detailsFragment =
            CityDetailsFragment.newInstance(weatherData)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.root_layout, detailsFragment, "weatherDetails")
            .addToBackStack(null)
            .commit()
    }
}