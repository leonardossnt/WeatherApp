package com.eldorado.weatherapp.model

data class Forecast(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<DailyForecast>,
    val message: Int
)
