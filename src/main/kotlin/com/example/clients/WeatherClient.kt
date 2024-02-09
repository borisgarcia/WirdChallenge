package com.example.clients

import com.example.config.Config
import com.example.models.WeatherData
import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import java.net.URLEncoder
import java.time.LocalDateTime
import java.time.ZoneOffset

class WeatherClient {
    suspend fun getWeatherInfo(city: String) {
        val httpClient = HttpClient()
        val redisClient = RedisClient()

        if (Math.random() < 0.2) {
            val key = "Error"+LocalDateTime.now(ZoneOffset.UTC).toString()
            redisClient.set(key, "The API Request Failed")
            println(key)
            throw Exception("The API Request Failed")
        }

        val encodedString = URLEncoder.encode(city, "UTF-8")

        try {
            val response = httpClient.get("https://api.tomorrow.io/v4/weather/realtime?location=$encodedString&apikey=${Config.instance.apiKey}")
            val responseString = response.body<String>().toString()
            val weatherData: WeatherData = Gson().fromJson(responseString, WeatherData::class.java)

            redisClient.set(city, responseString)

            println(city + ": " + (weatherData?.data?.values?.temperature ?: "No disponible"))
        } catch (e: Exception) {
            redisClient.set("Error", LocalDateTime.now(ZoneOffset.UTC).toString() + ": Error fetching weather data: $e")
            println("Error fetching weather data: $e")
        } finally {
            httpClient.close()
        }
    }
}