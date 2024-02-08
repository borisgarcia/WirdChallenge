package com.example.routes

import io.ktor.server.routing.*

fun Route.weatherRouting() {
    route("/weather/{location}") {
        get {

        }
    }
}