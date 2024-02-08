package com.example.models

import com.google.gson.annotations.SerializedName

data class WeatherData(
    @SerializedName("data") val data: Data,
    @SerializedName("location") val location: Location
)

data class Data(
    @SerializedName("time") val time: String,
    @SerializedName("values") val values: Values
)

data class Values(
    @SerializedName("cloudBase") val cloudBase: Double,
    @SerializedName("cloudCeiling") val cloudCeiling: Double,
    @SerializedName("cloudCover") val cloudCover: Double,
    @SerializedName("dewPoint") val dewPoint: Double,
    @SerializedName("freezingRainIntensity") val freezingRainIntensity: Double,
    @SerializedName("humidity") val humidity: Double,
    @SerializedName("precipitationProbability") val precipitationProbability: Double,
    @SerializedName("pressureSurfaceLevel") val pressureSurfaceLevel: Double,
    @SerializedName("rainIntensity") val rainIntensity: Double,
    @SerializedName("sleetIntensity") val sleetIntensity: Double,
    @SerializedName("snowIntensity") val snowIntensity: Double,
    @SerializedName("temperature") val temperature: Double,
    @SerializedName("temperatureApparent") val temperatureApparent: Double,
    @SerializedName("uvHealthConcern") val uvHealthConcern: Double,
    @SerializedName("uvIndex") val uvIndex: Double,
    @SerializedName("visibility") val visibility: Double,
    @SerializedName("weatherCode") val weatherCode: Double,
    @SerializedName("windDirection") val windDirection: Double,
    @SerializedName("windGust") val windGust: Double,
    @SerializedName("windSpeed") val windSpeed: Double
)

data class Location(
    @SerializedName("lat") val lat: Double,
    @SerializedName("lon") val lon: Double,
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: String
)
