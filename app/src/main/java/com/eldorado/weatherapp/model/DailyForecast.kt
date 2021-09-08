package com.eldorado.weatherapp.model

data class DailyForecast (
//    val clouds: Clouds,
    val dt: Int,
    val dt_txt: String,
    val main: Main,
    val pop: Double,
    val rain: Rain,
    val sys: SysForecast,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)
