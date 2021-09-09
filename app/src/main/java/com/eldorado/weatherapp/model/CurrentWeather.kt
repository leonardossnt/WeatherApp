package com.eldorado.weatherapp.model

data class CurrentWeather(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)

fun CurrentWeather.toWeatherView() = WeatherView(
    name,
    String.format("%.1f", main.temp),
    weather[0].description,
    "${String.format("%.1f", main.feels_like)}\u2103",
    "${String.format("%.1f", wind.speed)}m/s",
    "${main.humidity}%",
    "https://openweathermap.org/img/wn/${weather[0].icon}@2x.png"
)
