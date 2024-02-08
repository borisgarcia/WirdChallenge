package com.example.clients

import com.example.models.WeatherData
import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import java.net.URLEncoder

class WeatherClient {
    suspend fun getWeatherInfo(city: String) {
        val client = HttpClient()

        // Simulate failure with 20% probability
        if (Math.random() < 0.2) {
            throw Exception("The API Request Failed") // Use Exception for a more specific error type
        }

        val encodedString = URLEncoder.encode(city, "UTF-8")

        try {
            val response = client.get("https://api.tomorrow.io/v4/weather/realtime?location=$encodedString&apikey=2EyKhk8uerYayEBwC9q3QI7eXSBc4OaN")
            val responseString = response.body<String>().toString()
            val weatherData: WeatherData = Gson().fromJson(responseString, WeatherData::class.java)

            println(city + ": " + (weatherData?.data?.values?.temperature ?: "No disponible"))
        } catch (e: Exception) { // Catch any exceptions, including the simulated one
            println("Error fetching weather data: $e")
        } finally {
            client.close() // Ensure client is closed even if an exception occurs
        }
    }
}