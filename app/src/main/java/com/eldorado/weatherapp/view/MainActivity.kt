package com.eldorado.weatherapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.eldorado.weatherapp.R
import com.eldorado.weatherapp.network.WeatherApi
import com.eldorado.weatherapp.repository.WeatherRepository
import com.eldorado.weatherapp.viewmodel.MainViewModel
import com.eldorado.weatherapp.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var viewModel : MainViewModel

    private val weatherApi = WeatherApi.getInstance()

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, MainViewModelFactory(WeatherRepository(weatherApi))).get(MainViewModel::class.java)

        viewModel.weather.observe(this, Observer {
            Log.d(TAG, "Current weather => ${it}")
            Toast.makeText(this, "Actual Temperature: ${it.main.temp} ºC", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "Deu bom! ${it.main.temp}")
        })

        viewModel.error.observe(this, Observer {
            Toast.makeText(this, "Erro!", Toast.LENGTH_SHORT).show()
        })

        viewModel.getWeatherByLocationName("Manaus");
    }
}
