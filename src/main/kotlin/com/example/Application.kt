package com.example

import com.example.clients.RedisClient
import com.example.clients.WeatherClient
import com.example.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
            .start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureHTTP()
    configureRouting()

    val redisClient = RedisClient()
    redisClient.connect()

    suspend fun fetchWeatherData() {
        val weatherClient = WeatherClient()
        val cities = listOf("New York", "Zurich", "Auckland", "Sydney", "London", "Atlanta")

        for (city in cities) {
            while (true) {
                try {
                    weatherClient.getWeatherInfo(city)
                    break
                } catch (e: Exception) {
                    println("Error fetching weather data for $city, retrying...")
                }
            }
        }
    }

    val timer = CoroutineScope(Dispatchers.IO).launch {
        while (true) {
            fetchWeatherData()
            delay(5 * 60 * 1000L)
        }
    }
}
