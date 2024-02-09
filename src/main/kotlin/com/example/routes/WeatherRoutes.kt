package com.example.routes

import com.example.clients.RedisClient
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.weatherRouting() {
    route("/weather/{location}") {
        get() {
            val location = call.parameters["location"] ?: return@get call.respondText("Bad Request", status = HttpStatusCode.BadRequest)
            val redisClient = RedisClient()

            if(!redisClient.exists(location))
                return@get call.respondText("Not Found", status = HttpStatusCode.NotFound)

            val temperature = redisClient.get(location)
            call.respondText(temperature);
        }
    }
}