package com.parungao.weatherweatherlang.Views

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MotionEventCompat
import androidx.lifecycle.ViewModelProviders
import com.parungao.weatherweatherlang.Models.WeatherData
import com.parungao.weatherweatherlang.R
import com.parungao.weatherweatherlang.Utilities.InjectorUtils
import com.parungao.weatherweatherlang.ViewModels.WeatherViewModel
import kotlinx.android.synthetic.main.fragment_cities_list.*

class MainActivity : AppCompatActivity() {

    var favoritesList: MutableList<String> = ArrayList()
    lateinit var city : String
    var actionbar : ActionBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.root_layout, CitiesListFragment.newInstance(), "citiesList")
                .commit()
        }

        actionbar = supportActionBar

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
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

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return when (MotionEventCompat.getActionMasked(event)) {
            MotionEvent.ACTION_DOWN -> {
                val factory = InjectorUtils.provideWeatherViewModelFactory()
                val viewModel = ViewModelProviders.of(this , factory).get(WeatherViewModel::class.java)
                viewModel.callOpenWeatherData()
                spinner.visibility = View.VISIBLE
                true
            } else -> super.onTouchEvent(event)
        }
    }
}