package com.example.clients

import com.example.models.WeatherData
import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import java.net.URLEncoder

class WeatherClient {
    suspend fun getWeatherInfo(city: String) {
        val httpClient = HttpClient()
        val redisClient = RedisClient()

        if (Math.random() < 0.2) {
            throw Exception("The API Request Failed")
        }

        val encodedString = URLEncoder.encode(city, "UTF-8")

        try {
            val response = httpClient.get("https://api.tomorrow.io/v4/weather/realtime?location=$encodedString&apikey=2EyKhk8uerYayEBwC9q3QI7eXSBc4OaN")
            val responseString = response.body<String>().toString()
            val weatherData: WeatherData = Gson().fromJson(responseString, WeatherData::class.java)

            //val json = JSON.stringify(person)val json = JSON.stringify(person)
            redisClient.set(city, responseString)

            println(city + ": " + (weatherData?.data?.values?.temperature ?: "No disponible"))
        } catch (e: Exception) {
            println("Error fetching weather data: $e")
        } finally {
            httpClient.close()
        }
    }
}