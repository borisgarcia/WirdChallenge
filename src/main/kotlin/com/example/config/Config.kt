package com.example.config

class Config private constructor() {
    companion object {
        val instance = Config()
    }

    var redisPort: Int = 6379
    var redisHost: String = "localhost"
    var apiKey: String = "2EyKhk8uerYayEBwC9q3QI7eXSBc4OaN"

}