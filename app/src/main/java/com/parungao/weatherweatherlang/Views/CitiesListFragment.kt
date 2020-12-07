package com.parungao.weatherweatherlang.Views

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.view.GestureDetectorCompat
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


        (context as MainActivity).actionbar!!.title = "Weather Forecast"
        (context as MainActivity).actionbar!!.setDisplayHomeAsUpEnabled(false)

    }
    private fun initializeObservers(){
        val factory = InjectorUtils.provideWeatherViewModelFactory()
        val viewModel = ViewModelProviders.of(this , factory).get(WeatherViewModel::class.java)
        viewModel.getWeather().observe(this, Observer { weatherAndCitiesObject ->
            recyclerView.adapter = WeatherAdapter(activity as Context, weatherAndCitiesObject.list)
            spinner.visibility = View.GONE
        })
        viewModel.getError().observe(this, Observer { errorMsg ->
            spinner.visibility = View.GONE
            Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show();
        })
        viewModel.callOpenWeatherData()
    }
}