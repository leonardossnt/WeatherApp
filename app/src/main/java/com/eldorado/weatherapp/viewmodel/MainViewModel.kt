package com.eldorado.weatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eldorado.weatherapp.model.CurrentWeather
import com.eldorado.weatherapp.repository.WeatherRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: WeatherRepository) : ViewModel() {
    val weather = MutableLiveData<CurrentWeather>()
    val error = MutableLiveData<String>()

    fun getWeatherByLocationName(name : String) {
        val res = repository.getWeatherByLocationName(name)
        res.enqueue(object : Callback<CurrentWeather> {
            override fun onResponse(call: Call<CurrentWeather>, response: Response<CurrentWeather>) {
                weather.postValue(response.body());
            }

            override fun onFailure(call: Call<CurrentWeather>, t: Throwable) {
                t.printStackTrace();
                error.postValue(t.message)
            }
        })
    }
}
