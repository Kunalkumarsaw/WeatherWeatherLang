package com.parungao.weatherweatherlang.Views

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.parungao.weatherweatherlang.R
import com.parungao.weatherweatherlang.Utilities.InjectorUtils
import com.parungao.weatherweatherlang.ViewModels.WeatherViewModel
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.parungao.weatherweatherlang.Adapters.WeatherAdapter
import kotlinx.android.synthetic.main.fragment_cities_list.*

class CitiesListFragment : Fragment() {
    companion object {
        fun newInstance(): CitiesListFragment {
            return CitiesListFragment()
        }
    }
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_cities_list, container,
            false)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUi()
        initializeObservers()
    }
    private fun initializeUi(){
        val activity = activity as Context
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
    }
    private fun initializeObservers(){
        val factory = InjectorUtils.provideWeatherViewModelFactory()
        val viewModel = ViewModelProviders.of(this , factory).get(WeatherViewModel::class.java)
        viewModel.getWeather().observe(this, Observer { weatherAndCitiesObject ->
            recyclerView.adapter = WeatherAdapter(activity as Context, weatherAndCitiesObject.list)
        })
        refreshButton.setOnClickListener{
            viewModel.callOpenWeatherData()
        }
        viewModel.callOpenWeatherData()
    }
}