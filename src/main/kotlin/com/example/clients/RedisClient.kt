package com.example.clients
import redis.clients.jedis.Jedis

class RedisClient {
    private val jedis: Jedis = Jedis("localhost", 6379)

    fun connect() {
        jedis.connect()
    }

    fun set(key: String, value: String){
        jedis.set(key, value);
    }

    fun get(key: String): String{
        return jedis.get(key)
    }
}