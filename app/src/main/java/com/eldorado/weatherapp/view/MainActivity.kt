package com.eldorado.weatherapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.eldorado.weatherapp.R
import com.eldorado.weatherapp.databinding.ActivityMainBinding
import com.eldorado.weatherapp.model.toWeatherView
import com.eldorado.weatherapp.network.WeatherApi
import com.eldorado.weatherapp.repository.WeatherRepository
import com.eldorado.weatherapp.viewmodel.MainViewModel
import com.eldorado.weatherapp.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : MainViewModel

    private lateinit var dataBinding : ActivityMainBinding

    private val weatherApi = WeatherApi.getInstance()

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        viewModel = ViewModelProvider(this, MainViewModelFactory(WeatherRepository(weatherApi))).get(MainViewModel::class.java)

        viewModel.weather.observe(this, Observer {
            val weatherView = it.toWeatherView()
            dataBinding.weatherView = weatherView
            dataBinding.iconMainWeather.load(weatherView.iconUrl) {
                crossfade(true)
            }

            Toast.makeText(this, "Weather updated", Toast.LENGTH_SHORT).show()
        })

        viewModel.error.observe(this, Observer {
            Toast.makeText(this, "Erro!", Toast.LENGTH_SHORT).show()
        })

        viewModel.getWeatherByLocationName("Londres");
    }
}
