package com.eldorado.weatherapp.network

import com.eldorado.weatherapp.model.CurrentWeather
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("data/2.5/weather?")
    fun getWeatherByLocationName(
        @Query("q") name : String,
        @Query("lang") language : String = "pt_br",
        @Query("units") units : String = "metric",
        @Query("APPID", encoded = true) appId : String = "a0a1ee26bf5c5d99184399a7abba5ec1"
    ) : Call<CurrentWeather>

    companion object {
        var BASE_URL = "https://api.openweathermap.org/"

        var weatherApi: WeatherApi? = null

        fun getInstance() : WeatherApi {
            if (weatherApi == null) {
                val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
                weatherApi = retrofit.create(WeatherApi::class.java)
            }

            return weatherApi!!
        }
    }
}
