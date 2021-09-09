package com.eldorado.weatherapp.repository

import com.eldorado.weatherapp.network.WeatherApi

class WeatherRepository constructor(private val weatherApi: WeatherApi) {
    fun getWeatherByLocationName(name : String) = weatherApi.getWeatherByLocationName(name)
}
